package in.amita.practice;

public class TreeFlipEquivalentTrees {
	public boolean flipEquiv(TreeNode root1, TreeNode root2) {
		if(root1 == null && root2 == null) return true;
		if(root1 == null || root2 == null) return false;

		if(root1.val != root2.val) return false;
		if(isLeafNode(root1) && isLeafNode(root2)) return true;

		boolean toFlip = false;
		if(root1.left==null && root1.right!=null && root2.left!=null && root2.right==null && root1.right.val==root2.left.val){
			toFlip = true;
		}

		if(root1.left!=null && root1.right==null && root2.left==null && root2.right!=null && root1.left.val==root2.right.val){
			toFlip = true;
		}

		if(root1.left!=null && root1.right!=null && root2.left!=null && root2.right!=null){
			if(root1.left.val == root2.right.val && root1.right.val == root2.left.val){
				toFlip = true;
			} else {
				toFlip = false;
			}
		}

		if(toFlip) {
			//flip root1
			TreeNode temp = root1.left;
			root1.left = root1.right;
			root1.right = temp;
		}

		return flipEquiv(root1.left, root2.left) && flipEquiv(root1.right, root2.right);
	}

/*
	private boolean isFlippable(TreeNode root1, TreeNode root2) {
		boolean flip = false;
		if (root1.left == null && root2.right == null) {

		}
		if(root1.right == null && root2.left == null) {

		}
	}
*/
	private boolean isLeafNode(TreeNode root){
		return root.left == null && root.right == null;
	}
	public static void main(String[] args) {
		TreeFlipEquivalentTrees obj = new TreeFlipEquivalentTrees();
		TreeNode root1 = obj.new TreeNode(1, obj.new TreeNode(
				2, obj.new TreeNode(4), obj. new TreeNode(5, obj.new TreeNode(7),
				obj.new TreeNode(8))), obj.new TreeNode(3, obj. new TreeNode(6), null));
		TreeNode root2 = obj.new TreeNode(1, obj.new TreeNode(3, null, obj.new TreeNode(6)),
				obj.new TreeNode(2, obj.new TreeNode(4), obj.new TreeNode(
						5, obj.new TreeNode(8), obj.new TreeNode(7))));

		System.out.println(obj.flipEquiv(root1, root2));

		System.out.println(obj.flipEquiv(null, null));
		System.out.println(obj.flipEquiv(null, obj.new TreeNode(1)));
		System.out.println(obj.flipEquiv(obj.new TreeNode(0, null, obj.new TreeNode(1)), null));
		System.out.println(obj.flipEquiv(obj.new TreeNode(0, null, obj.new TreeNode(1)),
				obj.new TreeNode(0, obj.new TreeNode(1), null)));
	}
	class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		TreeNode(int val) {this.val = val;}
		TreeNode(int val, TreeNode left, TreeNode right) {
			this.val = val;
			this.left = left;
			this.right = right;
		}
	}
}
/*
The Solution:
Approach 1:
You didn't actually need to flip. Just make some extra recursions

public boolean flipEquiv(TreeNode root1, TreeNode root2) {
	if (root1 == root2)
  	return true;
  if (root1 == null || root2 == null || root1.val != root2.val)
  	return false;

return (flipEquiv(root1.left, root2.left) && flipEquiv(root1.right, root2.right) ||
        flipEquiv(root1.left, root2.right) && flipEquiv(root1.right, root2.left));
}

Approach 2:
Make something called a Canonical Representation. Flip each node so that the left child is smaller than the right, and call this the canonical representation.
All equivalent trees have exactly one canonical representation.

public boolean flipEquiv(TreeNode root1, TreeNode root2) {
	List<Integer> vals1 = new ArrayList();
  List<Integer> vals2 = new ArrayList();
  dfs(root1, vals1);
  dfs(root2, vals2);
  return vals1.equals(vals2);
}

public void dfs(TreeNode node, List<Integer> vals) {
	if (node != null) {
  	vals.add(node.val);
    int L = node.left != null ? node.left.val : -1;
    int R = node.right != null ? node.right.val : -1;

  	if (L < R) {
    	dfs(node.left, vals);
      dfs(node.right, vals);
    } else {
    	dfs(node.right, vals);
      dfs(node.left, vals);
    }

    vals.add(null);
  }
}

 */
