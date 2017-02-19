package ru.hutoroff.subset.finder.impl;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by hutoroff on 19.02.17.
 */
abstract class WordsFromFileFinderTest {
    protected static final String TEST_WORD = "крокодил";
    protected static final String PATH_TO_DICT = WordsFromFileFinderHashSetTest.class.getResource("/testDict.txt").getPath();
    protected static final Set<String> EXPECTED_SET = new HashSet<String>() {
        {
            add("код");
            add("док");
            add("род");
        }
    };
}
