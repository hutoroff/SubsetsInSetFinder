package ru.hutoroff.subset.finder.filter.impl;

import ru.hutoroff.subset.finder.filter.WordsFilter;

/**
 * Created by hutoroff on 25.02.17.
 */
public class WordsFilterPrimitive extends WordsFilter {

    public WordsFilterPrimitive(String targetWord) {
        super(targetWord);
    }

    @Override
    public boolean doFilter(String currentWord) {
        String lWord = this.targetWord;
        for (int i = 0; i < currentWord.length(); i++) {
            int charIdx = lWord.indexOf(currentWord.charAt(i));
            if(charIdx > -1)
                lWord = lWord.substring(0,charIdx) + lWord.substring(charIdx + 1);
            else
                return false;
        }
        return true;
    }
}
