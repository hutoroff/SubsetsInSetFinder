package ru.hutoroff.subset.finder.helper;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Created by hutoroff on 19.02.17.
 */
public class WordStructure implements Cloneable {
    private HashMap<Character, Integer> symbols;

    private WordStructure(HashMap<Character, Integer> symbols) {
        this.symbols = new HashMap<>(symbols);
    }

    private WordStructure(WordStructure wordStructure) {
        this(wordStructure.symbols);
    }

    public WordStructure(String word) {
        symbols = new HashMap<>();
        if(word == null)
            return;

        for (char c : word.toCharArray()) {
            if(!symbols.containsKey(c))
                symbols.put(c, 0);
            symbols.replace(c, symbols.get(c) + 1);
        }
    }

    public boolean decrement(Character c) {
        Integer curVal = symbols.get(c);
        if(curVal == null || curVal < 1)
            return false;
        if(curVal == 1)
            symbols.remove(c);
        else
            symbols.replace(c, curVal - 1);
        return true;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return new WordStructure(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof WordStructure)) return false;
        WordStructure structure = (WordStructure) o;
        return Objects.equals(symbols, structure.symbols);
    }

    @Override
    public int hashCode() {
        return Objects.hash(symbols);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("WordStructure{ ");
        for (Map.Entry<Character, Integer> entry : symbols.entrySet()) {
            sb.append(entry.getKey()).append(":").append(entry.getValue()).append("; ");
        }
        return sb.append("}").toString();
    }
}
