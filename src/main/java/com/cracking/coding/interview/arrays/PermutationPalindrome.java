package com.cracking.coding.interview.arrays;

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

    public static boolean isPermutationOfPalindromeAlt(String str) {
        int ch[] = new int['z' - 'a' + 1];
        int countOdd = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) >= 'a' && str.charAt(i) <= 'z') {
                int c = str.charAt(i) - 'a';
                ch[c]++;
                if (ch[c] % 2 == 1) {
                    countOdd++;
                } else {
                    countOdd--;
                }
            }
        }

        return countOdd <= 1;
    }

    public static boolean isPermutationOfPalindromeUsingBitSet(String str) {
        int countOdd ;
        int bit = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) >= 'a' && str.charAt(i) <= 'z') {
                int c = str.charAt(i) - 'a';
                countOdd = bit & 1 << c;
                if (countOdd > 0) {
                    bit ^= 1 << c; //bit &= ~(1<<c)
                } else
                    bit |= 1 << c;
            }
        }

        return (bit & (bit - 1)) == 0;
    }

    public static void main(String[] args) {
        String str = "ab cb cgaf. ";
        System.out.println(str + " is Permutation Of Palindrome : " + isPermutationOfPalindrome(str));
        System.out.println(str + " is Permutation Of Palindrome : " + isPermutationOfPalindromeAlt(str));
        System.out.println(str + " is Permutation Of Palindrome : " + isPermutationOfPalindromeUsingBitSet(str));
    }
}