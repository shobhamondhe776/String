package Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;

public class Stringmethod {
	public static void main(String[] args) {
        String str1 = "Hello, World!";
        String str2 = "race car";
        String str3 = "listen";
        String str4 = "aabcccccaaa";
        String str5 = "Hello World";

        System.out.println("Character Frequency: " + charFrequency(str1));
        System.out.println("Is Palindrome: " + isPalindrome(str2));
        System.out.println("Are Anagrams: " + areAnagrams("listen", "silent"));
        System.out.println("Longest Unique Substring: " + longestUniqueSubstring(str3));
        System.out.println("Compressed String: " + compressString(str4));
        System.out.println("Permutations: " + findPermutations(str3));
        System.out.println("Duplicates Removed: " + removeDuplicates(str1));
        System.out.println("Words Reversed: " + reverseWords(str5));
    }

    public static HashMap<Character, Integer> charFrequency(String str) {
        HashMap<Character, Integer> frequencyMap = new HashMap<>();
        for (char c : str.toCharArray()) {
            frequencyMap.merge(c, 1, Integer::sum);
        }
        return frequencyMap;
    }

    public static boolean isPalindrome(String str) {
        String cleanedStr = str.replaceAll("\\s", "").toLowerCase();
        return cleanedStr.equals(new StringBuilder(cleanedStr).reverse().toString());
    }

    public static boolean areAnagrams(String str1, String str2) {
        if (str1.length() != str2.length()) return false;
        char[] charArray1 = str1.replaceAll("\\s", "").toLowerCase().toCharArray();
        char[] charArray2 = str2.replaceAll("\\s", "").toLowerCase().toCharArray();
        Arrays.sort(charArray1);
        Arrays.sort(charArray2);
        return Arrays.equals(charArray1, charArray2);
    }

    public static String longestUniqueSubstring(String str) {
         HashSet<Character> charSet = new HashSet<>();
        int maxLength = 0;
        int start = 0;
        int end = 0;
        String longestSubstring = "";
        while (end < str.length()) {
            if (!charSet.contains(str.charAt(end))) {
                charSet.add(str.charAt(end));
                if (charSet.size() > maxLength) {
                    maxLength = charSet.size();
                    longestSubstring = str.substring(start, end + 1);
                }
                end++;
            } else {
                charSet.remove(str.charAt(start));
                start++;
            }
        }
        return longestSubstring;
    }

    public static String compressString(String str) {
        StringBuilder compressedStr = new StringBuilder();
        int count = 1;
        for (int i = 1; i < str.length(); i++) {
            if (str.charAt(i) == str.charAt(i - 1)) {
                count++;
            } else {
                compressedStr.append(str.charAt(i - 1)).append(count);
                count = 1;
            }
        }
        compressedStr.append(str.charAt(str.length() - 1)).append(count);
        return compressedStr.length() < str.length() ? compressedStr.toString() : str;
    }

    public static List<String> findPermutations(String str) {
        List<String> permutations = new ArrayList<>();
        findPermutationsHelper(str, "", permutations);
        return permutations;
    }

    private static void findPermutationsHelper(String str, String current, List<String> permutations) {
        if (str.length() == 0) {
            permutations.add(current);
            return;
        }
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            String remaining = str.substring(0, i) + str.substring(i + 1);
            findPermutationsHelper(remaining, current + c, permutations);
        }
    }

    public static String removeDuplicates(String str) {
        HashSet<Character> charSet = new LinkedHashSet<>();
        for (char c : str.toCharArray()) {
            charSet.add(c);
        }
        StringBuilder result = new StringBuilder();
        for (char c : charSet) {
            result.append(c);
        }
        return result.toString();
    }

    public static String reverseWords(String str) {
        String[] words = str.split("\\s");
        StringBuilder reversedStr = new StringBuilder();
        for (int i = words.length - 1; i >= 0; i--) {
            reversedStr.append(words[i]);
            if (i > 0) {
                reversedStr.append(" ");
            }
        }
        return reversedStr.toString();
    }
}


