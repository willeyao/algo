package leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * 最长不重复子串
 * 使用滑动窗口算法
 * right: i
 * left: 重复字符串的下一个字符(如abcba,right滑动到第2个b时出现重复，left滑动到第一个b的后面，indexof(b)+1)
 */
public class LongestSubstringWithoutRepeatingCharacters3 {
    public static int lengthOfLongestSubstring(String s) {
        if (s == null || "".equals(s)) {
            return 0;
        }
        int maxLenght = 1;
        // key: char, value: index
        Map<Character, Integer> charMap = new HashMap<>();
        int left = 0;
        for (int i=0;i<s.length();i++) {
            char ch = s.charAt(i);
            // 出现重复
            if (charMap.containsKey(ch)) {
                int repeatedIndex = charMap.get(ch);
                left = Math.max(left, repeatedIndex+1);
            }
            charMap.put(ch, i);
            int currLegth = i - left + 1;
            maxLenght = Math.max(maxLenght, currLegth);
        }
        return maxLenght;
    }

    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("abcba"));
        System.out.println(lengthOfLongestSubstring("abc"));
        System.out.println(lengthOfLongestSubstring("aabc"));
        System.out.println(lengthOfLongestSubstring("abbc"));
    }
}
