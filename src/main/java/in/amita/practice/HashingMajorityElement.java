/*
Given an array nums of size n, return the majority element.
The majority element is the element that appears more than ⌊n / 2⌋ times. You may assume that the majority element always exists in the array.

Example 1:
Input: nums = [3,2,3]
Output: 3

Example 2:
Input: nums = [2,2,1,1,1,2,2]
Output: 2
*/

class HashingMajorityElement {
    /*
      O(N) and O(N)
      This is not the most efficient solution. 
      Search Boyer-Moore Voting Algorithm that does it in O(N) and O(1). 
      There are other approaches as well.
      https://leetcode.com/problems/majority-element/solution/
    */
    
  public int majorityElement(int[] nums) {
        HashMap<Integer, Integer> majMap = new HashMap<>();
        
        double maj = nums.length/2;
        int majCount = (int) Math.floor(maj);
        for(int key: nums){
            int count = majMap.getOrDefault(key,0)+1;
            if(count > majCount) return key;
            majMap.put(key, count);
        }
        
        return -1;        
        
    }
}
