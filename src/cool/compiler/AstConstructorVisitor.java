package cool.compiler;

import cool.parser.CoolParser;
import cool.parser.CoolParserBaseVisitor;
import org.antlr.v4.runtime.Token;

import java.util.ArrayList;

public class AstConstructorVisitor extends CoolParserBaseVisitor<ASTNode> {
    @Override
    public ASTNode visitProgram(CoolParser.ProgramContext ctx) {
        ArrayList<Class> classes = new ArrayList<>();

        for (CoolParser.ClassContext childClass: ctx.cls) {
            classes.add((Class) visit(childClass));
        }

        return new Program(ctx.start, classes, ctx);
    }

    @Override
    public ASTNode visitClass_declaration(CoolParser.Class_declarationContext ctx) {
        ArrayList<Feature> features = new ArrayList<>();

        for (CoolParser.FeatureContext feature: ctx.features) {
            features.add((Feature) visit(feature));
        }

        return new Class(ctx.start, ctx.type, ctx.inherited, features, ctx);
    }

    @Override
    public ASTNode visitDef_formal(CoolParser.Def_formalContext ctx) {
        return new Formal(ctx.start, new Id(ctx.name, ctx), ctx.type, ctx);
    }

    @Override
    public ASTNode visitLet_var(CoolParser.Let_varContext ctx) {
        Expression assign = null;
        if (ctx.assign != null) {
            assign = (Expression) visit(ctx.assign);
        }

        return new Local(ctx.start, new Id(ctx.name, ctx), ctx.type, assign, ctx);
    }

    @Override
    public ASTNode visitDef_var(CoolParser.Def_varContext ctx) {
        Expression assign = null;

        if (ctx.assign != null) {
                assign = (Expression) visit(ctx.assign);
        }

        return new Attribute(ctx.start, new Id(ctx.name, ctx), ctx.type, assign, ctx);
    }

    @Override
    public ASTNode visitDef_func(CoolParser.Def_funcContext ctx) {
        ArrayList<Formal> args = new ArrayList<>();

        for (CoolParser.FormalContext formal: ctx.args) {
            args.add((Formal) visit(formal));
        }

        return new Method(ctx.start, new Id(ctx.name, ctx), ctx.type, (Expression) visit(ctx.e), args, ctx);
    }

    @Override
    public ASTNode visitId(CoolParser.IdContext ctx) {
        return new Id(ctx.ID().getSymbol(), ctx);
    }

    @Override
    public ASTNode visitInt(CoolParser.IntContext ctx) {
        return new CoolInteger(ctx.INTEGER().getSymbol(), ctx);
    }

    @Override
    public ASTNode visitStr(CoolParser.StrContext ctx) {
        return new CoolString(ctx.STRING().getSymbol(), ctx);
    }

    @Override
    public ASTNode visitTrue(CoolParser.TrueContext ctx) {
        return new CoolTrue(ctx.TRUE().getSymbol(), ctx);
    }

    @Override
    public ASTNode visitFalse(CoolParser.FalseContext ctx) {
        return new CoolFalse(ctx.FALSE().getSymbol(), ctx);
    }

    @Override
    public ASTNode visitAdd_sub(CoolParser.Add_subContext ctx) {
        return new BinaryOp(ctx.start, (Expression) visit(ctx.lhs), (Expression) visit(ctx.rhs), ctx.op, ctx);
    }

    @Override
    public ASTNode visitMul_div(CoolParser.Mul_divContext ctx) {
        return new BinaryOp(ctx.start, (Expression) visit(ctx.lhs), (Expression) visit(ctx.rhs), ctx.op, ctx);
    }

    @Override
    public ASTNode visitCompare(CoolParser.CompareContext ctx) {
        return new BinaryOp(ctx.start, (Expression) visit(ctx.lhs), (Expression) visit(ctx.rhs), ctx.op, ctx);
    }

    @Override
    public ASTNode visitNegative_int(CoolParser.Negative_intContext ctx) {
        return new NegativeInt(ctx.start, (Expression) visit(ctx.e), ctx);
    }

    @Override
    public ASTNode visitPar_expr(CoolParser.Par_exprContext ctx) {
        return new Paren(ctx.start, (Expression) visit(ctx.e), ctx);
    }

    @Override
    public ASTNode visitNot(CoolParser.NotContext ctx) {
        return new Not(ctx.start, (Expression) visit(ctx.e), ctx);
    }

    @Override
    public ASTNode visitAssign(CoolParser.AssignContext ctx) {
        return new Assign(ctx.start, (Expression) visit(ctx.e), new Id(ctx.name, ctx), ctx);
    }

    @Override
    public ASTNode visitNew(CoolParser.NewContext ctx) {
        return new New(ctx.start, ctx.type, ctx);
    }

    @Override
    public ASTNode visitIs_void(CoolParser.Is_voidContext ctx) {
        return new IsVoid(ctx.start, (Expression) visit(ctx.e), ctx);
    }

    @Override
    public ASTNode visitFunc_call(CoolParser.Func_callContext ctx) {
        ArrayList<Expression> args = new ArrayList<>();

        for (CoolParser.ExprContext arg: ctx.args) {
            args.add((Expression) visit(arg));
        }

        return new FunctionCall(ctx.start, (Expression) visit(ctx.e), ctx.disp, new Id(ctx.name, ctx), args, ctx);
    }

    @Override
    public ASTNode visitImplicit_func_call(CoolParser.Implicit_func_callContext ctx) {
        ArrayList<Expression> args = new ArrayList<>();

        for (CoolParser.ExprContext arg: ctx.args) {
            args.add((Expression) visit(arg));
        }

        return new FunctionCall(ctx.start, null, null, new Id(ctx.name, ctx), args, ctx);
    }

    @Override
    public ASTNode visitIf(CoolParser.IfContext ctx) {
        return new If(ctx.start, (Expression) visit(ctx.cond), (Expression) visit(ctx.if_branch), (Expression) visit(ctx.else_branch), ctx);
    }

    @Override
    public ASTNode visitWhile(CoolParser.WhileContext ctx) {
        return new While(ctx.start, (Expression) visit(ctx.cond), (Expression) visit(ctx.loop_expr), ctx);
    }

    @Override
    public ASTNode visitLet(CoolParser.LetContext ctx) {
        ArrayList<Local> locals = new ArrayList<>();

        for (CoolParser.Var_definitionContext local: ctx.local) {
            locals.add((Local) visit(local));
        }

        return new Let(ctx.start, (Expression) visit(ctx.e), locals, ctx);
    }

    @Override
    public ASTNode visitCase(CoolParser.CaseContext ctx) {
        ArrayList<Id> branchNames = new ArrayList<>();
        ArrayList<Token> branchTypes = new ArrayList<>();
        ArrayList<Expression> results = new ArrayList<>();

        for (int i = 0; i < ctx.branch_name.size(); i++) {
            branchNames.add(new Id(ctx.branch_name.get(i), ctx));
            branchTypes.add(ctx.branch_type.get(i));
            results.add((Expression) visit(ctx.res.get(i)));
        }

        return new Case(ctx.start, (Expression) visit(ctx.e), branchNames, branchTypes, results, ctx);
    }

    @Override
    public ASTNode visitCode_block(CoolParser.Code_blockContext ctx) {
        ArrayList<Expression> expressions = new ArrayList<>();

        for (CoolParser.ExprContext expr: ctx.e) {
            expressions.add((Expression) visit(expr));
        }

        return new CodeBlock(ctx.start,expressions, ctx);
    }
}
