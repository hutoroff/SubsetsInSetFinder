package ru.hutoroff.subset.finder;

import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by hutoroff on 16.02.17.
 */
public class WordPermutationTest {

    private static final String TEST_WORD = "123";
    private Set<String> EXPECTED_SET = new HashSet<String>() {
        {
            add("1");
            add("2");
            add("3");
            add("12");
            add("21");
            add("23");
            add("32");
            add("31");
            add("13");
            add("132");
            add("231");
            add("123");
            add("321");
            add("312");
            add("213");

        }
    };

    @Test
    public void getAllPermutations() throws Exception {
        WordPermutation wordPermutation = new WordPermutation(TEST_WORD);
        Set<String> actual = wordPermutation.getAllPermutations();
        assertNotNull(actual);
        assertTrue(actual.size() == EXPECTED_SET.size());
        assertTrue(actual.containsAll(EXPECTED_SET));
        assertTrue(EXPECTED_SET.containsAll(actual));
    }
}