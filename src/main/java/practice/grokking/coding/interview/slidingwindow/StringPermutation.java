package practice.grokking.coding.interview.slidingwindow;

import java.util.HashMap;

class StringPermutation {
    public static boolean findPermutation(String str, String pattern) {

        if (str == null || str.length() < pattern.length())
            throw new IllegalArgumentException();

        HashMap<Character, Integer> freqMap = new HashMap<>();

        for (int i = 0; i < pattern.length(); i++) {
            char c = pattern.charAt(i);
            freqMap.put(c, freqMap.getOrDefault(c, 0) + 1);
        }

        int start = 0;
        int matchedChar = 0;
        for (int current = 0; current < str.length(); current++) {
            char ch = str.charAt(current);

            if (freqMap.containsKey(ch)) {
                freqMap.put(ch, freqMap.get(ch) - 1);
                if (freqMap.get(ch) == 0)
                    matchedChar++;
            }

            if (matchedChar == freqMap.size())
                return true;

            if (current >= pattern.length() - 1) {
                char startChar = str.charAt(start++);
                if (freqMap.containsKey(startChar)) {
                    if (freqMap.get(startChar) == 0)
                        matchedChar--;
                    freqMap.put(startChar, freqMap.getOrDefault(startChar, 0) + 1);
                }
            }
        }
        return false;
    }
}

