/*
Given a string s, find the longest palindromic subsequence's length in s.
A subsequence is a sequence that can be derived from another sequence by deleting some or no elements without changing the order of the remaining elements.

Example 1:
Input: s = "bbbab"
Output: 4
Explanation: One possible longest palindromic subsequence is "bbbb".

Example 2:
Input: s = "cbbd"
Output: 2
Explanation: One possible longest palindromic subsequence is "bb".
*/

class DPLPS {
    public int longestPalindromeSubsequence(String s) {
        String t = reverse(s);
        // longest palindromic subsequence is nothing but 
      // longest common subsequence of the string and it's reverse!
        return longestCommonSubsequence(s, t);
    }
    
    private String reverse(String s){
        return new StringBuilder(s).reverse().toString();
    }
    
    private int longestCommonSubsequence(String text1, String text2) {
		int m = text1.length();
		int n = text2.length();
		
		int[][] dp = new int[m+1][n+1];

		for(int i=1; i<=m; i++){
			for(int j=1; j<=n; j++){
				if(text1.charAt(i-1)==text2.charAt(j-1)){
					dp[i][j] = dp[i-1][j-1] + 1;
				}else{
					dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
				}
			}
		}
		return dp[m][n];
	}
}
