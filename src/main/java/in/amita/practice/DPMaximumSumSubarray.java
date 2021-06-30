package in.amita.practice;

import java.util.Arrays;

/*
Given an integer array nums, find the contiguous subarray (containing at least one number) which has the largest sum and return its sum.

Example 1:
Input: nums = [-2,1,-3,4,-1,2,1,-5,4]
Output: 6
Explanation: [4,-1,2,1] has the largest sum = 6.

Example 2:
Input: nums = [1]
Output: 1

Example 3:
Input: nums = [5,4,-1,7,8]
Output: 23

 */
/*
THIS IS KADANE'S ALGORITHM.

 */
public class DPMaximumSumSubarray {
	public int maxSumArray(int[] nums) {
		if(nums==null || nums.length==0) return 0;

		int n = nums.length;
		int globalMax = Integer.MIN_VALUE;
		int maxSoFar = 0;

		for (int i = 0; i < n; i++) {
			maxSoFar = maxSoFar+nums[i];
			globalMax = Math.max(maxSoFar, globalMax);
			if(maxSoFar<0) maxSoFar =0;
		}

		return globalMax;
	}

	// I have not tested if the array returned is correct except for 1 case below...
	public int[] maxSumArrayPrint(int[] nums){
		int globalMax = Integer.MIN_VALUE;
		int maxSoFar = 0;
		int start = 0, end = 0;
		int s = 0;
		for (int i = 0; i < nums.length; i++) {
			maxSoFar = maxSoFar + nums[i];
			if(globalMax<maxSoFar){
				globalMax = maxSoFar;
				end = i;
			}
			if(maxSoFar<0) {
				maxSoFar = 0;
				start = i+1;
			}
		}

		int[] res = new int[end-start+1];
		for (int i = start,j=0; i <=end ; i++,j++) {
			res[j] = nums[i];
		}
		return res;
	}

	public static void main(String[] args) {
		DPMaximumSumSubarray obj = new DPMaximumSumSubarray();
		System.out.println(obj.maxSumArray(new int[]{-2,1,-3,4,-1,2,1,-5,4})); // 6
		System.out.println(Arrays.toString(obj.maxSumArrayPrint(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4}))); // [4,-1,2,1]
	}

}
