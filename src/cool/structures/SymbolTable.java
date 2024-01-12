package cool.structures;

import java.io.File;

import org.antlr.v4.runtime.*;

import cool.compiler.Compiler;
import cool.parser.CoolParser;

public class SymbolTable {
    public static Scope globals;
    public static ParserRuleContext ctx;

    private static boolean semanticErrors;

    public static void defineBasicClasses(ParserRuleContext ctx) {
        globals = new DefaultScope(null);
        semanticErrors = false;
        SymbolTable.ctx = ctx;

        // Add basic classes
        ClassSymbol intClass = new ClassSymbol("Int");
        ClassSymbol boolClass = new ClassSymbol("Bool");
        ClassSymbol stringClass = new ClassSymbol("String");
        ClassSymbol ioClass = new ClassSymbol("IO");

        globals.add(ClassSymbol.Object);
        globals.add(ioClass);
        globals.add(intClass);
        globals.add(stringClass);
        globals.add(boolClass);

        ClassSymbol selfType = new SelfTypeClass("SELF_TYPE_" + ClassSymbol.Object, ClassSymbol.Object);
        SymbolTable.globals.add(selfType);

        // Add basic methods
        FunctionSymbol abortMethod = new FunctionSymbol(ClassSymbol.Object, "abort");
        FunctionSymbol typeNameMethod = new FunctionSymbol(ClassSymbol.Object, "type_name");
        FunctionSymbol copyMethod = new FunctionSymbol(ClassSymbol.Object, "copy");
        FunctionSymbol outStringMethod = new FunctionSymbol(ioClass, "out_string");
        IdSymbol outStringParam = new IdSymbol("x");
        FunctionSymbol outIntMethod = new FunctionSymbol(ioClass, "out_int");
        IdSymbol outIntParam = new IdSymbol("x");
        FunctionSymbol inStringMethod = new FunctionSymbol(ioClass, "in_string");
        FunctionSymbol inIntMethod = new FunctionSymbol(ioClass, "in_int");
        FunctionSymbol lengthMethod = new FunctionSymbol(stringClass, "length");
        FunctionSymbol concatMethod = new FunctionSymbol(stringClass, "concat");
        IdSymbol concatParam = new IdSymbol("s");
        FunctionSymbol substrMethod = new FunctionSymbol(stringClass, "substr");
        IdSymbol substrParam1 = new IdSymbol("i");
        IdSymbol substrParam2 = new IdSymbol("l");

        abortMethod.setType(ClassSymbol.Object);
        ClassSymbol.Object.add(abortMethod);
        typeNameMethod.setType(stringClass);
        ClassSymbol.Object.add(typeNameMethod);
        copyMethod.setType(selfType);
        ClassSymbol.Object.add(copyMethod);
        outStringMethod.setType(selfType);
        outStringMethod.add(outStringParam);
        outStringParam.setType(stringClass);
        ioClass.add(outStringMethod);
        outIntMethod.setType(selfType);
        outIntMethod.add(outIntParam);
        outIntParam.setType(intClass);
        ioClass.add(outIntMethod);
        inStringMethod.setType(stringClass);
        ioClass.add(inStringMethod);
        inIntMethod.setType(intClass);
        ioClass.add(inIntMethod);
        lengthMethod.setType(intClass);
        stringClass.add(lengthMethod);
        concatMethod.setType(stringClass);
        concatParam.setType(stringClass);
        concatMethod.add(concatParam);
        stringClass.add(concatMethod);
        substrMethod.setType(stringClass);
        substrParam1.setType(intClass);
        substrParam2.setType(intClass);
        substrMethod.add(substrParam1);
        substrMethod.add(substrParam2);
        stringClass.add(substrMethod);
    }

    /**
     * Displays a semantic error message.
     *
     * @param ctx Used to determine the enclosing class context of this error,
     *            which knows the file name in which the class was defined.
     * @param info Used for line and column information.
     * @param str The error message.
     */
    public static void error(ParserRuleContext ctx, Token info, String str) {
        if (! (ctx instanceof CoolParser.ProgramContext)) {
            while (!(ctx.getParent() instanceof CoolParser.ProgramContext))
                ctx = ctx.getParent();
        }

        String message = "\"" + new File(Compiler.fileNames.get(ctx)).getName()
                + "\", line " + info.getLine()
                + ":" + (info.getCharPositionInLine() + 1)
                + ", Semantic error: " + str;

        System.err.println(message);

        semanticErrors = true;
    }

    public static void error(String str) {
        String message = "Semantic error: " + str;

        System.err.println(message);

        semanticErrors = true;
    }

    public static boolean hasSemanticErrors() {
        return semanticErrors;
    }
}
