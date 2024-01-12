package cool.structures;

public class SelfTypeClass extends ClassSymbol{
    private ClassSymbol concreteSelfClass;

    public SelfTypeClass(String name, ClassSymbol classSymbol) {
        super(name);
        concreteSelfClass = classSymbol;
        this.parent = classSymbol;
    }

    public ClassSymbol getConcreteSelfClass() {
        return concreteSelfClass;
    }
}
