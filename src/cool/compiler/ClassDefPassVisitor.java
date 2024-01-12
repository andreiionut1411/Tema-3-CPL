package cool.compiler;

import cool.compiler.*;
import cool.compiler.*;
import cool.structures.*;

public class ClassDefPassVisitor implements ASTVisitor<Void> {
    @Override
    public Void visit(Program program) {
        for (var childClass: program.childClasses) {
            if (childClass.type.getText().equals("SELF_TYPE")) {
                SymbolTable.error(childClass.ctx, childClass.type, "Class has illegal name SELF_TYPE");
            }

            // Pentru scope-ul global consideram numele de clase ID-uri.
            Symbol sym = SymbolTable.globals.idLookup(childClass.type.getText());

            if (sym != null) {
                SymbolTable.error(childClass.ctx, childClass.type, "Class " + childClass.type.getText() + " is redefined");
            }

            ClassSymbol classSymbol = new ClassSymbol(childClass.type.getText());
            ClassSymbol selfType = new SelfTypeClass("SELF_TYPE_" + childClass.type.getText(), classSymbol);
            SymbolTable.globals.add(classSymbol);
            SymbolTable.globals.add(selfType);
        }

        return null;
    }

    @Override
    public Void visit(cool.compiler.Class coolClass) {
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
