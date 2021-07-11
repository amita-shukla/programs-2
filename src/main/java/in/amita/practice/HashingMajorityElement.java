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
    
    /*
    Below is the Moore's voting algorithm.
    O(n) & O(1)
    */
    public int majorityElement(int[] nums) {
        int potentialMaj = nums[0];
        int majCount = 1;
        for(int i=1; i<nums.length; i++){
            // when the count is zero, 
            // that means that total number of elements other than the candidate
            // have turned up more than (or equal to) the candidate element.
            // hence a new element takes the place of the candidate
            if(majCount==0) potentialMaj = nums[i];
            
            // if you've found a distinct element, cancel it out by decreasing the count 
            if(nums[i]!=potentialMaj){
                majCount--;
            }else{
                majCount++;
            }
        }
        
        // moore's voting algorithm may be wrong if there's no element present/
        // but since the problem says that majority element is always present,
        // we don't need to confirm
        return potentialMaj;
    }
}
