package com.cracking.coding.interview;

public class UniqueCharacter {

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
        int bitChecker = 0;
        for (int i = 0; i < s.length(); i++) {
            int val = s.charAt(i) - 'a';
            if ((bitChecker & (1 << val)) > 0) {
                return false;
            }
            bitChecker |= (1 << val);
        }
        return true;
    }

    public static void main(String[] args) {
        UniqueCharacter ucs = new UniqueCharacter();
        System.out.println(ucs.isUniqueCharStringUsingBruteForce("abcdefghij"));
        System.out.println(ucs.isUniqueCharStringUsingAscii("abcdefghij"));
        System.out.println(ucs.isUniqueCharStringUsingBitSet("abcdefghij"));
    }
}
