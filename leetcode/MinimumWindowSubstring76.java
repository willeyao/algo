package leetcode;

import java.util.HashMap;
import java.util.Map;

public class MinimumWindowSubstring76 {

    /**
     * 性能优化
     * 主要调整了计算两个字符串相似度的方法，通过加法计数法来计算
     * @param s
     * @param t
     * @return
     */
    public String minWindow(String s, String t) {
        if (s.length() < t.length()) {
            return "";
        }
        Map<Character, Integer> charMap = new HashMap<>();
        for (char ch : t.toCharArray()) {
            int value = charMap.getOrDefault(ch, 0);
            charMap.put(ch, value+1);
        }
        Map<Character, Integer> sMap = new HashMap<>();
        int left = 0;
        int count = 0;
        String rt = "";
        for (int i=0;i<s.length();i++) {
            char ch = s.charAt(i);
            if (!charMap.containsKey(ch)) {
                continue;
            }
            sMap.put(ch, sMap.getOrDefault(ch, 0)+1);
            if (sMap.get(ch) <= charMap.get(ch)) {
                count++;
            }
            
            if (count == t.length()) {
                while (left <= i) {
                    char tmpChar = s.charAt(left);
                    if (!charMap.containsKey(tmpChar) || sMap.get(tmpChar) > charMap.get(tmpChar)) {
                        left++;
                        if (sMap.containsKey(tmpChar)) {
                            sMap.put(tmpChar, sMap.get(tmpChar) -1);
                        }
                    } else {
                        break;
                    }
                }
                if (rt == "" || rt.length() > i - left + 1) {
                    rt = s.substring(left, i+1);
                }
            }
        }
        return rt;
    }

    public static void main(String[] args) {
        MinimumWindowSubstring76 instance = new MinimumWindowSubstring76();
        System.out.println(instance.minWindow("ADOBECODEBANC", "ABC"));
        System.out.println(instance.minWindow("a", "a"));
        System.out.println(instance.minWindow("a", "aa"));
        System.out.println(instance.minWindow("ab", "b"));
    }
}
