package ru.hutoroff.subset.finder.helper;

import java.util.HashMap;

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
        symbols.replace(c, curVal - 1);
        return true;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return new WordStructure(this);
    }
}
