package leetcode;

import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;

/**
 * leetcode第56题，区间合并
 * https://leetcode.cn/problems/merge-intervals/
 */
/**
 * 常规解题思路是分两步：
 * 1. 按区间最小值排序
 * 2. 双指针循环
 * 本题思路：
 * 1. 创建一个长度为所有区间最大值减最小值的数组
 * 2. 把区间分成长度为1的小区间，按小区间的最小值投影到数组对应的下标上
 * 执行速度超过99.45%的提交
 */
public class MergeIntervals56 {
    public int[][] merge(int[][] intervals) {
        // 寻找所有区间中最大的数字
        int max = 0;
        for (int[] interval : intervals) {
            max = Math.max(interval[1], max);
        }
        int[] arr = new int[max+1];
        Set<Integer> singleList = new HashSet<>();
        for (int[] interval : intervals) {
            // 处理数组中两个值相同的情况
            if(interval[0] == interval[1]) {
                singleList.add(interval[0]);
            }
            // 分隔成长度为1的小区间，区间的开始值为i，设置下标i的元素为1
            for (int i=interval[0];i<interval[1];i++) {
                arr[i] = 1;
            }
        }
        List<List<Integer>> ret = new ArrayList<>();
        if (singleList.size()>0) {
            for (Integer i : singleList) {
                // 区间开始和结束相同时，如果和前后区间都不连续，则单独构成一个区间
                if ((i==0 && arr[i] == 0) || (i>0&&arr[i-1] == 0&&arr[i] == 0)) {
                    List<Integer> inner = new ArrayList<>();
                    inner.add(i);
                    inner.add(i);
                    ret.add(inner);
                }
            }
        }
        List<Integer> tmpList = new ArrayList<>();
        for (int i=0;i<arr.length;i++) {
            if (arr[i] == 1) {
                tmpList.add(i);
            } else if (tmpList.size()>0){
                List<Integer> interval = new ArrayList<>(2);
                interval.add(tmpList.get(0));
                interval.add(tmpList.get(tmpList.size()-1)+1);
                ret.add(interval);
                tmpList.clear();
            }
        }
        int[][] retArr = new int[ret.size()][2];
        for (int i=0;i<ret.size();i++) {
            List<Integer> interval = ret.get(i);
            retArr[i][0] = interval.get(0);
            retArr[i][1] = interval.get(1);
        }
        return retArr;
    }
}