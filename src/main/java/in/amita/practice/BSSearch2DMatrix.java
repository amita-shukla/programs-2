package in.amita.practice;
/*
Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:
Integers in each row are sorted from left to right.
The first integer of each row is greater than the last integer of the previous row.

Example 1:
Input: matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 3
Output: true

Example 2:
Input: matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 13
Output: false

Think in terms of how you can reduce the search space.
if you start from the top left corner, or the bottom right corner,
you still need to search the entire matrix.
But, if you start from top right corner, you only need to either search in the column at bottom or row on the left

what's the time complexity? the worst case will be if the target is at the bottom left.
in that case you move from top to bottom and then bottom to left. Hence O(M+N)

https://www.youtube.com/watch?v=RhnsAlOkfYY&t=3719s
*/
class BSSearch2DMatrix {
    /*
             i  
    1  3  5  7  j
    10 11 16 20
    23 30 34 60
    */
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length;
        if(m==0) return false;
        int n = matrix[0].length;
        if(n==0) return false;
        
        if(target<matrix[0][0] || target>matrix[m-1][n-1]) return false;
        
        // start with the top-right corner
        int i=0, j=n-1;
        while(i<m && j>=0){
            if(matrix[i][j] == target){
                return true;
            } else if(matrix[i][j] < target){
                // if target is greater than current element, you can only go down 
                // (remember you can't go right coz you started from top right corner)
                i++;
            }else {
              // if target is less than current element, you can only go left
              // again, you can't go up coz you started from top right
              j--;  
            }
        }
        return false;
    }
}
