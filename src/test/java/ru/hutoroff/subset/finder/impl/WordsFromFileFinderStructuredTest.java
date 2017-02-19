package ru.hutoroff.subset.finder.impl;

import org.junit.Test;

import java.util.Set;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by hutoroff on 19.02.17.
 */
public class WordsFromFileFinderStructuredTest extends WordsFromFileFinderTest {
    @Test
    public void findSubsetsFromWord() throws Exception {
        WordsFromFileFinderStructured underTest = new WordsFromFileFinderStructured(TEST_WORD, PATH_TO_DICT);
        Set<String> actual = underTest.findSubsetsFromWord();
        assertNotNull(actual);
        assertTrue(actual.containsAll(EXPECTED_SET));
        assertTrue(EXPECTED_SET.containsAll(actual));
    }

}