/*

You are given an integer array coins representing coins of different denominations and an integer amount representing a total amount of money.
Return the number of combinations that make up that amount. If that amount of money cannot be made up by any combination of the coins, return 0.
You may assume that you have an infinite number of each kind of coin.
The answer is guaranteed to fit into a signed 32-bit integer.

Example 1:
Input: amount = 5, coins = [1,2,5]
Output: 4
Explanation: there are four ways to make up the amount:
5=5
5=2+2+1
5=2+1+1+1
5=1+1+1+1+1

Example 2:
Input: amount = 3, coins = [2]
Output: 0
Explanation: the amount of 3 cannot be made up just with coins of 2.

Example 3:
Input: amount = 10, coins = [10]
Output: 1

This is a simple Variation of unbounded Knapsack problem
*/
class DPCointChange2 {
    public int change(int amount, int[] coins) {
        int m = coins.length;
        int n = amount;
        
        int[] dp = new int[n+1];
        dp[0] = 1;
        
        for(int i=1; i<=m; i++){
            for(int j=1; j<=n; j++){
                if(coins[i-1] <= j){
                    dp[j] += dp[j-coins[i-1]];
                }
            }
        }
        
        return dp[n];
    }
    
  /*
  initialization:
   |   0 1 2 3 4 5
  0| 0 1 0 0 0 0 0 
  1| 1 1
  2| 2 1
  5| 3 1
  */
    public int change2D(int amount, int[] coins) {
        int m = coins.length;
        int n = amount;
        
        int[][] dp = new int[m+1][n+1];
        for(int j =0; j<=n; j++){
            dp[0][j] = 0;
        }
        for(int i=0; i<=m; i++){
            dp[i][0] = 1;
        }
        
        for(int i=1; i<=m; i++){
            for(int j=1; j<=n; j++){
                if(coins[i-1] <= j){
                  // the only difference from bounded knapsack is the dp[i][j-coins[i-1]] instead of dp[i-1][j-coins[i-1]];
                    dp[i][j] = dp[i-1][j] + dp[i][j-coins[i-1]];
                } else {
                    dp[i][j] = dp[i-1][j];
                }
            }
        }
        return dp[m][n];
    }
}
