package com.xgsama.algorithm.others;

import java.util.HashSet;
import java.util.Set;

/**
 * AlgoTest
 *
 * @author : xgSama
 * @date : 2021/8/2 20:58:52
 */
public class AlgoTest {

    public int lengthOfLongestSubstring(String s) {
        int max = 0, fast = 0, slow = 0, len = s.length();
        Set<Character> characters = new HashSet<>();

        for (int i = 0; i < len; i++) {
            while (characters.contains(s.charAt(i))) {
                characters.remove(s.charAt(slow++));
            }
            characters.add(s.charAt(i));

            max = Math.max(max, characters.size());
        }

        return max;
    }
}
