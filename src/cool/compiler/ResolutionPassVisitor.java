package cool.compiler;

import cool.structures.*;

import java.util.ArrayList;

public class ResolutionPassVisitor implements ASTVisitor<ClassSymbol> {
    Scope crtScope = SymbolTable.globals;

    // The function returns true if type2 is the ancestor of type1
    public boolean verifyIfAncestor(ClassSymbol type1, ClassSymbol type2) {
        ClassSymbol crtClass = type1;

        if (type1.getName().equals(type2.getName())) {
            return true;
        }

        if (type1.getName().startsWith("SELF_TYPE")) {
            crtClass = ((SelfTypeClass) crtClass).getConcreteSelfClass();
        }

        while (crtClass != null) {
            if (crtClass.getName().equals(type2.getName())) {
                return true;
            }

            crtClass = (ClassSymbol) crtClass.getParent();
        }

        return false;
    }

    public ClassSymbol leastUpperBound(ClassSymbol type1, ClassSymbol type2) {
        ArrayList<ClassSymbol> type1Chain = new ArrayList<>();
        ArrayList<ClassSymbol> type2Chain = new ArrayList<>();
        ClassSymbol crtType = type1;

        while (crtType != null) {
            type1Chain.add(crtType);
            crtType = (ClassSymbol) crtType.getParent();
        }

        crtType = type2;

        while (crtType != null) {
            type2Chain.add(crtType);
            crtType = (ClassSymbol) crtType.getParent();
        }

        for (ClassSymbol option1: type1Chain) {
            for (ClassSymbol option2: type2Chain) {
                if (option1.getName().equals(option2.getName())) {
                    return option1;
                }
            }
        }

        return null;
    }

    // The function finds the class in which we are currently
    public ClassSymbol getCrtClass(Id id) {
        Scope crtScope = id.getScope();

        while (!(crtScope instanceof ClassSymbol) && crtScope != null) {
            crtScope = crtScope.getParent();
        }

        return (ClassSymbol) crtScope;
    }

    @Override
    public ClassSymbol visit(Program program) {
        for (Class classes: program.childClasses) {
            classes.accept(this);
        }

        return null;
    }

    @Override
    public ClassSymbol visit(Class coolClass) {
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
    public ClassSymbol visit(Formal formal) {
        return null;
    }

    @Override
    public ClassSymbol visit(Attribute attribute) {
        var idType = attribute.name.accept(this);

        if (attribute.assign != null) {
            var assignType = attribute.assign.accept(this);
            String idTypeStr = null;
            String assignTypeStr = null;

            if (idType != null) {
                if (idType.getName().startsWith("SELF_TYPE")) {
                    idTypeStr = "SELF_TYPE";
                } else {
                    idTypeStr = idType.getName();
                }
            }

            if (assignType != null) {
                if (assignType.getName().startsWith("SELF_TYPE")) {
                    assignTypeStr = "SELF_TYPE";
                } else {
                    assignTypeStr = assignType.getName();
                }
            }

            if (assignType != null && !verifyIfAncestor(assignType, idType)) {
                SymbolTable.error(attribute.ctx, attribute.assign.token, "Type " + assignTypeStr +
                        " of initialization expression of attribute " + attribute.name.token.getText() +
                        " is incompatible with declared type " + idTypeStr);
                return null;
            }

            if (assignType == null) {
                return null;
            }
        }

        return idType;
    }

    @Override
    public ClassSymbol visit(Method method) {
        ClassSymbol type = ((FunctionSymbol) method.name.getScope().funcLookup(method.name.token.getText())).getType();
        ClassSymbol bodyType = method.e.accept(this);
        String typeName = type.getName();

        if (typeName.startsWith("SELF_TYPE")) {
            typeName = "SELF_TYPE";
        }

        if (type != null && bodyType != null && !verifyIfAncestor(bodyType, type)) {
            SymbolTable.error(method.ctx, method.e.token, "Type " + bodyType.getName() +
                    " of the body of method " + method.name.token.getText() + " is incompatible with declared " +
                    "return type " + typeName);
        }

        return null;
    }

    @Override
    public ClassSymbol visit(Id id) {
        var symbol = (IdSymbol) id.getScope().idLookup(id.token.getText());

        if (symbol == null) {
            SymbolTable.error(id.ctx, id.token, "Undefined identifier " + id.token.getText());
            return null;
        }

        if (id.token.getText().equals("self")) {
            String selfType = "SELF_TYPE_" + symbol.getType().getName();
            return (ClassSymbol) SymbolTable.globals.idLookup(selfType);
        }

        return symbol.getType();
    }

    @Override
    public ClassSymbol visit(CoolInteger integer) {
        return (ClassSymbol) SymbolTable.globals.idLookup("Int");
    }

    @Override
    public ClassSymbol visit(CoolString string) {
        return (ClassSymbol) SymbolTable.globals.idLookup("String");
    }

    @Override
    public ClassSymbol visit(CoolTrue coolTrue) {
        return (ClassSymbol) SymbolTable.globals.idLookup("Bool");
    }

    @Override
    public ClassSymbol visit(CoolFalse coolFalse) {
        return (ClassSymbol) SymbolTable.globals.idLookup("Bool");
    }

    @Override
    public ClassSymbol visit(BinaryOp binaryOp) {
        var type1 = binaryOp.lhs.accept(this);
        var type2 = binaryOp.rhs.accept(this);
        var op = binaryOp.op.getText();
        String typeStr1 = null;
        String typeStr2 = null;

        if (type1 != null) {
            if (type1.getName().startsWith("SELF_TYPE")) {
                typeStr1 = "SELF_TYPE";
            } else {
                typeStr1 = type1.getName();
            }
        }

        if (type2 != null) {
            if (type2.getName().startsWith("SELF_TYPE")) {
                typeStr2 = "SELF_TYPE";
            } else {
                typeStr2 = type2.getName();
            }
        }

        if (op.equals("+") || op.equals("-") || op.equals("/") || op.equals("*") || op.equals("<") || op.equals("<=")) {
            if (type1 != null && !type1.getName().equals("Int")) {
                SymbolTable.error(binaryOp.ctx, binaryOp.lhs.token, "Operand of " + binaryOp.op.getText() + " has type " +
                        typeStr1 + " instead of Int");
                return null;
            }

            if (type2 != null && !type2.getName().equals("Int")) {
                SymbolTable.error(binaryOp.ctx, binaryOp.rhs.token, "Operand of " + binaryOp.op.getText() + " has type " +
                        typeStr2 + " instead of Int");
                return null;
            }
        } else if (op.equals("=")) {
            if (!((type1.getName().equals("Int") && type2.getName().equals("Int")) ||
                    (type1.getName().equals("String") && type2.getName().equals("String")) ||
                    (type1.getName().equals("Bool") && type2.getName().equals("Bool")) ||
                    (!type1.getName().equals("Int") && !type1.getName().equals("String") && !type1.getName().equals("Bool") &&
                     !type2.getName().equals("Int") && !type2.getName().equals("String") && !type2.getName().equals("Bool")))) {
                SymbolTable.error(binaryOp.ctx, binaryOp.op, "Cannot compare " + typeStr1 + " with " + typeStr2);
                return null;
            }
        }

        if (type1 == null || type2 == null) {
            return null;
        }

        if (op.equals("<") || op.equals("<=") || op.equals("=")) {
            return (ClassSymbol) SymbolTable.globals.idLookup("Bool");
        }

        return (ClassSymbol) SymbolTable.globals.idLookup("Int");
    }

    @Override
    public ClassSymbol visit(NegativeInt negativeInt) {
        var type = negativeInt.e.accept(this);
        String typeStr = null;

        if (type != null) {
            if (type.getName().startsWith("SELF_TYPE")) {
                typeStr = "SELF_TYPE";
            } else {
                typeStr = type.getName();
            }
        }

        if (type != null && !type.getName().equals("Int")) {
            SymbolTable.error(negativeInt.ctx, negativeInt.e.token, "Operand of ~ has type " + typeStr + " instead of Int");
            return null;
        }

        return type;
    }

    @Override
    public ClassSymbol visit(Paren paren) {
        var type = paren.e.accept(this);

        return type;
    }

    @Override
    public ClassSymbol visit(Not nnot) {
        var type = nnot.e.accept(this);
        String typeStr = null;

        if (type != null) {
            if (type.getName().startsWith("SELF_TYPE")) {
                typeStr = "SELF_TYPE";
            } else {
                typeStr = type.getName();
            }
        }

        if (type != null && !type.getName().equals("Bool")) {
            SymbolTable.error(nnot.ctx, nnot.e.token, "Operand of not has type " + typeStr + " instead of Bool");
            return null;
        }

        if (type == null) {
            return null;
        }

        return (ClassSymbol) SymbolTable.globals.idLookup("Bool");
    }

    @Override
    public ClassSymbol visit(Assign assign) {
        var idType =  assign.name.accept(this);
        var exprType = assign.e.accept(this);
        String idTypeStr = null;
        String exprTypeStr = null;

        if (idType != null) {
            if (idType.getName().startsWith("SELF_TYPE")) {
                idTypeStr = "SELF_TYPE";

                // Daca avem SELF_TYPE, atunci variabila are tipul atributului self din clasa curenta,
                // nu din clasa in care a fost definit.
                idType = (ClassSymbol) SymbolTable.globals.idLookup("SELF_TYPE_" + getCrtClass(assign.name));
            } else {
                idTypeStr = idType.getName();
            }
        }

        if (exprType != null) {
            if (exprType.getName().startsWith("SELF_TYPE")) {
                exprTypeStr = "SELF_TYPE";
            } else {
                exprTypeStr = exprType.getName();
            }
        }

        if (idType != null && exprType != null && !verifyIfAncestor(exprType, idType)) {
            SymbolTable.error(assign.ctx, assign.e.token, "Type " + exprTypeStr + " of assigned expression is incompatible " +
                    "with declared type " + idTypeStr + " of identifier " + assign.name.token.getText());
            return null;
        }

        if (assign.name.token.getText().equals("self")) {
            SymbolTable.error(assign.ctx, assign.name.token, "Cannot assign to self");
        }

        if (idType == null || exprType == null) {
            return null;
        }

        return exprType;
    }

    @Override
    public ClassSymbol visit(New nnew) {
        String newType = nnew.type.getText();

        if (newType.equals("SELF_TYPE")) {
            Scope crtScope = nnew.getCrtScope();

            while (!(crtScope instanceof ClassSymbol) && crtScope != null) {
                crtScope = crtScope.getParent();
            }

            newType = "SELF_TYPE_" + ((ClassSymbol) crtScope).getName();
        }

        var type = (ClassSymbol) SymbolTable.globals.idLookup(newType);

        if (type == null) {
            return null;
        }

        return type;
    }

    @Override
    public ClassSymbol visit(IsVoid isVoid) {
        return (ClassSymbol) SymbolTable.globals.idLookup("Bool");
    }

    @Override
    public ClassSymbol visit(FunctionCall functionCall) {
        ClassSymbol callerType = null;
        ClassSymbol actualCallerType = null;

        if (functionCall.e != null && functionCall.dispatchType == null) {
            callerType = functionCall.e.accept(this);
            actualCallerType = callerType;
        }

        if (functionCall.e != null && functionCall.dispatchType != null) {
            if (functionCall.dispatchType.getText().equals("SELF_TYPE")) {
                SymbolTable.error(functionCall.ctx, functionCall.dispatchType, "Type of static dispatch cannot be SELF_TYPE");
                return null;
            } else {
                callerType = (ClassSymbol) SymbolTable.globals.idLookup(functionCall.dispatchType.getText());
                var originalCallerType = functionCall.e.accept(this);
                actualCallerType = originalCallerType;
                String originalCallerTypeStr = originalCallerType.getName();

                if (originalCallerTypeStr.startsWith("SELF_TYPE")) {
                    originalCallerTypeStr = "SELF_TYPE";
                }

                if (callerType == null) {
                    SymbolTable.error(functionCall.ctx, functionCall.dispatchType, "Type " + functionCall.dispatchType.getText() +
                            " of static dispatch is undefined");
                    return null;
                } else if (!verifyIfAncestor(originalCallerType, callerType)) {
                    SymbolTable.error(functionCall.ctx, functionCall.dispatchType, "Type " + functionCall.dispatchType.getText() +
                            " of static dispatch is not a superclass of type " + originalCallerTypeStr);
                    return null;
                }
            }
        }

        if (callerType != null && callerType.getName().startsWith("SELF_TYPE")) {
            callerType = ((SelfTypeClass) callerType).getConcreteSelfClass();
        }

        if (functionCall.e == null) {
            callerType = getCrtClass(functionCall.name);
            actualCallerType = (ClassSymbol) SymbolTable.globals.idLookup("SELF_TYPE_" + callerType.getName());
        }

        if (callerType != null) {
            FunctionSymbol symbol = (FunctionSymbol) callerType.funcLookup(functionCall.name.token.getText());

            if (symbol == null) {
                SymbolTable.error(functionCall.ctx, functionCall.name.token, "Undefined method " +
                        functionCall.name.token.getText() + " in class " + callerType.getName());
                return null;
            }

            if (symbol.getFormals().size() != functionCall.args.size()) {
                SymbolTable.error(functionCall.ctx, functionCall.name.token, "Method " + functionCall.name.token.getText() +
                        " of class " + callerType.getName() + " is applied to wrong number of arguments");
                return null;
            }

            String[] keys = symbol.getFormals().keySet().toArray(new String[0]);

            for (int i = 0; i < symbol.getFormals().size(); i++) {
                var argType = functionCall.args.get(i).accept(this);
                var formalSymbol = (IdSymbol) symbol.getFormals().get(keys[i]);

                if (!verifyIfAncestor(argType, formalSymbol.getType())) {
                    SymbolTable.error(functionCall.ctx, functionCall.args.get(i).token, "In call to method " + functionCall.name.token.getText() +
                            " of class " + callerType.getName() + ", actual type " + argType.getName() + " of formal parameter " +
                            formalSymbol.getName() + " is incompatible with declared type " + formalSymbol.getType().getName());
                }
            }

            functionCall.callerType = callerType;

            if (symbol.getType().getName().startsWith("SELF_TYPE")) {
                return actualCallerType;
            }

            return symbol.getType();
        }

        return null;
    }

    @Override
    public ClassSymbol visit(If iff) {
        var condType = iff.cond.accept(this);
        var thenBranch = iff.ifBranch.accept(this);
        var elseBranch = iff.elseBranch.accept(this);

        if (condType != null && !condType.getName().equals("Bool")) {
            String condTypeStr = condType.getName();

            if (condTypeStr.startsWith("SELF_TYPE")) {
                condTypeStr = "SELF_TYPE";
            }

            SymbolTable.error(iff.ctx, iff.cond.token, "If condition has type " + condTypeStr + " instead of Bool");
        }

        if (thenBranch != null && elseBranch != null) {
            return leastUpperBound(thenBranch, elseBranch);
        }

        return null;
    }

    @Override
    public ClassSymbol visit(While wwhile) {
        var condType = wwhile.cond.accept(this);

        if (condType != null && !condType.getName().equals("Bool")) {
            String condTypeStr = condType.getName();

            if (condTypeStr.startsWith("SELF_TYPE")) {
                condTypeStr = "SELF_TYPE";
            }

            SymbolTable.error(wwhile.ctx, wwhile.cond.token, "While condition has type " + condTypeStr + " instead of Bool");
        }

        return (ClassSymbol) SymbolTable.globals.idLookup("Object");
    }

    @Override
    public ClassSymbol visit(Local local) {
        ClassSymbol type = local.name.accept(this);
        ClassSymbol exprType = null;

        if (local.assign != null) {
            exprType = local.assign.accept(this);
        }

        if (exprType != null && type != null && !verifyIfAncestor(exprType, type)) {
            String exprTypeStr = exprType.getName();
            String typeStr = type.getName();

            if (exprTypeStr.startsWith("SELF_TYPE")) {
                exprTypeStr = "SELF_TYPE";
            }

            if (typeStr.startsWith("SELF_TYPE")) {
                typeStr = "SELF_TYPE";
            }

            SymbolTable.error(local.ctx, local.assign.token, "Type " + exprTypeStr + " of initialization expression " +
                    "of identifier " + local.name.token.getText() + " is incompatible with declared type " + typeStr);
            return null;
        }

        return type;
    }

    @Override
    public ClassSymbol visit(Let let) {
        for (Local local: let.locals) {
            local.accept(this);
        }

        return let.e.accept(this);
    }

    @Override
    public ClassSymbol visit(Case ccase) {
        ClassSymbol finalType = ccase.results.get(0).accept(this);

        for (int i = 1; i < ccase.branchNames.size(); i++) {
            ClassSymbol type = ccase.results.get(i).accept(this);
            finalType = leastUpperBound(finalType, type);
        }

        return finalType;
    }

    @Override
    public ClassSymbol visit(CodeBlock codeBlock) {
        for (int i = 0; i < codeBlock.e.size() - 1; i++) {
            codeBlock.e.get(i).accept(this);
        }

        return codeBlock.e.get(codeBlock.e.size() - 1).accept(this);
    }
}
