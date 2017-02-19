package ru.hutoroff.subset.finder.impl;

import ru.hutoroff.subset.exception.WordFinderException;
import ru.hutoroff.subset.finder.WordsFromFileFinder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by hutoroff on 19.02.17.
 */
public class WordsFromFileFinderPrimitive implements WordsFromFileFinder {

    private final String targetWord;
    private final Path pathToDict;
    private Set<String> result;

    private WordsFromFileFinderPrimitive(String targetWord, Path pathToDict) {
        this.targetWord = targetWord != null ? targetWord.toLowerCase() : null;
        this.pathToDict = pathToDict;
    }

    public WordsFromFileFinderPrimitive(String targetWord, String pathToDict) {
        this(targetWord, Paths.get(pathToDict));
    }

    @Override
    public Collection<String> findSubsetsFromWord() throws WordFinderException {
        if(this.result == null)
            this.result = findSubsetsFromWord(this.targetWord);
        return this.result;
    }

    private Set<String> findSubsetsFromWord(String targetWord) throws WordFinderException {
        if(targetWord == null)
            return Collections.emptySet();
        try {
            return Files.lines(this.pathToDict).filter(line -> this.filterLinesByWord(line, targetWord)).collect(Collectors.toCollection(HashSet::new));
        } catch (IOException e) {
            throw new WordFinderException("Error on reading dict: " + pathToDict, e);
        }
    }

    private boolean filterLinesByWord(String line, String word) {
        String lWord = word;
        for (int i = 0; i < line.length(); i++) {
            int charIdx = lWord.indexOf(line.charAt(i));
            if(charIdx > -1)
                lWord = new StringBuilder(lWord).deleteCharAt(charIdx).toString();
            else
                return false;
        }
        return true;
    }
}
