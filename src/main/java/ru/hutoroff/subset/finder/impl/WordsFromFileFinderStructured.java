package ru.hutoroff.subset.finder.impl;

import ru.hutoroff.subset.exception.WordFinderException;
import ru.hutoroff.subset.finder.WordsFromFileFinder;
import ru.hutoroff.subset.finder.helper.WordStructure;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

/**
 * Created by hutoroff on 19.02.17.
 */
public class WordsFromFileFinderStructured implements WordsFromFileFinder {

    private final WordStructure targetWordStructure;
    private final Path pathToDict;
    private Set<String> list;

    private WordsFromFileFinderStructured(String targetWord, Path pathToFile) {
        this.targetWordStructure =  new WordStructure(targetWord != null ? targetWord.toLowerCase() : null);
        this.pathToDict = pathToFile;
    }

    public WordsFromFileFinderStructured(String targetWord, String pathToFile) {
        this(targetWord, Paths.get(pathToFile));
    }

    @Override
    public Set<String> findSubsetsFromWord() throws WordFinderException {
        if(this.list == null)
            this.list = findSubsetsFromWord(this.targetWordStructure);
        return this.list;
    }

    private Set<String> findSubsetsFromWord(WordStructure targetWordStrucutre) throws WordFinderException {
        try {
            return Files.lines(this.pathToDict).filter(line -> lineIsInWord(line, targetWordStrucutre)).collect(Collectors.toCollection(TreeSet::new));
        } catch (IOException e) {
            throw new WordFinderException("Error on reading dict: " + pathToDict, e);
        }
    }

    private boolean lineIsInWord(String line, WordStructure wordStructure) {
        WordStructure ws;
        try {
            ws = (WordStructure) wordStructure.clone();
        } catch (CloneNotSupportedException e) {
            return false;
        }

        for (char c : line.toCharArray()) {
            if(!ws.decrement(c))
                return false;
        }
        return true;
    }
}
