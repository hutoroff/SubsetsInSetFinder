package ru.hutoroff.subset.finder;

import ru.hutoroff.subset.exception.WordFinderException;

import java.util.Collection;

/**
 * Created by hutoroff on 19.02.17.
 */
public interface WordsFromFileFinder {
    Collection<String> findSubsetsFromWord() throws WordFinderException;
}
