/*
  A path in a binary tree is a sequence of nodes where each pair of adjacent nodes in the sequence has an edge connecting them. A node can only appear in the sequence at most once. Note that the path does not need to pass through the root.
  The path sum of a path is the sum of the node's values in the path.
  Given the root of a binary tree, return the maximum path sum of any path.

  Example 1:
  Input: root = [1,2,3]
  Output: 6
  Explanation: The optimal path is 2 -> 1 -> 3 with a path sum of 2 + 1 + 3 = 6.

  Example 2:
  Input: root = [-10,9,20,null,null,15,7]
  Output: 42
  Explanation: The optimal path is 15 -> 20 -> 7 with a path sum of 15 + 20 + 7 = 42.
 */
package in.amita.practice;

public class TreeMaximumPathSum {
	int maxPathWithRoot;
	int maxPathWithoutRoot;
	public int maxPathSum(TreeNode root){
		maxPathWithRoot = Integer.MIN_VALUE;
		maxPathWithoutRoot = Integer.MIN_VALUE;
		traverse(root);
		return Math.max(maxPathWithRoot, maxPathWithoutRoot);
	}

	public int traverse(TreeNode root){
		if (root == null) return 0;
		int lMax = traverse(root.left);
		int rMax = traverse(root.right);
		int max = Math.max(Math.max(lMax, rMax), 0) + root.val; // don't choose left or right nodes if they're negative, i.e. do a max(..,0)
		maxPathWithRoot = Math.max(maxPathWithRoot, lMax + rMax + root.val);
		maxPathWithoutRoot = Math.max(maxPathWithoutRoot, max);
		return max;
	}

	private TreeNode createTree(){
		return new TreeNode(1, new TreeNode(2), new TreeNode(3));
	}

	private TreeNode createTree2(){
		return new TreeNode(-10,
				new TreeNode(9), new TreeNode(20,
					new TreeNode(15), new TreeNode(7)));
	}

	public static void main(String[] args) {
		TreeMaximumPathSum obj = new TreeMaximumPathSum();
		TreeNode root = obj.createTree();
		System.out.println(obj.maxPathSum(root));

		TreeNode root2 = obj.createTree2();
		System.out.println(obj.maxPathSum(root2));

		System.out.println(obj.maxPathSum(obj.new TreeNode(2, obj.new TreeNode(-1), obj.new TreeNode(-2))));


	}

	class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode(){}
		TreeNode(int val) {this.val = val;}
		TreeNode(int val, TreeNode left, TreeNode right) {
			this.val = val;
			this.left = left;
			this.right = right;
		}
	}
}
