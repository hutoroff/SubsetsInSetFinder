package ru.hutoroff.subset;

import ru.hutoroff.subset.exception.WordFinderException;
import ru.hutoroff.subset.finder.WordsFromFileFinder;
import ru.hutoroff.subset.finder.impl.WordsFromFileFinderPrimitive;
import ru.hutoroff.subset.finder.impl.WordsFromFileFinderStructured;

import java.time.Duration;
import java.time.Instant;

/**
 * Created by hutoroff on 16.02.17.
 */
public class Application {
    private static String word;
    private static String dictPath;
    private static Integer solutionType;

    public static void main(String[] args) {
        if(args == null || args.length <2) {
            System.err.println("Incorrect input. Awaited: %WORD% %PATH_TO_DICT%");
            return;
        }

        word = args[0];
        dictPath = args[1];
        if(args.length > 2)
            solutionType = args[2] != null ? Integer.valueOf(args[2]) : null;
        System.out.println("Word: " + word);
        System.out.println("Path to dict: " + dictPath);
        if(solutionType != null)
            System.out.println("Solution type: " + (solutionType == 0 ? "Primitive based" : "Structure based"));
        else
            solutionType = 1;

        WordsFromFileFinder wordsFromFileFinder;
        Integer counter = 0;
        Instant start = null;
        Instant finish = null;
        if(solutionType == 0) {
            try {
                start = Instant.now();
                wordsFromFileFinder = new WordsFromFileFinderPrimitive(word, dictPath);
                wordsFromFileFinder.findSubsetsFromWord().forEach(System.out::println);
                finish = Instant.now();
                counter = wordsFromFileFinder.findSubsetsFromWord().size();
            } catch (WordFinderException e) {
                e.printStackTrace();
            }
        } else {
            try {
                start = Instant.now();
                wordsFromFileFinder = new WordsFromFileFinderStructured(word, dictPath);
                wordsFromFileFinder.findSubsetsFromWord().forEach(System.out::println);
                finish = Instant.now();
                counter = wordsFromFileFinder.findSubsetsFromWord().size();
            } catch (WordFinderException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Found " + counter + " words in " + Duration.between(start, finish) + " ns");
    }
}
