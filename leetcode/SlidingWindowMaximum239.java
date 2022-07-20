package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SlidingWindowMaximum239 {
    public int[] maxSlidingWindow(int[] nums, int k) {
        List<Integer> list = new ArrayList<>(nums.length);
        for (int i=0;i<nums.length;i++){
            list.add(nums[i]);
        }
        k = Math.min(k, nums.length);
        int len = nums.length - k + 1;
        int[] rt = new int[len];
        int right = 0;
        for(int i=0;i<len;i++) {
            right = i+k;
            rt[i] = Collections.max(list.subList(i, right));
        }
        return rt;
    }

    public static void main(String[] args) {
        SlidingWindowMaximum239 slidingWindowMaximum239 = new SlidingWindowMaximum239();
        System.out.println(Arrays.toString(slidingWindowMaximum239.maxSlidingWindow(new int[]{1},3)));
    }
}