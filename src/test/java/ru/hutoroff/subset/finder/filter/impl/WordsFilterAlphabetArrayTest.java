package ru.hutoroff.subset.finder.filter.impl;

import org.junit.Before;
import org.junit.Test;
import ru.hutoroff.subset.finder.TestData;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by hutoroff on 25.02.17.
 */
public class WordsFilterAlphabetArrayTest {

    private WordsFilterAlphabetArray underTest;

    @Before
    public void setUp() throws Exception {
        underTest = new WordsFilterAlphabetArray(TestData.TEST_WORD);
    }

    @Test
    public void doFilterTrue() throws Exception {
        assertTrue(underTest.doFilter("рок"));
    }

    @Test
    public void doFilterFalse() throws Exception {
        assertFalse(underTest.doFilter("рококо"));
    }
}