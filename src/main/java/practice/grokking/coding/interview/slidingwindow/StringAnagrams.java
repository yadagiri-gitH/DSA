package practice.grokking.coding.interview.slidingwindow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

class StringAnagrams {
    public static List<Integer> findStringAnagrams(String str, String pattern) {
        List<Integer> resultIndices = new ArrayList<Integer>();

        int start = 0;
        int matched = 0;

        HashMap<Character, Integer> freqMap = new HashMap<>();

        for (char c : pattern.toCharArray()) {
            freqMap.put(c, freqMap.getOrDefault(c, 0) + 1);
        }

        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);

            if (freqMap.containsKey(c)) {
                freqMap.put(c, freqMap.get(c) - 1);
                if (freqMap.get(c) == 0) {
                    matched++;
                }
            }

            if (matched == freqMap.size()) {
                resultIndices.add(start);
            }

            if (i - start + 1 >= pattern.length()) {
                char sc = str.charAt(start++);
                if (freqMap.containsKey(sc)) {
                    if (freqMap.get(sc) == 0) {
                        matched--;
                    }
                    freqMap.put(sc, freqMap.getOrDefault(sc, 0) + 1);
                }
            }
        }

        return resultIndices;
    }

    public static void main(String[] args) {
        System.out.println(StringAnagrams.findStringAnagrams("ppqp", "pq"));
        System.out.println(StringAnagrams.findStringAnagrams("abbcabc", "abc"));
    }

}
