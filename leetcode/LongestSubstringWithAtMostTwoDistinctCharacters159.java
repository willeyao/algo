package leetcode;

import java.util.HashMap;
import java.util.Map;

public class LongestSubstringWithAtMostTwoDistinctCharacters159 {
    public int lengthOfLongestSubstringTwoDistinct(String s) {
        if (s.length() <= 2) {
            return s.length();
        }
        int len = 2;
        int left = 0;
        // key: 字符，value: 连续相同中最小的index
        Map<Character, Integer> charMap = new HashMap<>();
        for (int i=0;i<s.length();i++) {
            char ch = s.charAt(i);
            if (charMap.containsKey(ch)) {
                // 不连续
                if (charMap.get(ch) + 1 < i) {
                    charMap.put(ch, i);
                }
                if (i==s.length()-1 && len < i-left + 1) {
                    len = i - left + 1;
                }
                continue;
            }
            // 第一个出现的字符放入map中
            if (charMap.size() < 2) {
                charMap.put(ch, i);
            } else {
                if (len < i-left) {
                    len = i - left;
                }
                // 左指针移动
                left = charMap.get(s.charAt(i-1));
                charMap.clear();
                charMap.put(s.charAt(i-1), left);
                charMap.put(s.charAt(i), i);
            }
        }
        return len;
    }

    public static void main(String[] args) {
        LongestSubstringWithAtMostTwoDistinctCharacters159 instance = new LongestSubstringWithAtMostTwoDistinctCharacters159();
        System.out.println(instance.lengthOfLongestSubstringTwoDistinct("eceba"));
        System.out.println(instance.lengthOfLongestSubstringTwoDistinct("ccaabbba"));
    }
}
