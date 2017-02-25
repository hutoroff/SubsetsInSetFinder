package ru.hutoroff.subset.finder.filter.impl;

import ru.hutoroff.subset.finder.filter.WordsFilter;
import ru.hutoroff.subset.finder.helper.WordStructure;

/**
 * Created by hutoroff on 25.02.17.
 */
public class WordsFilterStructured extends WordsFilter {

    private final WordStructure wordStructure;

    public WordsFilterStructured(String targetWord) {
        super(targetWord);
        this.wordStructure = new WordStructure(targetWord);
    }

    @Override
    public boolean doFilter(String currentWord) {
        WordStructure ws;
        try {
            ws = (WordStructure) wordStructure.clone();
        } catch (CloneNotSupportedException e) {
            return false;
        }

        for (char c : currentWord.toCharArray()) {
            if(!ws.decrement(c))
                return false;
        }
        return true;
    }
}
