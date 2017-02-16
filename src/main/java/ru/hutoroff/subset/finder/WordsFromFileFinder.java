package ru.hutoroff.subset.finder;

import ru.hutoroff.subset.exception.WordFinderException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by hutoroff on 16.02.17.
 */
public class WordsFromFileFinder {

    private final String targetWord;
    private final HashSet<String> dict;

    private WordsFromFileFinder(String targetWord, Path pathToDict) throws WordFinderException {
        this.targetWord = targetWord != null ? targetWord.toLowerCase() : null;
        this.dict = readDict(pathToDict);
    }

    public WordsFromFileFinder(String word, String dictPath) throws WordFinderException {
        this(word, Paths.get(dictPath));
    }

    public Set<String> findSubsetsFromWord() {
        if(targetWord == null)
            return Collections.emptySet();

        Set<String> allWordPermutations = getAllWordPermutations(this.targetWord);
        if(allWordPermutations == null)
            return Collections.emptySet();
        allWordPermutations.retainAll(dict);
        return allWordPermutations;
    }

    private Set<String> getAllWordPermutations(String word) {
        WordPermutation wordPermutation = new WordPermutation(word);
        return wordPermutation.getAllPermutations();
    }

    private HashSet<String> readDict(Path pathToDict) throws WordFinderException {
        try {
            return Files.lines(pathToDict).map(line -> line.trim().toLowerCase()).collect(Collectors.toCollection(HashSet::new));
        } catch (IOException e) {
            throw new WordFinderException("Error on reading dict: " + pathToDict, e);
        }
    }
}
