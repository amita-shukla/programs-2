/*
Number of Islands
Given an m x n 2D binary grid grid which represents a map of '1's (land) and '0's (water), return the number of islands.
An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.

Example 1:
Input: grid = [
  ["1","1","1","1","0"],
  ["1","1","0","1","0"],
  ["1","1","0","0","0"],
  ["0","0","0","0","0"]
]
Output: 1

Example 2:
Input: grid = [
  ["1","1","0","0","0"],
  ["1","1","0","0","0"],
  ["0","0","1","0","0"],
  ["0","0","0","1","1"]
]
Output: 3
 */
package in.amita.practice;

public class GraphNumberOfIslands {
	boolean [][] visited;
	int m, n;
	public int numIslands(char[][] grid){
		m = grid.length;
		if(m == 0) return 0;
		n = grid[0].length;
		visited = new boolean[m][n];
		int num = 0;
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if(grid[i][j]=='1' && !visited[i][j]){
					num++;
					dfs(grid, i, j);
				}
			}
		}
		return num;
	}

	private void dfs(char[][] grid, int i, int j){
		visited[i][j] = true;
		if(j+1 < n  && grid[i][j+1]=='1' && !visited[i][j+1])
			dfs(grid, i, j+1);

		if(i+1 < m && grid[i+1][j]=='1' && !visited[i+1][j])
			dfs(grid, i+1, j);

		if(j-1 >= 0  && grid[i][j-1]=='1' && !visited[i][j-1])
			dfs(grid, i,j-1);

		if(i-1 >= 0 && grid[i-1][j]=='1' && !visited[i-1][j])
			dfs(grid, i-1, j);
	}

	public static void main(String[] args) {
		GraphNumberOfIslands obj = new GraphNumberOfIslands();
		char[][] grid1 = {
				{'1','1','1','1','0'},
				{'1','1','0','1','0'},
				{'1','1','0','0','0'},
				{'0','0','0','0','0'}};
		System.out.println(obj.numIslands(grid1));

		char[][] grid2 = {
				{'1','1','0','0','0'},
				{'1','1','0','0','0'},
				{'0','0','1','0','0'},
				{'0','0','0','1','1'}};
		System.out.println(obj.numIslands(grid2));

		// need to go backwards from row 2
		char[][] grid3 = {
				{'1','1','1'},
				{'0','1','0'},
				{'1','1','1'}
		};
		System.out.println(obj.numIslands(grid3));
	}
}
