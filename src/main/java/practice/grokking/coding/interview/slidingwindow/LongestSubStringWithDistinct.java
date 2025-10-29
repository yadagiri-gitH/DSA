package practice.grokking.coding.interview.slidingwindow;

import java.util.HashMap;

public class LongestSubStringWithDistinct {


    /* won't work for abca
    public static int findLength(String str) {
         if (str == null || str.length() == 0)
             throw new IllegalArgumentException();

         char prev = str.charAt(0);
         int start = 0;
         int longestCount = 1;

         for (int i = 1; i < str.length(); i++) {
             char c = str.charAt(i);
             if (c != prev) {
                 longestCount = Math.max(longestCount, i - start + 1);
             } else {
                 start = i;
             }
             prev = c;
         }

         return longestCount;
     }
     */

    public static int findLengthUsingMap(String str) {
        if (str == null || str.length() == 0)
            throw new IllegalArgumentException();

        HashMap<Character, Integer> dCountMap = new HashMap<>();
        int start = 0;
        int maxLength = 0;

        for (int i = 0; i < str.length(); i++) {
            char current = str.charAt(i);
            if (dCountMap.containsKey(current)) {
                start = Math.max(start, dCountMap.get(current) + 1);
            }
            dCountMap.put(current, i);
            maxLength = Math.max(maxLength, i - start + 1);
        }

        return maxLength;
    }

    public static void main(String[] args) {
        //1st solution won't work if its non repetitive char
        //System.out.println("Length of the longest substring: " + LongestSubStringWithDistinct.findLength("abca"));
        System.out.println("Length of the longest substring: " + LongestSubStringWithDistinct.findLengthUsingMap("tmmzuxt"));
        System.out.println("Length of the longest substring: " + LongestSubStringWithDistinct.findLengthUsingMap("aabccbb"));
        System.out.println("Length of the longest substring: " + LongestSubStringWithDistinct.findLengthUsingMap("abbbb"));
        System.out.println("Length of the longest substring: " + LongestSubStringWithDistinct.findLengthUsingMap("abccde"));
        System.out.println("Length of the longest substring: " + LongestSubStringWithDistinct.findLengthUsingMap("abbabc"));

    }

}
