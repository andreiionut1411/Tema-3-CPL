package cool.structures;

import org.antlr.v4.runtime.misc.Pair;

import java.util.*;

public class ClassSymbol extends Symbol implements Scope{
    protected ClassSymbol parent;
    protected Map<String, Symbol> idSymbols = new LinkedHashMap<>();
    protected Map<String, Symbol> funcSymbols = new LinkedHashMap<>();
    public static ArrayList<ClassSymbol> classesInCycle = new ArrayList<>();
    public static final ClassSymbol Object = new ClassSymbol("Object");
    public HashMap<String, Pair<Integer, String>> functionOffsetTable = new LinkedHashMap<>(); // function name -> (offset, original class name)
    public HashMap<String, Pair<Integer, String>> attrOffsetTable = new LinkedHashMap<>(); // attribute name -> (offset, type of attribute)
    public int numberOfPrevAttributes = 3;

    public ClassSymbol(String name) {
        super(name);
        parent = ClassSymbol.Object;
        IdSymbol selfSymbol = new IdSymbol("self");
        idSymbols.put("self", selfSymbol);
        selfSymbol.setType(this);
    }

    @Override
    public boolean add(Symbol sym) {
        if (sym instanceof FunctionSymbol) {
            if (funcSymbols.containsKey(sym.getName()))
                return false;

            funcSymbols.put(sym.getName(), sym);
        } else {
            if (idSymbols.containsKey(sym.getName()))
                return false;

            idSymbols.put(sym.getName(), sym);
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

    public void setParent(ClassSymbol parent) {
        if (parent ==  ClassSymbol.Object) {
            this.parent = parent;
            return;
        }

        for (ClassSymbol classSymbol: classesInCycle) {
            if (parent.getName().equals(classSymbol.getName())) {
                this.parent = parent;
                return;
            }
        }

        ArrayList<ClassSymbol> parents = new ArrayList<>();
        ClassSymbol crtNode = parent;

        while (parent != null) {
            parents.add(parent);

            // If we found a cycle, we remember it
            if (parent.getName().equals(name)) {
                classesInCycle.addAll(parents);
                return;
            }

            parent = (ClassSymbol) parent.getParent();
        }

        this.parent = crtNode;
    }

    public Map<String, Symbol> getFuncSymbols() {
        return funcSymbols;
    }

    public Map<String, Symbol> getIdSymbols() {
        return idSymbols;
    }

    public void populateOffsetTables() {
        ClassSymbol crtClass = this;
        LinkedList<String> attrTypes = new LinkedList<>();
        LinkedList<String> attrList = new LinkedList<>();
        LinkedList<String> classList = new LinkedList<>();
        LinkedList<String> funcList = new LinkedList<>();
        Set<String> definedMethods = new HashSet<>();
        int offset = 12; // The first 12 bytes are reserved for other things in the class

        while (true) {
            var funcSymbols = crtClass.getFuncSymbols();
            var attrSymbols = crtClass.getIdSymbols();
            ArrayList<String> funcNames = new ArrayList<>(funcSymbols.keySet());
            ArrayList<String> attrNames = new ArrayList<>(attrSymbols.keySet());
            Collections.reverse(funcNames);
            Collections.reverse(attrNames);

            for (var func: funcNames) {
                if (definedMethods.contains(funcSymbols.get(func).getName())) {
                    // In case a parent has a function that is overloaded below, then we add that function at the same offset
                    for (int i = 0; i < classList.size(); i++) {
                        if (funcList.get(i).equals(func)) {
                            funcList.remove(i);
                            String functionClass = classList.remove(i);
                            funcList.add(func);
                            classList.add(functionClass);
                            break;
                        }
                    }
                    continue;
                }

                definedMethods.add(funcSymbols.get(func).getName());
                funcList.add(funcSymbols.get(func).getName());
                classList.add(crtClass.name);
            }

            for (var attr: attrNames) {
                if (attrSymbols.get(attr).getName().equals("self")) {
                    continue;
                }

                attrTypes.add(((IdSymbol)attrSymbols.get(attr)).getType().getName());
                attrList.add(attrSymbols.get(attr).getName());

                if (!crtClass.getName().equals(this.name)) {
                    numberOfPrevAttributes++;
                }
            }

            if (crtClass.getName().equals("Object")) {
                break;
            }

            crtClass = (ClassSymbol) crtClass.getParent();
        }

        for (int i = attrTypes.size() - 1; i >= 0; i--) {
            String type = attrTypes.get(i);
            String attrName = attrList.get(i);

            attrOffsetTable.put(attrName, new Pair<>(offset, type));
            offset += 4;
        }

        offset = 0;

        for (int i = funcList.size() - 1; i >= 0; i--) {
            functionOffsetTable.put(funcList.get(i), new Pair<>(offset, classList.get(i)));
            offset += 4;
        }
    }
}
