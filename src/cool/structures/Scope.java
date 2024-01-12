package cool.structures;

public interface Scope {
    public boolean add(Symbol sym);

    public Symbol idLookup(String str);

    public Symbol funcLookup(String str);

    public Scope getParent();
}
