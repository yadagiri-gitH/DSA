package com.cracking.coding.interview;

public class UniqueCharacterString {

    public boolean isUniqueCharStringUsingBruteForce(String s) {
        for (int i = 0; i < s.length() - 1; i++) {
            for (int j = i + 1; j < s.length(); j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isUniqueCharStringUsingAscii(String s) {
        boolean ch[] = new boolean[256];
        for (int i = 0; i < s.length(); i++) {
            if (ch[s.charAt(i)]) {
                return false;
            }
            ch[s.charAt(i)] = true;
        }
        return true;
    }

    public boolean isUniqueCharStringUsingBitSet(String s) {
        int bit = 0;
        for (int i = 0; i < s.length(); i++) {
            int charNum = s.charAt(i) - 'a';
            int value = bit & (1 << charNum);
            if (value > 0) {
                return false;
            }
            bit |= charNum;
        }
        return true;
    }

    public static void main(String[] args) {
        UniqueCharacterString ucs = new UniqueCharacterString();
        System.out.println(ucs.isUniqueCharStringUsingBruteForce("abcdefghij"));
        System.out.println(ucs.isUniqueCharStringUsingAscii("abcdefghij"));
        System.out.println(ucs.isUniqueCharStringUsingBitSet("abcdefghij"));
    }
}
