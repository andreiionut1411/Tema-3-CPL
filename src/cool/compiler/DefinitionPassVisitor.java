package cool.compiler;

import cool.parser.CoolParser;
import cool.structures.*;

public class DefinitionPassVisitor implements ASTVisitor {
    Scope crtScope = SymbolTable.globals;

    public ClassSymbol getCrtClass() {
        Scope scope = crtScope;

        while (!(scope instanceof ClassSymbol)) {
            scope = scope.getParent();
        }

        return (ClassSymbol) scope;
    }

    @Override
    public Object visit(Program program) {
        for (Class classes: program.childClasses) {
            classes.accept(this);
        }

        return null;
    }

    @Override
    public Object visit(cool.compiler.Class coolClass) {
        ClassSymbol symbol = (ClassSymbol) SymbolTable.globals.idLookup(coolClass.type.getText());
        Scope oldScope = crtScope;
        crtScope = symbol;

        for (Feature feature: coolClass.features) {
            feature.accept(this);
        }

        crtScope = oldScope;
        return null;
    }

    @Override
    public Object visit(Formal formal) {
        var name = formal.name.token;
        var type = formal.type;
        var sym = new IdSymbol(name.getText());

        if (name.getText().equals("self")) {
            SymbolTable.error(formal.ctx, name, "Method " + ((FunctionSymbol) crtScope).getName() + " of class " +
                    ((ClassSymbol) crtScope.getParent()).getName() + " has formal parameter with illegal name self");
            return null;
        }

        if (!crtScope.add(sym)) {
            SymbolTable.error(formal.ctx, name, "Method " + ((FunctionSymbol) crtScope).getName() + " of class " +
                    ((ClassSymbol) crtScope.getParent()).getName() + " redefines formal parameter " + name.getText());
            return null;
        }

        if (type.getText().equals("SELF_TYPE")) {
            SymbolTable.error(formal.ctx, type, "Method " + ((FunctionSymbol) crtScope).getName() + " of class " +
                    ((ClassSymbol) crtScope.getParent()).getName() + " has formal parameter " + name.getText() +
                    " with illegal type SELF_TYPE");
            return null;
        }

        if (SymbolTable.globals.idLookup(type.getText())  == null) {
            SymbolTable.error(formal.ctx, type, "Method " + ((FunctionSymbol) crtScope).getName() + " of class " +
                    ((ClassSymbol) crtScope.getParent()).getName() + " has formal parameter " + name.getText() +
                    " with undefined type " + type.getText());
            return null;
        }

        formal.name.setScope(crtScope);
        formal.name.setSymbol(sym);
        sym.setType((ClassSymbol) SymbolTable.globals.idLookup(type.getText()));

        return null;
    }

    @Override
    public Object visit(Attribute attribute) {
        var id = attribute.name.token;
        var type = attribute.type.getText();
        IdSymbol symbol = new IdSymbol(id.getText());

        if (type.equals("SELF_TYPE")) {
            type = "SELF_TYPE_" + ((ClassSymbol) crtScope).getName();
        }

        attribute.name.setScope(crtScope);
        attribute.name.setSymbol(symbol);
        symbol.setType((ClassSymbol) SymbolTable.globals.idLookup(type));

        if (id.getText().equals("self")) {
            SymbolTable.error(attribute.ctx, attribute.name.token, "Class " + ((ClassSymbol) crtScope).getName() +  " has attribute with illegal name self");
            return null;
        }

        if (crtScope.getParent().idLookup(id.getText()) != null) {
            SymbolTable.error(attribute.ctx, attribute.name.token, "Class " + ((ClassSymbol) crtScope).getName() + " redefines inherited attribute " + id.getText());
            return null;
        }

        boolean ret = crtScope.add(symbol);

        if (!ret) {
            SymbolTable.error(attribute.ctx, attribute.name.token, "Class " + ((ClassSymbol) crtScope).getName() + " redefines attribute " + id.getText());
            return null;
        }

        if (SymbolTable.globals.idLookup(type)  == null) {
            SymbolTable.error(attribute.ctx, attribute.type, "Class " + ((ClassSymbol) crtScope).getName() +
                    " has attribute " + id.getText() + " with undefined type " + type);
        }

        if (attribute.assign != null) {
            attribute.assign.accept(this);
        }

        return null;
    }

    @Override
    public Object visit(Method method) {
        var name = method.name.token;
        var type = method.type.getText();
        var sym = new FunctionSymbol(crtScope, name.getText());

        if (type.equals("SELF_TYPE")) {
            type = "SELF_TYPE_" + ((ClassSymbol) crtScope).getName();
        }

        method.name.setSymbol(sym);
        method.name.setScope(crtScope);
        sym.setType((ClassSymbol) SymbolTable.globals.idLookup(type));

        if (!crtScope.add(sym)) {
            SymbolTable.error(method.ctx, method.name.token, "Class " + ((ClassSymbol) crtScope).getName() + " redefines method " + name.getText());
            return null;
        }

        if (SymbolTable.globals.idLookup(type) == null) {
            SymbolTable.error(method.ctx, method.type, "Class " + ((ClassSymbol) crtScope).getName() +
                    " has method " + name.getText() + " with undefined return type " + method.type.getText());
        }

        crtScope = sym;

        for (var formal: method.args) {
            formal.accept(this);
        }

        method.e.accept(this);
        crtScope = sym.getParent();

        return null;
    }

    @Override
    public Object visit(Id id) {
        return null;
    }

    @Override
    public Object visit(CoolInteger integer) {
        return null;
    }

    @Override
    public Object visit(CoolString string) {
        return null;
    }

    @Override
    public Object visit(CoolTrue coolTrue) {
        return null;
    }

    @Override
    public Object visit(CoolFalse coolFalse) {
        return null;
    }

    @Override
    public Object visit(BinaryOp binaryOp) {
        binaryOp.lhs.accept(this);
        binaryOp.rhs.accept(this);
        return null;
    }

    @Override
    public Object visit(NegativeInt negativeInt) {
        return null;
    }

    @Override
    public Object visit(Paren paren) {
        paren.e.accept(this);
        return null;
    }

    @Override
    public Object visit(Not nnot) {
        nnot.e.accept(this);
        return null;
    }

    @Override
    public Object visit(Assign assign) {
        assign.e.accept(this);
        return null;
    }

    @Override
    public Object visit(New nnew) {
        String typeName = nnew.type.getText();

        if (typeName.equals("SELF_TYPE")) {
            typeName = "SELF_TYPE_" + getCrtClass().getName();
        }

        var type = SymbolTable.globals.idLookup(typeName);

        if (type == null) {
            SymbolTable.error(nnew.ctx, nnew.type, "new is used with undefined type " + nnew.type.getText());
        }

        nnew.setCrtScope(crtScope);

        return null;
    }

    @Override
    public Object visit(IsVoid isVoid) {
        isVoid.e.accept(this);

        return null;
    }

    @Override
    public Object visit(FunctionCall functionCall) {
        if (functionCall.e != null) {
            functionCall.e.accept(this);
        }

        for (Expression expression: functionCall.args) {
            expression.accept(this);
        }

        return null;
    }

    @Override
    public Object visit(If iff) {
        iff.cond.accept(this);
        iff.ifBranch.accept(this);
        iff.elseBranch.accept(this);
        return null;
    }

    @Override
    public Object visit(While wwhile) {
        wwhile.cond.accept(this);
        wwhile.loopExpr.accept(this);
        return null;
    }

    @Override
    public Object visit(Local local) {
        String localType = local.type.getText();

        if (localType.equals("SELF_TYPE")) {
            localType = "SELF_TYPE_" + getCrtClass();
        }

        if (local.name.token.getText().equals("self")) {
            SymbolTable.error(local.ctx, local.name.token, "Let variable has illegal name self");
            return null;
        }

        if (SymbolTable.globals.idLookup(localType) == null) {
            SymbolTable.error(local.ctx, local.type, "Let variable " + local.name.token.getText() + " has undefined type " + local.type.getText());
            return null;
        }

        if (local.assign != null)
            local.assign.accept(this);

        return null;
    }

    @Override
    public Object visit(Let let) {
        Scope oldScope = crtScope;

        for (Local local: let.locals) {
            var newScope = new DefaultScope(crtScope);
            String localType = local.type.getText();

            if (localType.equals("SELF_TYPE")) {
                localType = "SELF_TYPE_" + getCrtClass();
            }

            local.accept(this);
            crtScope = newScope;
            IdSymbol sym = new IdSymbol(local.name.token.getText());
            sym.setType((ClassSymbol) SymbolTable.globals.idLookup(localType));
            local.name.setScope(crtScope);
            local.name.setSymbol(sym);
            newScope.add(sym);
        }

        let.e.accept(this);
        crtScope = oldScope;

        return null;
    }

    @Override
    public Object visit(Case ccase) {
        ccase.e.accept(this);

        for(int i = 0; i < ccase.branchNames.size(); i++) {
            if (ccase.branchNames.get(i).token.getText().equals("self")) {
                SymbolTable.error(ccase.ctx, ccase.branchNames.get(i).token, "Case variable has illegal name self");
            } else if (ccase.branchTypes.get(i).getText().equals("SELF_TYPE")) {
                SymbolTable.error(ccase.ctx, ccase.branchTypes.get(i), "Case variable " + ccase.branchNames.get(i).token.getText() +
                        " has illegal type SELF_TYPE");
            } else if (SymbolTable.globals.idLookup(ccase.branchTypes.get(i).getText()) == null) {
                SymbolTable.error(ccase.ctx, ccase.branchTypes.get(i), "Case variable " + ccase.branchNames.get(i).token.getText() +
                        " has undefined type " + ccase.branchTypes.get(i).getText());
            }

            IdSymbol sym = new IdSymbol(ccase.branchNames.get(i).token.getText());
            crtScope.add(sym);
            sym.setType((ClassSymbol) SymbolTable.globals.idLookup(ccase.branchTypes.get(i).getText()));

            Scope oldScope = crtScope;
            crtScope = new DefaultScope(crtScope);
            ccase.branchNames.get(i).setSymbol(sym);
            ccase.branchNames.get(i).setScope(crtScope);
            ccase.results.get(i).accept(this);
            crtScope = oldScope;
        }
        return null;
    }

    @Override
    public Object visit(CodeBlock codeBlock) {
        crtScope = new DefaultScope(crtScope);

        for (Expression expression: codeBlock.e) {
            expression.accept(this);
        }

        crtScope = crtScope.getParent();

        return null;
    }
}
