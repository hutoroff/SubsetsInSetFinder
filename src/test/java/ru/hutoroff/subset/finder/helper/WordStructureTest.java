package ru.hutoroff.subset.finder.helper;

import org.junit.Test;
import ru.hutoroff.subset.finder.TestData;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by hutoroff on 25.02.17.
 */
public class WordStructureTest {
    @Test
    public void decrementSymbol() throws Exception {
        WordStructure actual = new WordStructure(TestData.TEST_WORD);
        actual.decrement('л');
        WordStructure expected = new WordStructure(TestData.TEST_WORD.replaceFirst("л", ""));
        assertEquals(expected, actual);
    }

    @Test
    public void decrementSymbolCount() throws Exception {
        WordStructure actual = new WordStructure(TestData.TEST_WORD);
        actual.decrement('к');
        WordStructure expected = new WordStructure(TestData.TEST_WORD.replaceFirst("к", ""));
        assertEquals(expected, actual);
    }

    @Test
    public void equalsTest() throws Exception {
        WordStructure first = new WordStructure("abcc");
        WordStructure second = new WordStructure("cbac");
        assertTrue(first.equals(second));
    }
}