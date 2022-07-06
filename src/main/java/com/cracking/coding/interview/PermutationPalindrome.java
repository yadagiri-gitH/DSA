package com.cracking.coding.interview;

public class PermutationPalindrome {
    /* this will work if every bit of String is considered including special symbols ,means it won't work for aa b*/
   /* public boolean isPermutationOfPalindrome(String str) {
        int[] charFreq = new int[256];
        for (int i = 0; i < str.length(); i++) {
            charFreq[str.charAt(i)]++;
        }

        boolean foundOdd = false;
        for (int i = 0; i < charFreq.length; i++) {
            if (charFreq[i] % 2 > 0) {
                if (foundOdd) {
                    return false;
                }
                foundOdd = true;
            }
        }

        return true;
    }
  */
    public static boolean isPermutationOfPalindrome(String str) {
        int[] charFreq = new int['z' - 'a' + 1];//122-97=25

        for (int i = 0; i < str.length(); i++) {
            int ch = str.charAt(i);
            if (ch >= 'a' && ch <= 'z') {
                charFreq[ch - 'a']++;
            }
        }

        boolean foundOdd = false;
        for (int i = 0; i < charFreq.length; i++) {
            if (charFreq[i] % 2 > 0) {
                if (foundOdd) {
                    return false;
                }
                foundOdd = true;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        String str="ab cb a. ";
        System.out.println(str +" is Permutation Of Palindrome : "+isPermutationOfPalindrome(str));
    }
}