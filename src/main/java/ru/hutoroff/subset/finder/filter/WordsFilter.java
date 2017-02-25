package ru.hutoroff.subset.finder.filter;

/**
 * Created by hutoroff on 25.02.17.
 */
public abstract class WordsFilter {
    protected final String targetWord;

    protected WordsFilter(String targetWord) {
        this.targetWord = targetWord != null ? targetWord.toLowerCase() : null;
    }

    public abstract boolean doFilter(String currentWord);
}
