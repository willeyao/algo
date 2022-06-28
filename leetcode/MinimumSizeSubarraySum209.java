package leetcode;

public class MinimumSizeSubarraySum209 {
    public int minSubArrayLen(int target, int[] nums) {
        int len = nums.length+1;
        int sum = 0;
        int right = 0;
        int left = 0;
        while(right<nums.length) {
            sum += nums[right++];
            if (sum >= target) {
                while (left <= right) {
                    sum -= nums[left++];
                    if (sum < target) {
                        break;
                    }
                }
                len = Math.min(len, right-left+1);
                if (len == 1) {
                    return len;
                }
            }
        }
        if (len > nums.length) {
            len = 0;
        }
        return len;
    }

    public static void main(String[] args) {
        MinimumSizeSubarraySum209 instance = new MinimumSizeSubarraySum209();
        System.out.println(instance.minSubArrayLen(7, new int[]{2,3,1,2,2,3}));
        System.out.println(instance.minSubArrayLen(4, new int[]{1,4,4}));
        System.out.println(instance.minSubArrayLen(11, new int[]{1,1,1,1,1,1,1,1}));
    }
}
