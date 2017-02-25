package ru.hutoroff.subset;

import ru.hutoroff.subset.exception.WordFinderException;
import ru.hutoroff.subset.finder.WordsFromFileFinder;
import ru.hutoroff.subset.finder.filter.WordsFilter;
import ru.hutoroff.subset.finder.filter.impl.WordsFilterAlphabetArray;
import ru.hutoroff.subset.finder.filter.impl.WordsFilterPrimitive;
import ru.hutoroff.subset.finder.impl.WordsFromFileFinderFilterBased;

import java.time.Duration;
import java.time.Instant;

/**
 * Created by hutoroff on 16.02.17.
 */
public class Application {
    private static String dictPath;
    private static Integer solutionType;

    public static void main(String[] args) {
        if(args == null || args.length <2) {
            System.err.println("Incorrect input. Awaited: %WORD% %PATH_TO_DICT%");
            return;
        }

        String word = args[0];
        dictPath = args[1];
        if(args.length > 2)
            solutionType = args[2] != null ? Integer.valueOf(args[2]) : null;
        System.out.println("Word: " + word);
        System.out.println("Path to dict: " + dictPath);
        if(solutionType != null)
            System.out.println("Solution type: " + (solutionType == 0 ? "Primitive based" : "Alphabet based"));
        else
            solutionType = 1;

        if(solutionType == 0) {
            WordsFilter filter = new WordsFilterPrimitive(word);
            printResultAndTime(filter);
        } else {
            WordsFilter filter = new WordsFilterAlphabetArray(word);
            printResultAndTime(filter);
        }
    }

    private static void printResultAndTime(WordsFilter filter) {
        Integer counter = 0;
        Instant start = null;
        Instant finish = null;
        WordsFromFileFinder wordsFromFileFinder;

        try {
            start = Instant.now();
            wordsFromFileFinder = new WordsFromFileFinderFilterBased(dictPath, filter);
            wordsFromFileFinder.findSubsetsFromWord().forEach(System.out::println);
            finish = Instant.now();
            counter = wordsFromFileFinder.findSubsetsFromWord().size();
        } catch (WordFinderException e) {
            e.printStackTrace();
        }

        System.out.println("Found " + counter + " words in " + Duration.between(start, finish) + " ns");
    }
}
