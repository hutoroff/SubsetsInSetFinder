package ru.hutoroff.subset.finder.filter.impl;

import ru.hutoroff.subset.finder.filter.WordsFilter;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by hutoroff on 25.02.17.
 */
public class WordsFilterAlphabetArray extends WordsFilter {

    private int[] sourceWordSymbolsWithCount;
    private int[] testWordSymbolsWithCount;
    private Queue<Character> touchedTestWordSymbolsIndexes;
    private Character queueElement;

    public WordsFilterAlphabetArray(String targetWord) {
        super(targetWord);
        sourceWordSymbolsWithCount = new int[Character.MAX_VALUE];
        testWordSymbolsWithCount = new int[Character.MAX_VALUE];
        touchedTestWordSymbolsIndexes = new LinkedList<>();
        queueElement = null;
        if(this.targetWord != null) {
            for (char c : this.targetWord.toCharArray()) {
                sourceWordSymbolsWithCount[c] = sourceWordSymbolsWithCount[c] + 1;
            }
        }
    }

    @Override
    public boolean doFilter(String currentWord) {
        for (char c : currentWord.toCharArray()) {
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
