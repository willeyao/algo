package leetcode;

import java.util.Map;
import java.util.HashMap;

/**
 * 两数之和
 * https://leetcode.cn/problems/two-sum/
 */
public class TwoSum1 {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer,Integer> hash = new HashMap<>();
        int[] ret = new int[2];
        for (int i=0;i<nums.length;i++) {
            hash.put(nums[i], i);
        }
        for (int i=0;i<nums.length;i++) {
            int num = nums[i];
            int num2 = target - num;
            if (hash.containsKey(num2) && i!=hash.get(num2)) {
                ret[0] = i;
                ret[1] = hash.get(num2);
            }
        }
        return ret;
    }
}
