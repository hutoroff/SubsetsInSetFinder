package ru.hutoroff.subset.finder;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by hutoroff on 16.02.17.
 */
public class WordPermutation {

    private final String word;
    private Set<String> set;
    private final StringBuilder stringBuilder;

    public WordPermutation(String word) {
        this.stringBuilder = new StringBuilder();
        this.word = word;
    }

    public Set<String> getAllPermutations() {
        if(this.set == null)
            this.set = getAllPermutations(word);
        return this.set;
    }

    private Set<String> getAllPermutations(String word) {
        if(this.word == null)
            return Collections.emptySet();

        Set<String> result = getAllPermutationsSameSize(word);
        if(word.length() > 1) {
            for (int i = 0; i < word.length(); i++) {
                String tmp = new StringBuilder(word).deleteCharAt(i).toString();
                result.addAll(getAllPermutations(tmp));
            }
        }
        return result;
    }

    private Set<String> getAllPermutationsSameSize(String word) {
        Set<String> result = new HashSet<>();
        if (word.length() == 1) {
            result.add(word);
        } else if (word.length() > 1) {
            int lastIdx = word.length() - 1;
            String last = word.substring(lastIdx);
            String rest = word.substring(0, lastIdx);
            Set<String> tmp = getAllPermutationsSameSize(rest);
            result = merge(tmp, last);
        }
        return result;
    }

    private Set<String> merge(Set<String> permutations, String subword) {
        Set<String> result = new HashSet<>();
        for (String s : permutations) {
            for (int i = 0; i <= s.length(); i++) {
                String newVal = new StringBuffer(s).insert(i, subword).toString();
                result.add(newVal);
            }
        }
        return result;
    }
}
