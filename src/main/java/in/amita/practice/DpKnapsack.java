package in.amita.practice;
/*
Given two integer arrays A and B of size N each which represent values and weights associated with N items respectively.
Also given an integer C which represents knapsack capacity.
Find out the maximum value subset of A such that sum of the weights of this subset is smaller than or equal to C.
NOTE:
    You cannot break an item, either pick the complete item, or don’t pick it (0-1 property).
Example Input
Input 1:
 A = [60, 100, 120]
 B = [10, 20, 30]
 C = 50

Input 2:
 A = [10, 20, 30, 40]
 B = [12, 13, 15, 19]
 C = 10

Example Output
Output 1:
 220

Output 2:
 0
Example Explanation
Explanation 1:
 Taking items with weight 20 and 30 will give us the maximum value i.e 100 + 120 = 220

Explanation 2:
 Knapsack capacity is 10 but each item has weight greater than 10 so no items can be considered in the knapsack therefore answer is 0.
 */
public class DpKnapsack {
	int W;

	public int solve(int[] A, int[] B, int C) {
		W = C;

		if(W==0 || A.length==0) return 0;

//		return knapSack(A, B, 0, 0);
		return knapsackDp(A, B, W);
	}

	public int knapSack(int[] vals, int[] wts, int ind, int currWt){
		if(ind == vals.length) return 0;

		int val = vals[ind];
		int wt = wts[ind];

		if(W < currWt+wt) return knapSack(vals, wts, ind+1, currWt);

		return Math.max(val + knapSack(vals, wts, ind+1, currWt + wt), knapSack(vals, wts, ind+1, currWt));
	}

	public int knapsackDp(int[] vals, int[] wts, int W){
		int[][] dp = new int[vals.length+1][W+1];

		for (int i = 0; i <= vals.length; i++) {
			for(int j = 0; j<=W; j++){
				if(i==0 || j==0) dp[i][j] = 0;
				else {
					// if wt of ith item (adjusting it for 1-index here) < cum wt till now (i.e. j)
					if(wts[i-1]<=j) {
						dp[i][j] = Math.max(
								// add the current item
								// val of current item (again 1-indx corrected) + max val till the previous item
								vals[i - 1] + dp[i - 1][j - wts[i - 1]],
								// skip the item, take whatever value was for i-1th row
								dp[i - 1][j]);
					}
					else {
						dp[i][j] = dp[i - 1][j];
					}
				}
			}

		}
		return dp[vals.length][W];
	}

	public static void main(String[] args) {
		DpKnapsack obj = new DpKnapsack();
		int[] vals = { 468, 335, 501, 170, 725, 479, 359, 963, 465, 706, 146, 282, 828, 962, 492, 996, 943, 828, 437, 392, 605, 903, 154, 293, 383, 422, 717, 719, 896, 448, 727, 772, 539, 870, 913, 668, 300, 36, 895, 704, 812, 323 };
		int[] wts = { 4, 4, 5, 2, 2, 4, 9, 8, 5, 3, 8, 8, 10, 4, 2, 10, 9, 7, 6, 1, 3, 9, 7, 1, 3, 5, 9, 7, 6, 1, 10, 1, 1, 7, 2, 4, 9, 10, 4, 5, 5, 7 };
//		System.out.println(obj.solve(vals, wts, 841)); // expected: 24576
		System.out.println(obj.solve(new int[]{1,2,3}, new int[]{1,4,2}, 5));
		// how DP looks for above example
		/*
	v w i  0 1 2 3 4 5
	0 0	0 |0 0 0 0 0 0
	1 1	1 |0 1 1 1 1 1
	2	4 2 |0 1 1 1 2 3
	3	2 3 |0 1 3 4 4 4
   */
	}
}
