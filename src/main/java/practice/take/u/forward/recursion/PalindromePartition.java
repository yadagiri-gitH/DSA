package practice.take.u.forward.recursion;

import java.util.ArrayList;
import java.util.List;

public class PalindromePartition {
    public static void main(String[] args) {
        String s = "abbb";
        List<List<String>> palindromeList = new ArrayList<>();
        palindromePartition(0, s, palindromeList, new ArrayList<>());
        System.out.println(palindromeList);
    }

    public static void palindromePartition(int start, String s, List<List<String>> palindromeList, List<String> palindrome) {
        if (start == s.length()) {
            palindromeList.add(new ArrayList<>(palindrome));
            return;
        }

        for (int index = start; index < s.length(); index++) {
            if (isPalindrome(start, index, s)) {
                palindrome.add(s.substring(start, index + 1));
                palindromePartition(index + 1, s, palindromeList, palindrome);
                palindrome.remove(palindrome.size() - 1);
            }
        }

    }

    public static boolean isPalindrome(int start, int end, String str) {
        while (start <= end) {
            if (str.charAt(start++) != str.charAt(end--)) {
                return false;
            }
        }
        return true;
    }
}
