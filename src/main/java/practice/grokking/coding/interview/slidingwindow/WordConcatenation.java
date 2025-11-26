package practice.grokking.coding.interview.slidingwindow;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WordConcatenation {
    public static List<Integer> findWordConcatenation(String str, String[] words) {
        List<Integer> resultIndices = new ArrayList<>();

        if (str == null || words == null || str.isEmpty() || words == null)
            return resultIndices;

        int wordCount = words.length, wordLength = words[0].length(), totalChar = wordCount * wordLength;

        Map<String, Integer> frequency = new HashMap<>();
        for (String word : words)
            frequency.put(word, frequency.getOrDefault(word, 0) + 1);

        for (int i = 0; i <= str.length() - totalChar; i++) {
            Map<String, Integer> wordsSeen = new HashMap<>();
            for (int j = 0; j < wordCount; j++) {
                int nextWordIndex = i + j * wordLength;
                String nextWord = str.substring(nextWordIndex, nextWordIndex + wordLength);
                if (!frequency.containsKey(nextWord))
                    break;

                wordsSeen.put(nextWord, wordsSeen.getOrDefault(nextWord, 0) + 1);

                if (wordsSeen.get(nextWord) > frequency.get(nextWord))
                    break;

                if (j + 1 == wordCount)
                    resultIndices.add(i);
            }
        }

        return resultIndices;
    }

    public static void main(String[] args) {
        List<Integer> result = WordConcatenation.findWordConcatenation("catfoxcat", new String[]{"cat", "fox"});
        System.out.println(result);
        result = WordConcatenation.findWordConcatenation("abccatcatfoxfox", new String[]{"cat", "fox"});
        System.out.println(result);
    }
}