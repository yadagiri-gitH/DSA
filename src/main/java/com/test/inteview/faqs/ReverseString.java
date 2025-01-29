package com.test.inteview.faqs;

import java.util.Arrays;

public class ReverseString {
    public static void main(String[] args) {
        String[] str = {"hello", "world", "java", "programming"};
        int n = str.length;
        String[] result = new String[n];
        for (int i = 0; i < n; i++) {
            char cs[] = str[i].toCharArray();
           // System.out.println("Actual String:" + String.valueOf(cs));
            int start = 0;
            int last = cs.length - 1;
            while (start <= last) {
                char temp = cs[start];
                cs[start] =cs[last];
                cs[last] = temp;
                start++;
                last--;
            }

            result[i] = String.valueOf(cs);
        }
        System.out.println("Reversed String Result:" + Arrays.toString(result));
    }
}
