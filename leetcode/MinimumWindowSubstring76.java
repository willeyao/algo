package leetcode;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

public class MinimumWindowSubstring76 {
    public String minWindow(String s, String t) {
        if (s.length() < t.length()) {
            return "";
        }
        Queue<Integer> indexQueue = new ArrayDeque<>();
        Map<Character, Integer> charMap = new HashMap<>();
        for (char ch : t.toCharArray()) {
            int value = charMap.getOrDefault(ch, 0);
            charMap.put(ch, value+1);
        }
        int left = -1;
        String rt = "";
        Map<Character, Integer> tmpMap = new HashMap<>(charMap);
        Map<Character, Integer> extraMap = new HashMap<>();
        for (int i=0;i<s.length();i++) {
            char ch = s.charAt(i);
            if (!charMap.containsKey(ch)) {
                continue;
            }
            if (left < 0) {
                left = i;
            }
            if (i != left) {
                indexQueue.offer(i);
            }
            if (!tmpMap.containsKey(ch)) {
                extraMap.put(ch, extraMap.getOrDefault(ch, 0)+1);
                continue;
            }
            if (tmpMap.get(ch) == 1) {
                tmpMap.remove(ch);
            } else {
                tmpMap.put(ch, tmpMap.get(ch)-1);
            }
            // 匹配到一个字符串
            if (tmpMap.isEmpty()) {
                if (rt == "" || i-left+1 < rt.length()) {
                    rt = s.substring(left, i+1);
                }
                tmpMap = new HashMap<>(charMap);
                if (indexQueue.isEmpty()) {
                    continue;
                }
                while(true) {
                    char tmpChar = s.charAt(left);
                    if (extraMap.containsKey(tmpChar)) {
                        if (extraMap.get(tmpChar) == 1) {
                            extraMap.remove(tmpChar);
                        } else {
                            extraMap.put(tmpChar, extraMap.get(tmpChar)-1);
                        }
                        left = indexQueue.poll();
                        if (i-left+1 < rt.length()) {
                            rt = s.substring(left, i+1);
                        }
                    } else {
                        left = indexQueue.poll();
                        tmpChar = s.charAt(left);
                        if (tmpMap.get(tmpChar) == 1) {
                            tmpMap.remove(tmpChar);
                        } else {
                            tmpMap.put(tmpChar, tmpMap.get(tmpChar)-1);
                        }
                        break;
                    } 
                }
                Queue<Integer> tmpQueue = new ArrayDeque<>(indexQueue);
                while (true) {
                    if (tmpQueue.isEmpty()) {
                        break;
                    }
                    int index = tmpQueue.poll();
                    ch = s.charAt(index);
                    if (!tmpMap.containsKey(ch)) {
                        continue;
                    }
                    if (tmpMap.get(ch) == 1) {
                        tmpMap.remove(ch);
                    } else {
                        tmpMap.put(ch, tmpMap.get(ch)-1);
                    }
                }
            }
        }
        return rt;
    }

    public static void main(String[] args) {
        MinimumWindowSubstring76 instance = new MinimumWindowSubstring76();
        System.out.println(instance.minWindow("ADOBECODEBANC", "ABC"));
    }
}
