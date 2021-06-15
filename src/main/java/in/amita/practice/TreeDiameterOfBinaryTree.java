/**
 * Given the root of a binary tree, return the length of the diameter of the tree.
 * The diameter of a binary tree is the length of the longest path between any two nodes in a tree.
 * This path may or may not pass through the root.
 * The length of a path between two nodes is represented by the number of edges between them.
 *
 * Example 1:
 * Input: root = [1,2,3,4,5]
 * Output: 3
 * Explanation: 3is the length of the path [4,2,1,3] or [5,2,1,3].
 *
 * Example 2:
 * Input: root = [1,2]
 * Output: 1
 */

package in.amita.practice;
public class TreeDiameterOfBinaryTree {

    int maxDiameterSoFar;
  
    public int diameterOfBinaryTree(TreeNode root){
        maxDiameterSoFar = Integer.MIN_VALUE;
        int longestPath = traverse(root);
        return Math.max(longestPath, maxDiameterSoFar);
    }

    private int traverse(TreeNode root){
        if (root == null) return 0;
        int l = traverse(root.left);
        int r = traverse(root.right);
        int maxPath = Math.max(l, r) + 1;
        maxDiameterSoFar = Math.max(maxDiameterSoFar, l + r + 1);
        return maxPath;
    }

    /*
                   1
              2           3
           4    5       8
             6    7
     */
    private TreeNode createSampleTree(){
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.left.right.left = new TreeNode(6);
        root.left.right.right = new TreeNode(7);
        root.right.left = new TreeNode(8);
        return root;
    }

    public static void main(String[] args) {
        TreeDiameterOfBinaryTree obj = new TreeDiameterOfBinaryTree();
        TreeNode root = obj.createSampleTree();
        System.out.println(obj.diameterOfBinaryTree(root));
//        System.out.println(obj.diameterOfBinaryTree(null));
    }
  
    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int val) {
            this.val = val;
        }
    }
}
