package in.amita.practice;
/*
Given a non-empty array of integers nums, every element appears twice except for one. Find that single one.
You must implement a solution with a linear runtime complexity and use only constant extra space.

Example 1:
Input: nums = [2,2,1]
Output: 1

Example 2:
Input: nums = [4,1,2,1,2]
Output: 4

Example 3:
Input: nums = [1]
Output: 1

 */

public class BMSingleNumber {
	/*
	2 properties are being used here:
	x^0 = x;
	y^y=0;
	i.e. git x^y^y = x;
	 */
	public int singleNumber(int[] nums){
		int x =0;
		for (int i: nums) {
			x = x^i;
		}
		return x;
	}
}
