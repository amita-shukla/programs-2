/**
Simple  question:
Given the root of a binary tree, return the sum of all left leaves.

Example 1:
Input: root = [3,9,20,null,null,15,7]
Output: 24
Explanation: There are two left leaves in the binary tree, with values 9 and 15 respectively.

Example 2:
Input: root = [1]
Output: 0
 */
class TreeSumOfLeftLeaves {
    int sum;
    public int sumOfLeftLeaves(TreeNode root) {
        traverse(root, false);
        return sum;
    }

    private void traverse(TreeNode root, boolean isLeft){
        if(root==null) return;

        if(root.left ==null && root.right==null){
            if(isLeft){
                sum += root.val;
            }
        }

        traverse(root.left, true);
        traverse(root.right, false);

    }

    public static void main(String[] args){
        TreeSumOfLeftLeaves obj = new TreeSumOfLeftLeaves();
        TreeNode root = obj.new TreeNode(9, null, obj.new TreeNode(2, obj.new TreeNode(0, obj.new TreeNode(-7, obj.new TreeNode(-1), null), null), null));
        System.out.println(obj.sumOfLeftLeaves(root));
    }

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

}
