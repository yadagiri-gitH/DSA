package practice.test.inteview.faqs;

public class StringCompression {//GS interview question

    public static void main(String[] args) {
        String input = "a";
        StringBuilder results = new StringBuilder();
        if (input.length() == 1) {
            results.append(input + 1);
        }
        int count = 1;
        char prev = input.charAt(0);
        for (int i = 1; i < input.length(); i++) {
            char current = input.charAt(i);
            if (prev == current) {
                count++;
            }
            if (prev != current || i == input.length() - 1) {
                //  System.out.println(prev);
                // System.out.println(count);
                results.append(String.valueOf(prev) + String.valueOf(count));
                count = 1;
            }
            prev = current;
        }
        System.out.println(results.toString());
        System.out.println(compression(input));
    }

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

        return compression.toString();
    }
}
