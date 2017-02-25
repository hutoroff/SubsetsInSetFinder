package ru.hutoroff.subset.finder.impl;

import ru.hutoroff.subset.exception.WordFinderException;
import ru.hutoroff.subset.finder.filter.WordsFilter;
import ru.hutoroff.subset.finder.WordsFromFileFinder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by hutoroff on 25.02.17.
 */
public class WordsFromFileFinderFilterBased implements WordsFromFileFinder {
    private final Path pathToDict;
    private WordsFilter wordsFilter;
    private List<String> result;

    private WordsFromFileFinderFilterBased(Path pathToDict, WordsFilter wordsFilter) {
        this.pathToDict = pathToDict;
        this.wordsFilter = wordsFilter;
    }

    public WordsFromFileFinderFilterBased(String pathToDict, WordsFilter wordsFilter) {
        this(Paths.get(pathToDict), wordsFilter);
    }

    public void setWordsFilter(WordsFilter wordsFilter) {
        this.wordsFilter = wordsFilter;
    }

    @Override
    public Collection<String> findSubsetsFromWord() throws WordFinderException {
        if(this.result == null)
            try {
                this.result = Files.lines(this.pathToDict).filter(wordsFilter::doFilter).collect(Collectors.toCollection(LinkedList::new));
            } catch (IOException e) {
                throw new WordFinderException("Error on reading words from dict. Caused by: ", e);
            }
        return this.result;
    }
}
