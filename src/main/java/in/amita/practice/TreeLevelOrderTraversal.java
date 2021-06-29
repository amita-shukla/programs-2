package in.amita.practice;

import java.util.*;

/*

THIS is one of the most basic problems of Tree/Graph:

https://leetcode.com/problems/binary-tree-level-order-traversal/
Given the root of a binary tree, return the level order traversal of its nodes' values. (i.e., from left to right, level by level).
Example 1:
Input: root = [3,9,20,null,null,15,7]
Output: [[3],[9,20],[15,7]]

Example 2:
Input: root = [1]
Output: [[1]]

Example 3:
Input: root = []
Output: []

 */
public class TreeLevelOrderTraversal {
	public List<List<Integer>> levelOrder(TreeNode root) {
		if(root==null) return new ArrayList<>();
		List<List<Integer>> res = new ArrayList<>();

		Queue<TreeNode> q = new LinkedList<>();
		q.add(root);

		while(!q.isEmpty()){
			// This SIZE makes all the difference!!
			int size = q.size();
			List<Integer> level = new ArrayList<>();
			for(int i=0; i< size; i++) {
				TreeNode node =  q.remove();
				level.add(node.val);
				if(node.left!=null) q.add(node.left);
				if(node.right!=null) q.add(node.right);
			}
			res.add(level);
		}
		return res;
	}

	class TreeNode{
		int val;
		TreeNode left;
		TreeNode right;
	}
}
