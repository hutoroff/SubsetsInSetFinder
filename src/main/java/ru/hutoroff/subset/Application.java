package ru.hutoroff.subset;

/**
 * Created by hutoroff on 16.02.17.
 */
public class Application {
    private static String word;
    private static String dictPath;

    public static void main(String[] args) {
        if(args == null || args.length <2) {
            System.err.println("Incorrect input. Awaited: %WORD% %PATH_TO_DICT%");
            return;
        }

        word = args[0];
        dictPath = args[1];
        System.out.println("Word: " + word);
        System.out.println("Path to dict: " + dictPath);

        //TODO: logic
    }
}
