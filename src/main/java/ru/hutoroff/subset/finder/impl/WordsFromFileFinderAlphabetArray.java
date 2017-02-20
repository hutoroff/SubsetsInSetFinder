package ru.hutoroff.subset.finder.impl;

import ru.hutoroff.subset.exception.WordFinderException;
import ru.hutoroff.subset.finder.WordsFromFileFinder;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by hutoroff on 20.02.17.
 */
public class WordsFromFileFinderAlphabetArray implements WordsFromFileFinder {

    private int[] sourceWordSymbolsWithCount;
    private int[] testWordSymbolsWithCount;
    private Path pathToDict;
    private Queue<Character> touchedTestWordSymbolsIndexes;
    private List<String> result;
    private Character queueElement;

    private WordsFromFileFinderAlphabetArray(String targetWord, Path pathToDict) {
        this.pathToDict = pathToDict;
        this.sourceWordSymbolsWithCount = new int[Character.MAX_VALUE];
        this.testWordSymbolsWithCount = new int[Character.MAX_VALUE];
        this.touchedTestWordSymbolsIndexes = new LinkedList<>();
        this.queueElement = null;
        for (char c : targetWord.toLowerCase().toCharArray())
            sourceWordSymbolsWithCount[c] = sourceWordSymbolsWithCount[c] + 1;
    }

    public WordsFromFileFinderAlphabetArray(String targetWord, String pathToDict) {
        this(targetWord, Paths.get(pathToDict));
    }

    @Override
    public Collection<String> findSubsetsFromWord() throws WordFinderException {
        if(this.result == null)
            try {
                this.result = Files.lines(this.pathToDict).filter(this::filterLine).collect(Collectors.toCollection(LinkedList::new));
            } catch (IOException e) {
                throw new WordFinderException("Error on reading dict: " + pathToDict, e);
            }
        return this.result;
    }

    private boolean filterLine(String line) {
        for (char c : line.toCharArray()) {
            touchedTestWordSymbolsIndexes.add(c);
            testWordSymbolsWithCount[c] = testWordSymbolsWithCount[c] + 1;
            if(testWordSymbolsWithCount[c] > sourceWordSymbolsWithCount[c]) {
                clearForNextLoop();
                return false;
            }
        }
        clearForNextLoop();
        return true;
    }

    private void clearForNextLoop() {
        if(touchedTestWordSymbolsIndexes.size() > Character.MAX_VALUE) {
            for (int i = 0; i < testWordSymbolsWithCount.length; i++)
                testWordSymbolsWithCount[i] = 0;
            touchedTestWordSymbolsIndexes.clear();
        } else {
            do {
                queueElement = touchedTestWordSymbolsIndexes.poll();
                if(queueElement != null)
                    testWordSymbolsWithCount[queueElement] = 0;
                else
                    break;
            } while (true);
        }
    }
}
