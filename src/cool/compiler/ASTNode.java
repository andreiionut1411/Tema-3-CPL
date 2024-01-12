package cool.compiler;

import cool.parser.CoolParser;
import cool.structures.ClassSymbol;
import cool.structures.IdSymbol;
import cool.structures.Scope;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.SortedMap;

public abstract class ASTNode {
    Token token;
    ParserRuleContext ctx;

    ASTNode(Token token, ParserRuleContext context) {
        this.token = token;
        this.ctx = context;
    }

    public <T> T accept(ASTVisitor<T> visitor) {
        return null;
    }
}

class Program extends ASTNode {
    ArrayList<Class> childClasses;

    public Program(Token token, ArrayList<Class> childClasses, CoolParser.ProgramContext context) {
        super(token, context);
        this.childClasses = childClasses;
    }

    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}

class Class extends ASTNode {
    Token type;
    Token inherited;
    ArrayList<Feature> features;

    public Class(Token token, Token type, Token inherited, ArrayList<Feature> features, ParserRuleContext ctx) {
        super(token, ctx);
        this.type = type;
        this.inherited = inherited;
        this.features = features;
    }

    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}

class Formal extends ASTNode {
    Id name;
    Token type;

    public Formal(Token token, Id name, Token type, ParserRuleContext ctx) {
        super(token, ctx);
        this.name = name;
        this.type = type;
    }

    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}

abstract class Feature extends ASTNode {
    public Feature(Token token, ParserRuleContext ctx) {
        super(token, ctx);
    }
}

class Attribute extends Feature {
    Id name;
    Token type;
    Expression assign;
    int offset = 0;

    public Attribute(Token token, Id name, Token type, Expression assign, ParserRuleContext ctx) {
        super(token, ctx);
        this.name = name;
        this.type = type;
        this.assign = assign;
    }

    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }
}

class Method extends Feature {
    Id name;
    Token type;
    Expression e;
    ArrayList<Formal> args;

    public Method(Token token, Id name, Token type, Expression e, ArrayList<Formal> args, ParserRuleContext ctx) {
        super(token, ctx);
        this.name = name;
        this.type = type;
        this.e = e;
        this.args = args;
    }

    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}

abstract class Expression extends ASTNode {
    public Expression(Token token, ParserRuleContext ctx) {
        super(token, ctx);
    }
}

class Id extends Expression {
    private IdSymbol symbol;
    private Scope scope;

    public Id(Token token, ParserRuleContext ctx) {
        super(token, ctx);
    }

    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }

    IdSymbol getSymbol() {
        return symbol;
    }

    void setSymbol(IdSymbol symbol) {
        this.symbol = symbol;
    }

    Scope getScope() {
        return scope;
    }

    void setScope(Scope scope) {
        this.scope = scope;
    }
}

class CoolInteger extends Expression {
    public CoolInteger(Token token, ParserRuleContext ctx) {
        super(token, ctx);
    }

    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}

class CoolString extends Expression {
    public CoolString(Token token, ParserRuleContext ctx) {
        super(token, ctx);
    }

    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}

class CoolTrue extends Expression {
    public CoolTrue(Token token, ParserRuleContext ctx) {
        super(token, ctx);
    }

    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}

class CoolFalse extends Expression {
    public CoolFalse(Token token, ParserRuleContext ctx) {
        super(token, ctx);
    }

    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}

class BinaryOp extends Expression {
    Expression lhs;
    Expression rhs;
    Token op;

    public BinaryOp(Token token, Expression lhs, Expression rhs, Token op, ParserRuleContext ctx) {
        super(token, ctx);
        this.lhs = lhs;
        this.rhs = rhs;
        this.op = op;
    }

    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}

class NegativeInt extends Expression {
    Expression e;

    public NegativeInt(Token token, Expression e, ParserRuleContext ctx) {
        super(token, ctx);
        this.e = e;
    }

    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}

class Paren extends Expression {
    Expression e;

    public Paren(Token token, Expression e, ParserRuleContext ctx) {
        super(token, ctx);
        this.e = e;
    }

    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}

class Not extends Expression {
    Expression e;

    public Not(Token token, Expression e, ParserRuleContext ctx) {
        super(token, ctx);
        this.e = e;
    }

    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}

class Assign extends Expression {
    Id name;
    Expression e;

    public Assign(Token token, Expression e, Id name, ParserRuleContext ctx) {
        super(token, ctx);
        this.e = e;
        this.name = name;
    }
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}

class New extends Expression {
    Token type;
    Scope crtScope;

    public New(Token token, Token type, ParserRuleContext ctx) {
        super(token, ctx);
        this.type = type;
    }

    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }

    public Scope getCrtScope() {
        return crtScope;
    }

    public void setCrtScope(Scope crtScope) {
        this.crtScope = crtScope;
    }
}

class IsVoid extends Expression {
    Expression e;

    public IsVoid(Token token, Expression e, ParserRuleContext ctx) {
        super(token, ctx);
        this.e = e;
    }

    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}

class FunctionCall extends Expression {
    // Expression e is null in the case of an implicit function call, and dispatch type is null
    // if no other Type of dispatch is mentioned.
    Expression e;
    Token dispatchType;
    Id name;
    ClassSymbol callerType = null;
    ArrayList<Expression> args;

    public FunctionCall(Token token, Expression e, Token dispatchType, Id name, ArrayList<Expression> args, ParserRuleContext ctx) {
        super(token, ctx);
        this.e = e;
        this.dispatchType = dispatchType;
        this.name = name;
        this.args = args;
    }

    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}

class If extends Expression {
    Expression cond;
    Expression ifBranch;
    Expression elseBranch;

    public If(Token token, Expression cond, Expression ifBranch, Expression elseBranch, ParserRuleContext ctx) {
        super(token, ctx);
        this.cond = cond;
        this.ifBranch = ifBranch;
        this.elseBranch = elseBranch;
    }

    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}

class While extends Expression {
    Expression cond;
    Expression loopExpr;

    public While(Token token, Expression cond, Expression loopExpr, ParserRuleContext ctx) {
        super(token, ctx);
        this.cond = cond;
        this.loopExpr = loopExpr;
    }

    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}

class Local extends Attribute {
    public Local(Token token, Id name, Token type, Expression assign, ParserRuleContext ctx) {
        super(token, name, type, assign, ctx);
    }

    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}

class Let extends Expression {
    Expression e;
    ArrayList<Local> locals;

    public Let(Token token, Expression e, ArrayList<Local> locals, ParserRuleContext ctx) {
        super(token, ctx);
        this.e = e;
        this.locals = locals;
    }

    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}

class Case extends Expression {
    Expression e;
    ArrayList<Id> branchNames;
    ArrayList<Token> branchTypes;
    ArrayList<Expression> results;

    public Case(Token token, Expression e, ArrayList<Id> branchNames, ArrayList<Token> branchTypes, ArrayList<Expression> results, ParserRuleContext ctx) {
        super(token, ctx);
        this.e = e;
        this.branchNames = branchNames;
        this.branchTypes = branchTypes;
        this.results = results;
    }

    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}

class CodeBlock extends Expression {
    ArrayList<Expression> e;

    public CodeBlock(Token token, ArrayList<Expression> e, ParserRuleContext ctx) {
        super(token, ctx);
        this.e = e;
    }

    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visit(this);
    }
}