package cool.structures;

import java.util.*;

public class DefaultScope implements Scope {

    protected Map<String, Symbol> idSymbols = new LinkedHashMap<>();
    protected Map<String, Symbol> funcSymbols = new LinkedHashMap<>();

    private Scope parent;

    public DefaultScope(Scope parent) {
        this.parent = parent;
    }

    @Override
    public boolean add(Symbol sym) {
        // Reject duplicates in the same scope.
        if (idSymbols.containsKey(sym.getName()))
            return false;

        idSymbols.put(sym.getName(), sym);

        return true;
    }

    @Override
    public Symbol idLookup(String name) {
        var sym = idSymbols.get(name);

        if (sym != null)
            return sym;

        if (parent != null)
            return parent.idLookup(name);

        return null;
    }

    @Override
    public Symbol funcLookup(String name) {
        var sym = funcSymbols.get(name);

        if (sym != null)
            return sym;

        if (parent != null)
            return parent.funcLookup(name);

        return null;
    }

    @Override
    public Scope getParent() {
        return parent;
    }

    @Override
    public String toString() {
        return idSymbols.values().toString();
    }

    public Map<String, Symbol> getIdSymbols() {
        return idSymbols;
    }
}
