package cool.compiler;

public interface ASTVisitor<T> {
    T visit(Program program);
    T visit(Class coolClass);
    T visit(Formal formal);
    T visit(Attribute attribute);
    T visit(Method method);
    T visit(Id id);
    T visit(CoolInteger integer);
    T visit(CoolString string);
    T visit(CoolTrue coolTrue);
    T visit(CoolFalse coolFalse);
    T visit(BinaryOp binaryOp);
    T visit(NegativeInt negativeInt);
    T visit(Paren paren);
    T visit(Not nnot);
    T visit(Assign assign);
    T visit(New nnew);
    T visit(IsVoid isVoid);
    T visit(FunctionCall functionCall);
    T visit(If iff);
    T visit(While wwhile);
    T visit(Local local);
    T visit(Let let);
    T visit(Case ccase);
    T visit(CodeBlock codeBlock);
}
