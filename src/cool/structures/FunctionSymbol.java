package cool.structures;

import java.util.*;

// O functie este atât simbol, cât și domeniu de vizibilitate pentru parametrii
// săi formali.
public class FunctionSymbol extends IdSymbol implements Scope {

    // LinkedHashMap reține ordinea adăugării.
    protected Map<String, Symbol> idSymbols = new LinkedHashMap<>();
    protected Map<String, Symbol> funcSymbols = new LinkedHashMap<>();

    protected Scope parent;

    public FunctionSymbol(Scope parent, String name) {
        super(name);
        this.parent = parent;
    }

    @Override
    public boolean add(Symbol sym) {
        if (sym instanceof IdSymbol) {
            if (idSymbols.containsKey(sym.getName()))
                return false;

            idSymbols.put(sym.getName(), sym);
        } else {
            if (funcSymbols.containsKey(sym.getName()))
                return false;

            funcSymbols.put(sym.getName(), sym);
        }

        return true;
    }

    @Override
    public Symbol idLookup(String s) {
        var sym = idSymbols.get(s);

        if (sym != null)
            return sym;

        if (parent != null)
            return parent.idLookup(s);

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



    public Map<String, Symbol> getFormals() {
        return idSymbols;
    }
}