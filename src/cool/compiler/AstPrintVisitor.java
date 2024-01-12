package cool.compiler;

public class AstPrintVisitor implements ASTVisitor<Void> {
    int indent = 0;

    void printIndent(String str) {
        for (int i = 0; i < indent; i++)
            System.out.print("  ");
        System.out.println(str);
    }

    @Override
    public Void visit(Program program) {
        printIndent("program");
        indent++;

        for (Class childClass: program.childClasses) {
            childClass.accept(this);
        }

        indent--;
        return null;
    }

    @Override
    public Void visit(Class coolClass) {
        printIndent("class");
        indent++;
        printIndent(coolClass.type.getText());

        if (coolClass.inherited != null)
            printIndent(coolClass.inherited.getText());

        for (Feature feature: coolClass.features) {
            feature.accept(this);
        }

        indent--;
        return null;
    }

    @Override
    public Void visit(Formal formal) {
        printIndent("formal");
        indent++;
        printIndent(formal.name.token.getText());
        printIndent(formal.type.getText());
        indent--;
        return null;
    }

    @Override
    public Void visit(Attribute attribute) {
        printIndent("attribute");
        indent++;

        printIndent(attribute.name.token.getText());
        printIndent(attribute.type.getText());

        if (attribute.assign != null)
            attribute.assign.accept(this);

        indent--;
        return null;
    }

    @Override
    public Void visit(Method method) {
        printIndent("method");
        indent++;

        printIndent(method.name.token.getText());

        for (Formal formal: method.args) {
            formal.accept(this);
        }

        printIndent(method.type.getText());
        method.e.accept(this);

        indent--;
        return null;
    }

    @Override
    public Void visit(Id id) {
        printIndent(id.token.getText());
        return null;
    }

    @Override
    public Void visit(CoolInteger integer) {
        printIndent(integer.token.getText());
        return null;
    }

    @Override
    public Void visit(CoolString string) {
        printIndent(string.token.getText());
        return null;
    }

    @Override
    public Void visit(CoolTrue coolTrue) {
        printIndent(coolTrue.token.getText());
        return null;
    }

    @Override
    public Void visit(CoolFalse coolFalse) {
        printIndent(coolFalse.token.getText());
        return null;
    }

    @Override
    public Void visit(BinaryOp binaryOp) {
        printIndent(binaryOp.op.getText());
        indent++;
        binaryOp.lhs.accept(this);
        binaryOp.rhs.accept(this);
        indent--;
        return null;
    }

    @Override
    public Void visit(NegativeInt negativeInt) {
        printIndent("~");
        indent++;
        negativeInt.e.accept(this);
        indent--;
        return null;
    }

    @Override
    public Void visit(Paren paren) {
        paren.e.accept(this);
        return null;
    }

    @Override
    public Void visit(Not nnot) {
        printIndent("not");
        indent++;
        nnot.e.accept(this);
        indent--;
        return null;
    }

    @Override
    public Void visit(Assign assign) {
        printIndent("<-");
        indent++;
        printIndent(assign.token.getText());
        assign.e.accept(this);
        indent--;
        return null;
    }

    @Override
    public Void visit(New nnew) {
        printIndent("new");
        indent++;
        printIndent(nnew.type.getText());
        indent--;
        return null;
    }

    @Override
    public Void visit(IsVoid isVoid) {
        printIndent("isvoid");
        indent++;
        isVoid.e.accept(this);
        indent--;
        return null;
    }

    @Override
    public Void visit(FunctionCall functionCall) {
        if (functionCall.e != null) {
            printIndent(".");
        } else {
            printIndent("implicit dispatch");
        }

        indent++;

        if (functionCall.e != null) {
            functionCall.e.accept(this);
        }

        if (functionCall.dispatchType != null) {
            printIndent(functionCall.dispatchType.getText());
        }

        printIndent(functionCall.name.token.getText());

        for (Expression expression: functionCall.args) {
            expression.accept(this);
        }

        indent--;

        return null;
    }

    @Override
    public Void visit(If iff) {
        printIndent("if");
        indent++;

        iff.cond.accept(this);
        iff.ifBranch.accept(this);
        iff.elseBranch.accept(this);

        indent--;
        return null;
    }

    @Override
    public Void visit(While wwhile) {
        printIndent("while");
        indent++;

        wwhile.cond.accept(this);
        wwhile.loopExpr.accept(this);

        indent--;
        return null;
    }

    @Override
    public Void visit(Let let) {
        printIndent("let");
        indent++;

        for (Attribute local: let.locals) {
            local.accept(this);
        }

        let.e.accept(this);

        indent--;
        return null;
    }

    @Override
    public Void visit(Local local) {
        printIndent("local");
        indent++;
        printIndent(local.name.token.getText());
        printIndent(local.type.getText());

        if (local.assign != null)
            local.assign.accept(this);
        indent--;
        return null;
    }

    @Override
    public Void visit(Case ccase) {
        printIndent("case");
        indent++;

        ccase.e.accept(this);

        for (int i = 0; i < ccase.branchNames.size(); i++) {
            printIndent("case branch");
            indent++;
            printIndent(ccase.branchNames.get(i).token.getText());
            printIndent(ccase.branchTypes.get(i).getText());
            ccase.results.get(i).accept(this);
            indent--;
        }

        indent--;
        return null;
    }

    @Override
    public Void visit(CodeBlock codeBlock) {
        printIndent("block");
        indent++;

        for (Expression expression: codeBlock.e) {
            expression.accept(this);
        }

        indent--;
        return null;
    }
}
