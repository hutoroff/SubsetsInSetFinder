package ru.hutoroff.subset.finder;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by hutoroff on 25.02.17.
 */
public class TestData {
    public static final String TEST_WORD = "крокодил";
    public static final String PATH_TO_DICT = TestData.class.getResource("/testDict.txt").getPath();
    public static final Set<String> EXPECTED_SET = Collections.unmodifiableSet(new HashSet<String>() {
        {
            add("код");
            add("док");
            add("род");
        }
    });
}
