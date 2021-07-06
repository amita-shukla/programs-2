/*
Given a string A, find length of the longest repeating sub-sequence such that the two subsequence don’t have same string character at same position,
i.e., any i’th character in the two subsequences shouldn’t have the same index in the original string.
NOTE: Sub-sequence length should be greater than or equal to 2.

Output Format
Return an integer, 0 or 1:
    => 0 : False
    => 1 : True

Example Input
Input 1:
 A = "abab"
Input 2:
 A = "abba"
Example Output
Output 1:
 1
Output 2:
 0
Example Explanation
Explanation 1:
 "ab" is repeated.
Explanation 2:
 There is no repeating subsequence.
------------------------------------

This problem is similar to LCS, we need to find the longest common subsequence where the two strings are the same,
but we need to skip the case where matching characters are at the same index
  0 a b a b
0         0
a       0
b     0
a   0
b 0 

*/
public class DPLRS {
    public int longestRepeatingSubsequence(String s) {
        int n = s.length();
        
        int[][] dp = new int[n+1][n+1];
        
        for(int i=1; i<=n; i++){
            for (int j=1; j<=n; j++){
                if(s.charAt(i-1)==s.charAt(j-1) && i!=j){
                    dp[i][j] = 1+ dp[i-1][j-1];
                }else{
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }
        
        return dp[n][n]<2 ? 0 : 1;
    }
}
