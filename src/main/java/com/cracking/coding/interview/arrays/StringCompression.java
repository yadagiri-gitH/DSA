package com.cracking.coding.interview.arrays;

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
                compression.append(repeatCharCount);
                repeatCharCount = 0;
            }

            index++;

        }

        return compression.length() < str.length() ? compression.toString() : str;
    }

    public static String compressionString(String str) { //In order to save string builder space as it increase double the size in case of smallString but still 2 iterations

        int compressionFinalLength = totalCompressionStringLength(str);

        if (compressionFinalLength > str.length()) {
            return str;
        }

        StringBuilder compression = new StringBuilder(compressionFinalLength);

        int index = 0;
        int repeatCharCount = 0;
        int length = str.length();

        while (index < length) {

            repeatCharCount++;

            if (index + 1 == length || str.charAt(index) != str.charAt(index + 1)) {
                compression.append(str.charAt(index));
                compression.append(repeatCharCount);
                repeatCharCount = 0;
            }

            index++;

        }

        return compression.toString();
    }

    public static int totalCompressionStringLength(String str) {
        int index = 0;
        int repeatCharCount = 0;
        int totalCompressionCharLength = 0;
        int length = str.length();

        while (index < length) {

            repeatCharCount++;

            if (index + 1 == length || str.charAt(index) != str.charAt(index + 1)) {
                totalCompressionCharLength = totalCompressionCharLength + 1 + String.valueOf(repeatCharCount).length(); //char+repeat char count
                repeatCharCount = 0;
            }

            index++;

        }

        return totalCompressionCharLength;
    }


    public static void main(String[] args) {
        System.out.println(compression("aaaabbbbdddhhhhhggggdddaaab"));
        System.out.println(compression("aaaabbbbdddhhhhh"));
        System.out.println(compressionString("aaaabbbbdddhhhhhggggdddaaab"));
        System.out.println(compressionString("abcdf"));
        System.out.println(compressionString("abcdffgfgjgfggdghdghhh"));
    }
}
