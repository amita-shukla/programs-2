/*
416. Partition Equal Subset Sum
Given a non-empty array nums containing only positive integers, find if the array can be partitioned into two subsets such that the sum of elements in both subsets is equal.
Example 1:
Input: nums = [1,5,11,5]
Output: true
Explanation: The array can be partitioned as [1, 5, 5] and [11].

Example 2:
Input: nums = [1,2,3,5]
Output: false
Explanation: The array cannot be partitioned into equal sum subsets.
*/
class DPPartitionEqualSubsetSum {
    public boolean canPartition(int[] nums) {
        int sum = 0;
        for(int i=0; i<nums.length; i++){
            sum += nums[i];
        }
        
        // if the sum of elements is odd, then can't partition into 2 sets
        if(sum%2!=0) return false;
        
        // if we can find one subset whose sum = totalsum/2
        // then that means we have 2 subsets with equal sum!
        return subsetSum(nums, sum/2);
    }
    
    private boolean subsetSum(int[] nums, int target){
                
        int m = nums.length;
        int n = target;
        
        boolean[][] dp = new boolean[m+1][n+1];
        
        dp[0][0] = true;
        
        // 0-1 similar to knap sack
        for(int i=1; i<=m; i++){
            for(int j=1; j<=n; j++){
                if(nums[i-1] <= j){
                    dp[i][j] = dp[i-1][j] || dp[i-1][j-nums[i-1]];
                } else {
                    dp[i][j] = dp[i-1][j];
                }
            }
        }
        
        return dp[m][n];
    }
}

/*
Approach 4: Optimised Dynamic Programming - Using 1D Array

We could further optimize Approach 3. We must understand that for any array element ii, we need results of the previous iteration (i-1) only. Hence, we could achieve the same using a one-dimensional array as well.

public boolean canPartition(int[] nums) {
        if (nums.length == 0)
            return false;
        int totalSum = 0;
        // find sum of all array elements
        for (int num : nums) {
            totalSum += num;
        }
        // if totalSum is odd, it cannot be partitioned into equal sum subset
        if (totalSum % 2 != 0) return false;
        int subSetSum = totalSum / 2;
        boolean dp[] = new boolean[subSetSum + 1];
        dp[0] = true;
        for (int curr : nums) {
            for (int j = subSetSum; j >= curr; j--) {
                dp[j] |= dp[j - curr];
            }
        }
        return dp[subSetSum];
}
*/
