package ru.hutoroff.subset.finder.impl;

import org.junit.Before;
import org.junit.Test;
import ru.hutoroff.subset.finder.TestData;
import ru.hutoroff.subset.finder.filter.WordsFilter;
import ru.hutoroff.subset.finder.filter.impl.WordsFilterPrimitive;

import java.util.Collection;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by hutoroff on 25.02.17.
 */
public class WordsFromFileFinderFilterBasedTest {

    private WordsFromFileFinderFilterBased underTest;
    private WordsFilter filter = new WordsFilterPrimitive(TestData.TEST_WORD);

    @Before
    public void setUp() throws Exception {
        underTest = new WordsFromFileFinderFilterBased(TestData.PATH_TO_DICT, this.filter);
    }

    @Test
    public void findSubsetsFromWord() throws Exception {
        Collection<String> actual = underTest.findSubsetsFromWord();
        assertNotNull(actual);
        assertTrue(actual.containsAll(TestData.EXPECTED_SET));
        assertTrue(TestData.EXPECTED_SET.containsAll(actual));
    }

}