package cool.compiler;

import cool.structures.ClassSymbol;
import cool.structures.Symbol;
import cool.structures.SymbolTable;

public class ClassResPassVisitor implements ASTVisitor<Void>{

    @Override
    public Void visit(Program program) {
        for (var childClass: program.childClasses) {
            ClassSymbol sym = (ClassSymbol) SymbolTable.globals.idLookup(childClass.type.getText());

            if (childClass.inherited != null) {
                String inherited = childClass.inherited.getText();

                if (inherited.equals("Int") || inherited.equals("String") || inherited.equals("Bool") || inherited.equals("SELF_TYPE")) {
                    SymbolTable.error(childClass.ctx, childClass.inherited, "Class " + childClass.type.getText() + " has illegal parent " + inherited);
                } else if (SymbolTable.globals.idLookup(inherited) == null) {
                    SymbolTable.error(childClass.ctx, childClass.inherited, "Class " + childClass.type.getText() + " has undefined parent " + inherited);
                } else {
                    ClassSymbol parentSymbol = (ClassSymbol) SymbolTable.globals.idLookup(inherited);
                    sym.setParent(parentSymbol);
                }
            }
        }

        for (var childClass: program.childClasses) {
            ClassSymbol sym = (ClassSymbol) SymbolTable.globals.idLookup(childClass.type.getText());

            if (ClassSymbol.classesInCycle.contains(sym)) {
                SymbolTable.error(childClass.ctx, childClass.type, "Inheritance cycle for class " + sym.getName());
            }
        }

        return null;
    }

    @Override
    public Void visit(Class coolClass) {
        return null;
    }

    @Override
    public Void visit(Formal formal) {
        return null;
    }

    @Override
    public Void visit(Attribute attribute) {
        return null;
    }

    @Override
    public Void visit(Method method) {
        return null;
    }

    @Override
    public Void visit(Id id) {
        return null;
    }

    @Override
    public Void visit(CoolInteger integer) {
        return null;
    }

    @Override
    public Void visit(CoolString string) {
        return null;
    }

    @Override
    public Void visit(CoolTrue coolTrue) {
        return null;
    }

    @Override
    public Void visit(CoolFalse coolFalse) {
        return null;
    }

    @Override
    public Void visit(BinaryOp binaryOp) {
        return null;
    }

    @Override
    public Void visit(NegativeInt negativeInt) {
        return null;
    }

    @Override
    public Void visit(Paren paren) {
        return null;
    }

    @Override
    public Void visit(Not nnot) {
        return null;
    }

    @Override
    public Void visit(Assign assign) {
        return null;
    }

    @Override
    public Void visit(New nnew) {
        return null;
    }

    @Override
    public Void visit(IsVoid isVoid) {
        return null;
    }

    @Override
    public Void visit(FunctionCall functionCall) {
        return null;
    }

    @Override
    public Void visit(If iff) {
        return null;
    }

    @Override
    public Void visit(While wwhile) {
        return null;
    }

    @Override
    public Void visit(Local local) {
        return null;
    }

    @Override
    public Void visit(Let let) {
        return null;
    }

    @Override
    public Void visit(Case ccase) {
        return null;
    }

    @Override
    public Void visit(CodeBlock codeBlock) {
        return null;
    }
}
