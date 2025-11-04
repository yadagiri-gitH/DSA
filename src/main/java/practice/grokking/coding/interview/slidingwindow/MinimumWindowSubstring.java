package practice.grokking.coding.interview.slidingwindow;

import java.util.HashMap;
import java.util.Map;

class MinimumWindowSubstring {
    public static String findSubstring(String str, String pattern) {
        if (str == null || pattern == null || str.length() < pattern.length())
            return "";

        Map<Character, Integer> freqMap = new HashMap<>();
        for (char c : pattern.toCharArray())
            freqMap.put(c, freqMap.getOrDefault(c, 0) + 1);

        int start = 0, matched = 0;
        int minLength = Integer.MAX_VALUE;
        int subStrStart = 0;

        for (int i = 0; i < str.length(); i++) {
            char cc = str.charAt(i);
            if (freqMap.containsKey(cc)) {
                freqMap.put(cc, freqMap.get(cc) - 1);
                if (freqMap.get(cc) == 0)
                    matched++;
            }

            // shrink window from left while all chars matched
            while (matched == freqMap.size()) {

                if (i - start + 1 < minLength) {
                    minLength = i - start + 1;
                    subStrStart = start;
                }

                char sc = str.charAt(start++);
                if (freqMap.containsKey(sc)) {
                    if (freqMap.get(sc) == 0) {
                        matched--;
                    }
                    freqMap.put(sc, freqMap.get(sc) + 1);
                }
            }
        }
        return minLength == Integer.MAX_VALUE ? "" : str.substring(subStrStart, subStrStart + minLength);
    }

    public static void main(String[] args) {
        System.out.println(findSubstring("aabdec", "abc"));  // "abdec"
        System.out.println(findSubstring("abdabca", "abc")); // "abc"
        System.out.println(findSubstring("adcad", "abc"));   // ""
    }
}
