package leetcode;

public class MinimumWindowSubstring76 {

    /**
     * 性能优化
     * key数量有限时，可以用数字代替map，数组下标为key
     * @param s
     * @param t
     * @return
     */
    public String minWindow(String s, String t) {
        if (s.length() < t.length()) {
            return "";
        }
        int sLen = s.length();
        int tLen = t.length();
        int[] charMap = new int[128];
        for (char ch : t.toCharArray()) {
            charMap[ch]--;
        }
        int left = 0;
        int count = 0;
        String rt = "";
        for (int i=0;i<sLen;i++) {
            charMap[s.charAt(i)]++;
            if (charMap[s.charAt(i)] <= 0) {
                count++;
            }
            if (count == tLen) {
                while (left <= i && charMap[s.charAt(left)] > 0) {
                    charMap[s.charAt(left)]--;
                    left++; 
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
