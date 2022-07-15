package com.cracking.coding.interview;

public class StringCompression {
    public static String compression(String str) {
        StringBuilder compression = new StringBuilder();
        int index = 0;
        int repeatCharCount = 0;
        int length = str.length();

        while (index < length) {

            repeatCharCount++;

            if (index + 1 == length || str.charAt(index) != str.charAt(index + 1)) {
                compression.append(str.charAt(index));
                compression.append(repeatCharCount++);
                repeatCharCount = 0;
            }

            index++;

        }

        return compression.length() < str.length() ? compression.toString() : str;
    }

    public static void main(String[] args) {
        System.out.println(compression("aaaabbbbdddhhhhhggggdddaaab"));
        System.out.println(compression("aaaabbbbdddhhhhh"));
    }
}
