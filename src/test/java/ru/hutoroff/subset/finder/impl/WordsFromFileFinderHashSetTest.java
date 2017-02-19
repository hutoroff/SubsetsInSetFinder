package ru.hutoroff.subset.finder.impl;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import ru.hutoroff.subset.exception.WordFinderException;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by hutoroff on 16.02.17.
 */
public class WordsFromFileFinderHashSetTest {

    private static final String TEST_WORD = "крокодил";
    private static final String PATH_TO_DICT = WordsFromFileFinderHashSetTest.class.getResource("/testDict.txt").getPath();
    private static final Set<String> EXPECTED_SET = new HashSet<String>() {
        {
            add("код");
            add("док");
            add("род");
        }
    };

    @Rule
    public final ExpectedException wordsFinderException = ExpectedException.none();

    @Test
    public void testConstructor_badFile() throws Exception {
        wordsFinderException.expect(WordFinderException.class);
        new WordsFromFileFinderHashSet(TEST_WORD, "dict1.txt");
    }

    @Test
    public void testConstructor_success() throws Exception {
        WordsFromFileFinderHashSet underTest = new WordsFromFileFinderHashSet(TEST_WORD, "/home/hutoroff/words.txt");
        assertNotNull(underTest);
    }

    @Test
    public void testFindSubsetsFromWord() throws Exception {
        WordsFromFileFinderHashSet underTest = new WordsFromFileFinderHashSet(TEST_WORD, PATH_TO_DICT);
        assertNotNull(underTest);
        Set<String> actual = underTest.findSubsetsFromWord();
        assertNotNull(actual);
        assertTrue(actual.size() == EXPECTED_SET.size());
        assertTrue(actual.containsAll(EXPECTED_SET));
        assertTrue(EXPECTED_SET.containsAll(actual));
    }
}