package cool.compiler;

import cool.structures.*;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class ClassPassVisitor implements ASTVisitor<Void>{
    Scope crtScope = SymbolTable.globals;

    @Override
    public Void visit(Program program) {
        for (Class classes: program.childClasses) {
            classes.accept(this);
        }

        return null;
    }

    @Override
    public Void visit(Class coolClass) {
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
    public Void visit(Formal formal) {
        return null;
    }

    @Override
    public Void visit(Attribute attribute) {
        var name = attribute.name.token;
        var type = attribute.type;

        // Nu avem voie sa avem un atribut cu numele self, asa ca nu mai afisam eroare deoarece am facut-o mai devreme
        if (!name.getText().equals("self") && crtScope.getParent().idLookup(name.getText()) != null) {
            SymbolTable.error(attribute.ctx, name, "Class " + ((ClassSymbol) crtScope).getName() + " redefines inherited attribute " + name.getText());
            return null;
        }

        if (attribute.assign != null) {
            attribute.assign.accept(this);
        }

        return null;
    }

    @Override
    public Void visit(Method method) {
        var name = method.name.token;
        var type = method.type;
        var sym = (FunctionSymbol) crtScope.funcLookup(name.getText());
        var overridedFunc = (FunctionSymbol) crtScope.getParent().funcLookup(name.getText());

        if (overridedFunc != null) {
            LinkedHashMap<String, Symbol> args = (LinkedHashMap<String, Symbol>) overridedFunc.getFormals();
            String[] keys = args.keySet().toArray(new String[0]);

            if (overridedFunc.getFormals().size() != method.args.size()) {
                SymbolTable.error(method.ctx, name, "Class " + ((ClassSymbol) crtScope).getName() + " overrides method " +
                        name.getText() + " with different number of formal parameters");
                return null;
            }

            for (int i = 0; i < method.args.size(); i++) {
                if (!((IdSymbol)args.get(keys[i])).getType().getName().equals(method.args.get(i).type.getText())) {
                    SymbolTable.error(method.ctx, method.args.get(i).type, "Class " + ((ClassSymbol) crtScope).getName() + " overrides method " +
                            name.getText() + " but changes type of formal parameter " + method.args.get(i).name.token.getText() + " from " +
                            ((IdSymbol)args.get(keys[i])).getType().getName() + " to " + method.args.get(i).type.getText());
                }
            }

            if (!overridedFunc.getType().getName().equals(type.getText()) && !overridedFunc.getType().getName().startsWith("SELF_TYPE")) {
                SymbolTable.error(method.ctx, type, "Class " + ((ClassSymbol) crtScope).getName() + " overrides method " +
                        name.getText() + " but changes return type from " + overridedFunc.getType().getName() + " to " + type.getText());
                return null;
            }
        }

        crtScope = sym;
        method.e.accept(this);
        crtScope = sym.getParent();

        return null;
    }

    @Override
    public Void visit(Id id) {
        var symbol = (IdSymbol)crtScope.idLookup(id.token.getText());

        id.setScope(crtScope);
        id.setSymbol(symbol);

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
        binaryOp.rhs.accept(this);
        binaryOp.lhs.accept(this);
        return null;
    }

    @Override
    public Void visit(NegativeInt negativeInt) {
        negativeInt.e.accept(this);
        return null;
    }

    @Override
    public Void visit(Paren paren) {
        paren.e.accept(this);
        return null;
    }

    @Override
    public Void visit(Not nnot) {
        nnot.e.accept(this);
        return null;
    }

    @Override
    public Void visit(Assign assign) {
        assign.name.accept(this);
        assign.e.accept(this);
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
        functionCall.name.accept(this);

        if (functionCall.e != null) {
            functionCall.e.accept(this);
        }

        for (Expression expression: functionCall.args) {
            expression.accept(this);
        }

        return null;
    }

    @Override
    public Void visit(If iff) {
        iff.cond.accept(this);
        iff.ifBranch.accept(this);
        iff.elseBranch.accept(this);
        return null;
    }

    @Override
    public Void visit(While wwhile) {
        wwhile.cond.accept(this);
        wwhile.loopExpr.accept(this);
        return null;
    }

    @Override
    public Void visit(Local local) {
        if (local.assign != null) {
            local.assign.accept(this);
        }
        return null;
    }

    @Override
    public Void visit(Let let) {
        Scope oldScope = crtScope;

        for (Local local: let.locals) {
            crtScope = local.name.getScope().getParent();
            local.accept(this);
        }

        crtScope = let.locals.get(let.locals.size() - 1).name.getScope();
        let.e.accept(this);
        crtScope = oldScope;

        return null;
    }

    @Override
    public Void visit(Case ccase) {
        for (int i = 0; i < ccase.branchNames.size(); i++) {
            crtScope = ccase.branchNames.get(i).getScope();
            ccase.results.get(i).accept(this);
        }

        return null;
    }

    @Override
    public Void visit(CodeBlock codeBlock) {
        for (int i = 0; i < codeBlock.e.size(); i++) {
            codeBlock.e.get(i).accept(this);
        }

        return null;
    }
}
