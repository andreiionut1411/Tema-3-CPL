package cool.compiler;
import cool.parser.CoolParser;
import cool.structures.ClassSymbol;
import cool.structures.IdSymbol;
import cool.structures.SymbolTable;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.misc.Pair;
import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroupFile;

import java.io.File;
import java.util.*;

import static java.lang.Math.ceil;

public class CodeGenVisitor implements ASTVisitor<ST>{
    static STGroupFile templates = new STGroupFile("cool/cgen.stg");

    ST mainSection;	// filled directly (through visitor returns)
    ST dataSection; // filled collaterally ("global" access)
    ST funcSection;
    ST programST = templates.getInstanceOf("program");
    String crtClassName = "";
    HashMap<Integer, Integer> intTable = new HashMap<>(); // label number -> value
    HashMap<Integer, Integer> invIntTable = new HashMap<>(); // value -> label number
    HashMap<Integer, String> strTable = new HashMap<>(); // label number -> value
    HashMap<String, Integer> invStrTable = new HashMap<>(); // value -> label number
    HashMap<Integer, String> objNameTable = new HashMap<>(); // label number -> value
    HashMap<String, Integer> invObjNameTable = new HashMap<>(); // value -> label number
    HashMap<String, Integer> formalsOffsetTable = null; // Name of the formal -> offset
    HashMap<String, Integer> localsOffsetTable = null; // Name of the local -> offset
    int dispatchLabelNumber = 0;
    int numberOfLocalVariables = 0;
    int iffId = 0;
    boolean storeAttribute = false; // In case we need to store at the ID we make it true. If we need to load the value from ID, we make it false

    private void populateGenericFuncTable(String className) {
        ClassSymbol sym = (ClassSymbol) SymbolTable.globals.idLookup(className);
        sym.functionOffsetTable.put("abort", new Pair<>(0, className));
        sym.functionOffsetTable.put("type_name", new Pair<>(4, className));
        sym.functionOffsetTable.put("copy", new Pair<>(8, className));
    }

    private void populateIOFuncTable() {
        ClassSymbol sym = (ClassSymbol) SymbolTable.globals.idLookup("IO");
        sym.functionOffsetTable.put("abort", new Pair<>(0, sym.getName()));
        sym.functionOffsetTable.put("type_name", new Pair<>(4, sym.getName()));
        sym.functionOffsetTable.put("copy", new Pair<>(8, sym.getName()));
        sym.functionOffsetTable.put("out_string", new Pair<>(12, sym.getName()));
        sym.functionOffsetTable.put("out_int", new Pair<>(16, sym.getName()));
        sym.functionOffsetTable.put("in_string", new Pair<>(20, sym.getName()));
        sym.functionOffsetTable.put("in_int", new Pair<>(24, sym.getName()));
    }

    private void populateStringFuncTable() {
        ClassSymbol sym = (ClassSymbol) SymbolTable.globals.idLookup("String");
        sym.functionOffsetTable.put("abort", new Pair<>(0, sym.getName()));
        sym.functionOffsetTable.put("type_name", new Pair<>(4, sym.getName()));
        sym.functionOffsetTable.put("copy", new Pair<>(8, sym.getName()));
        sym.functionOffsetTable.put("length", new Pair<>(12, sym.getName()));
        sym.functionOffsetTable.put("concat", new Pair<>(16, sym.getName()));
        sym.functionOffsetTable.put("substr", new Pair<>(20, sym.getName()));
    }

    private void populateFuncTables() {
        populateGenericFuncTable("Object");
        populateIOFuncTable();
        populateGenericFuncTable("Int");
        populateStringFuncTable();
        populateStringFuncTable();
        populateGenericFuncTable("Bool");
    }

    @Override
    public ST visit(Program program) {
        dataSection = templates.getInstanceOf("sequenceSpaced");
        funcSection = templates.getInstanceOf("sequenceSpaced");
        mainSection = templates.getInstanceOf("sequence");
        var intLiterals = templates.getInstanceOf("sequence");
        var strLiterals = templates.getInstanceOf("sequence");

        // Add the integer literals needed for the basic classes
        intTable.put(0, 0);
        intTable.put(1, 6);
        intTable.put(2, 2);
        intTable.put(3, 3);
        intTable.put(4, 4);
        invIntTable.put(0, 0);
        invIntTable.put(6, 1);
        invIntTable.put(2, 2);
        invIntTable.put(3, 3);
        invIntTable.put(4, 4);

        // Add the string literals needed for the basic classes
        strTable.put(0, "");
        strTable.put(1, "Object");
        strTable.put(2, "IO");
        strTable.put(3, "Int");
        strTable.put(4, "String");
        strTable.put(5, "Bool");
        invStrTable.put("", 0);
        invStrTable.put("Object", 1);
        invStrTable.put("IO", 2);
        invStrTable.put("Int", 3);
        invStrTable.put("String", 4);
        invStrTable.put("Bool", 5);

        populateFuncTables();

        // We add all the offsets for the attributes and functions in a big map for later use
        for (var e: program.childClasses) {
            var crtClass = (ClassSymbol) SymbolTable.globals.idLookup(e.type.getText());

            crtClass.populateOffsetTables();
        }

        for (var e : program.childClasses) {
            mainSection.add("e", e.accept(this));
        }

        for (Integer key: intTable.keySet()) {
            var intSt = templates.getInstanceOf("defineIntLiterals").add("labelNumber", key).add("number", intTable.get(key));
            intLiterals.add("e", intSt);
        }

        for (Integer key: strTable.keySet()) {
            var value = strTable.get(key);
            var strST = templates.getInstanceOf("defineStrLiterals")
                    .add("labelNumber", key)
                    .add("string", value)
                    .add("len", 4 + (int)ceil((value.length() + 1) / 4.0))
                    .add("intConst", invIntTable.get(value.length()));
            strLiterals.add("e", strST);
        }

        programST.add("data", dataSection);
        programST.add("textFuncs", funcSection);
        programST.add("integerLiterals", intLiterals);
        programST.add("strLiterals", strLiterals);
        programST.add("textMain", mainSection);

        return programST;
    }

    @Override
    public ST visit(Class coolClass) {
        ClassSymbol sym = (ClassSymbol) SymbolTable.globals.idLookup(coolClass.type.getText());
        int classIndex = objNameTable.size() + 5;

        crtClassName = sym.getName();

        if (!invStrTable.containsKey(sym.getName())) {
            strTable.put(strTable.size(), sym.getName());
            invStrTable.put(sym.getName(), invStrTable.size());
        }

        objNameTable.put(classIndex, sym.getName());
        invObjNameTable.put(sym.getName(), classIndex);
        programST.add("classNames", "str_const" + (invStrTable.size() - 1));
        programST.add("classProto", sym.getName() + "_protObj");
        programST.add("classProto", sym.getName() + "_init");

        if (!invIntTable.containsKey(sym.getName().length())) {
            intTable.put(intTable.size(), sym.getName().length());
            invIntTable.put(sym.getName().length(), invIntTable.size());
        }

        var crtClass = sym;
        int numberOfPrevAttributes = crtClass.numberOfPrevAttributes;
        var protoDef = templates.getInstanceOf("defineProto");
        var functionTable = templates.getInstanceOf("defineFuncTables").add("className", coolClass.type.getText());
        var initsST = templates.getInstanceOf("initClass");

        for (var attr: crtClass.attrOffsetTable.keySet()) {
            String type = crtClass.attrOffsetTable.get(attr).b;

            if (type.equals("Int")) {
                protoDef.add("attributes", "int_const0");
            } else if (type.equals("String")) {
                protoDef.add("attributes", "str_const0");
            } else if (type.equals("Bool")) {
                protoDef.add("attributes", "bool_const0");
            } else if (type.startsWith("SELF_TYPE")) {
                protoDef.add("attributes", "0");
            }
        }

        for (var func: crtClass.functionOffsetTable.keySet()) {
            functionTable.add("functionName", crtClass.functionOffsetTable.get(func).b + "." + func);
        }

        initsST.add("className", crtClassName)
                .add("parent", ((ClassSymbol)((ClassSymbol)SymbolTable.globals.idLookup(crtClassName)).getParent()).getName());

        for (var feature: coolClass.features) {
            if (feature instanceof Attribute) {
                ((Attribute) feature).setOffset(numberOfPrevAttributes * 4);
                initsST.add("attr", feature.accept(this));
                numberOfPrevAttributes++;
            } else {
                feature.accept(this);
            }
        }

        programST.add("classInits", initsST);
        protoDef.add("objName", sym.getName()).add("objNumber", classIndex).add("size", crtClass.attrOffsetTable.size() + 3);
        programST.add("protoDefinitions", protoDef);
        programST.add("classTables", functionTable);

        return null;
    }

    @Override
    public ST visit(Formal formal) {
        return null;
    }

    @Override
    public ST visit(Attribute attribute) {
        if (attribute.assign != null) {
            var attrST = templates.getInstanceOf("initAttr");

            attrST.add("expr", attribute.assign.accept(this))
                .add("offset", attribute.offset);

            return attrST;
        }

        return null;
    }

    @Override
    public ST visit(Method method) {
        String prevClass = crtClassName;
        HashMap<String, Integer> prevFormalsOffsetTable = formalsOffsetTable;
        int offset = 12;

        numberOfLocalVariables = 0;
        formalsOffsetTable = new HashMap<>();

        for (var formal: method.args) {
            formalsOffsetTable.put(formal.name.token.getText(), offset);
            offset += 4;
        }

        var funcDef = templates.getInstanceOf("defineFunction")
                .add("name", crtClassName + "." + method.name.token.getText())
                .add("body", method.e.accept(this));

        if (method.args.size() > 0) {
            funcDef.add("removeParams",
                    templates.getInstanceOf("freeParams").add("offset", method.args.size() * 4).add("name", "params"));
        }

        if (numberOfLocalVariables > 0) {
            funcDef.add("removeLocals",
                    templates.getInstanceOf("freeParams").add("offset", numberOfLocalVariables * 4).add("name", "locals"));
        }

        programST.add("textFuncs", funcDef);
        crtClassName = prevClass;
        formalsOffsetTable = prevFormalsOffsetTable;
        return null;
    }

    @Override
    public ST visit(Id id) {
        var classAttrTable = ((ClassSymbol) SymbolTable.globals.idLookup(crtClassName)).attrOffsetTable;

        if (id.token.getText().equals("self")) {
            return templates.getInstanceOf("dispatchOnSelf");
        }

        if (!storeAttribute) {
            // We first verify if it is a local variable, then we try to see if it is a parameter of the function, then
            // we verify if it is an attribute.
            if (localsOffsetTable != null && localsOffsetTable.containsKey(id.token.getText())) {
                var st = templates.getInstanceOf("loadFormal");
                int offset = localsOffsetTable.get(id.token.getText());

                st.add("offset", offset);

                return st;
            } else if (formalsOffsetTable != null && formalsOffsetTable.containsKey(id.token.getText())) {
                var st = templates.getInstanceOf("loadFormal");
                int offset = formalsOffsetTable.get(id.token.getText());

                st.add("offset", offset);

                return st;
            } else if (classAttrTable.containsKey(id.token.getText())) {
                // In case the id is an attribute, then we load it from the appropriate offset
                var st = templates.getInstanceOf("loadAttribute");
                int offset = classAttrTable.get(id.token.getText()).a;

                st.add("offset", offset);

                return st;
            }
        } else {
            if (localsOffsetTable != null && localsOffsetTable.containsKey(id.token.getText())) {
                var st = templates.getInstanceOf("storeFormal");
                int offset = localsOffsetTable.get(id.token.getText());

                st.add("offset", offset);

                return st;
            } else if (formalsOffsetTable != null && formalsOffsetTable.containsKey(id.token.getText())) {
                var st = templates.getInstanceOf("storeFormal");
                int offset = formalsOffsetTable.get(id.token.getText());

                st.add("offset", offset);

                return st;
            } else if (classAttrTable.containsKey(id.token.getText())) {
                var st = templates.getInstanceOf("storeAttribute");
                int offset = classAttrTable.get(id.token.getText()).a;

                st.add("offset", offset);

                return st;
            }
        }

        return null;
    }

    @Override
    public ST visit(CoolInteger integer) {
        var st = templates.getInstanceOf("literal");
        int integerLiteral = Integer.parseInt(integer.token.getText());

        // If the crt integer doesn't exist as a constant, we add it.
        if (!invIntTable.containsKey(integerLiteral)) {
            intTable.put(intTable.size(), integerLiteral);
            invIntTable.put(integerLiteral, invIntTable.size());
        }

        st.add("constant", "int_const" + invIntTable.get(integerLiteral));

        return st;
    }

    @Override
    public ST visit(CoolString string) {
        var st = templates.getInstanceOf("literal");
        String strLiteral = string.token.getText();

        addNewStrConst(strLiteral);

        st.add("constant", "str_const" + invStrTable.get(strLiteral));

        return st;
    }

    private void addNewStrConst(String strLiteral) {
        if (!invStrTable.containsKey(strLiteral)) {
            strTable.put(strTable.size(), strLiteral);
            invStrTable.put(strLiteral, invStrTable.size());
        }

        if (!invIntTable.containsKey(strLiteral.length())) {
            intTable.put(intTable.size(), strLiteral.length());
            invIntTable.put(strLiteral.length(), invIntTable.size());
        }
    }

    @Override
    public ST visit(CoolTrue coolTrue) {
        var st = templates.getInstanceOf("literal")
                .add("constant", "bool_const1");
        return st;
    }

    @Override
    public ST visit(CoolFalse coolFalse) {
        var st = templates.getInstanceOf("literal")
                .add("constant", "bool_const0");
        return st;
    }

    @Override
    public ST visit(BinaryOp binaryOp) {
        switch (binaryOp.op.getText())
        {
            case "+" -> {
                 return templates.getInstanceOf("plus")
                        .add("e1", binaryOp.lhs.accept(this))
                        .add("e2", binaryOp.rhs.accept(this));
            }
            case "-" -> {
                return templates.getInstanceOf("minus")
                        .add("e1", binaryOp.lhs.accept(this))
                        .add("e2", binaryOp.rhs.accept(this));
            }
            case "*" -> {
                return templates.getInstanceOf("mult")
                        .add("e1", binaryOp.lhs.accept(this))
                        .add("e2", binaryOp.rhs.accept(this));
            }
            case "/" -> {
                return templates.getInstanceOf("div")
                        .add("e1", binaryOp.lhs.accept(this))
                        .add("e2", binaryOp.rhs.accept(this));
            }
            case "=" -> {
                return templates.getInstanceOf("eq")
                        .add("e1", binaryOp.lhs.accept(this))
                        .add("e2", binaryOp.rhs.accept(this))
                        .add("id", ++iffId);
            }
            case "<" -> {
                return templates.getInstanceOf("lt")
                        .add("e1", binaryOp.lhs.accept(this))
                        .add("e2", binaryOp.rhs.accept(this))
                        .add("id", ++iffId);
            }
            case "<=" -> {
                return templates.getInstanceOf("le")
                        .add("e1", binaryOp.lhs.accept(this))
                        .add("e2", binaryOp.rhs.accept(this))
                        .add("id", ++iffId);
            }
        }
        return null;
    }

    @Override
    public ST visit(NegativeInt negativeInt) {
        return templates.getInstanceOf("unaryMinus").add("e1", negativeInt.e.accept(this));
    }

    @Override
    public ST visit(Paren paren) {
        return paren.e.accept(this);
    }

    @Override
    public ST visit(Not nnot) {
        var st = templates.getInstanceOf("nott");
        st.add("expr", nnot.e.accept(this))
                .add("id", this.iffId);
        this.iffId += 1;
        return st;
    }

    @Override
    public ST visit(Assign assign) {
        boolean prevStoreAttribute = storeAttribute;

        storeAttribute = false;
        var ST = templates.getInstanceOf("assign").add("value", assign.e.accept(this));

        storeAttribute = true;
        ST.add("storeValue", assign.name.accept(this));
        storeAttribute = prevStoreAttribute;

        return ST;
    }

    @Override
    public ST visit(New nnew) {
        var st = templates.getInstanceOf("newObject");

        if (nnew.type.getText().equals("SELF_TYPE")) {
            return templates.getInstanceOf("newSelfType");
        } else {
            st.add("object", nnew.type.getText());
        }

        return st;
    }

    @Override
    public ST visit(IsVoid isVoid) {
        var st = templates.getInstanceOf("isVoid");
        st.add("expr", isVoid.e.accept(this))
                .add("id", this.iffId);
        this.iffId += 1;
        return st;
    }

    @Override
    public ST visit(FunctionCall functionCall) {
        var st = templates.getInstanceOf("callFunction");
        var classSymbol = (ClassSymbol)SymbolTable.globals.idLookup(functionCall.callerType.getName());
        int offset = classSymbol.functionOffsetTable.get(functionCall.name.token.getText()).a;
        ParserRuleContext ctx = functionCall.ctx;

        if (! (ctx instanceof CoolParser.ProgramContext)) {
            while (!(ctx.getParent() instanceof CoolParser.ProgramContext))
                ctx = ctx.getParent();
        }

        String fileName = new File(Compiler.fileNames.get(ctx)).getName();

        addNewStrConst(fileName);

        st.add("dispatchNumber", dispatchLabelNumber++)
                .add("methodOffset", offset)
                .add("fileNameConst", invStrTable.get(fileName))
                .add("line", functionCall.name.token.getLine());

        if (functionCall.dispatchType == null) {
            st.add("loadDispTable", templates.getInstanceOf("loadDispTab"));
        } else {
            st.add("loadDispTable", templates.getInstanceOf("loadSpecificDispTable").add("name", functionCall.dispatchType.getText()));
        }

        if (functionCall.e != null) {
            // When calling the method, we are loading the attribute.
            boolean prevStoreAttribute = storeAttribute;

            storeAttribute = false;
            st.add("expr", functionCall.e.accept(this));
            storeAttribute = prevStoreAttribute;
        } else {
            st.add("expr", templates.getInstanceOf("dispatchOnSelf"));
        }

        // Add the params in the reverse order to the stack
        for (int i = functionCall.args.size() - 1; i >= 0; i--) {
            var param = functionCall.args.get(i);
            var paramST = templates.getInstanceOf("addParams");

            paramST.add("loadParam", param.accept(this));
            st.add("params", paramST);
        }

        return st;
    }

    @Override
    public ST visit(If iff) {
        var st = templates.getInstanceOf("iff");
        st.add("condition", iff.cond.accept(this))
                .add("thenBranch", iff.ifBranch.accept(this))
                .add("elseBranch", iff.elseBranch.accept(this))
                .add("id", this.iffId);
        this.iffId += 1;
        return st;
    }

    @Override
    public ST visit(While wwhile) {
        return null;
    }

    @Override
    public ST visit(Local local) {
        var st = templates.getInstanceOf("initLocal");
        ST init = null;

        if (local.assign == null) {
            if (local.type.getText().equals("Int")) {
                init = templates.getInstanceOf("literal").add("constant", "int_const0");
            } else if (local.type.getText().equals("String")) {
                init = templates.getInstanceOf("literal").add("constant", "str_const0");
            } else if (local.type.getText().equals("Bool")) {
                init = templates.getInstanceOf("literal").add("constant", "bool_const0");
            }
        } else {
            init = local.assign.accept(this);
        }

        st.add("initialization", init).add("offset", localsOffsetTable.get(local.name.token.getText()));

        return st;
    }

    @Override
    public ST visit(Let let) {
        var st = templates.getInstanceOf("let").add("localsOffset", let.locals.size() * 4);
        var inits = templates.getInstanceOf("sequence");
        var prevLocals = localsOffsetTable;
        int offset = -1;

        localsOffsetTable = new HashMap<>();

        for (int i = 0; i < let.locals.size(); i++) {
            localsOffsetTable.put(let.locals.get(i).name.token.getText(), offset * 4);
            offset--;
            numberOfLocalVariables++;
            inits.add("e", let.locals.get(i).accept(this));
        }

        st.add("inits", inits);
        st.add("body", let.e.accept(this));
        localsOffsetTable = prevLocals;

        return st;
    }

    @Override
    public ST visit(Case ccase) {
        return null;
    }

    @Override
    public ST visit(CodeBlock codeBlock) {
        var st = templates.getInstanceOf("sequence");

        for (var e: codeBlock.e) {
            st.add("e", e.accept(this));
        }

        return st;
    }
}
