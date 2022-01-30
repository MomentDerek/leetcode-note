/**
给定二叉搜索树（BST）的根节点 root 和一个整数值 val。 

 你需要在 BST 中找到节点值等于 val 的节点。 返回以该节点为根的子树。 如果节点不存在，则返回 null 。 

 

 示例 1: 

 

 
输入：root = [4,2,7,1,3], val = 2
输出：[2,1,3]
 

 Example 2: 

 
输入：root = [4,2,7,1,3], val = 5
输出：[]
 

 

 提示： 

 
 数中节点数在 [1, 5000] 范围内 
 1 <= Node.val <= 10⁷ 
 root 是二叉搜索树 
 1 <= val <= 10⁷ 
 
 Related Topics 树 二叉搜索树 二叉树 👍 234 👎 0

*/

package leetcode.editor.cn;

import leetcode.editor.cn.util.TreeNode;

// 700 二叉搜索树中的搜索
public class SearchInABinarySearchTree700{
    public static void main(String[] args) {
        Solution solution = new SearchInABinarySearchTree700().new Solution();
        long startTime=System.currentTimeMillis();
        long endTime=System.currentTimeMillis();
        System.out.println("程序运行时间： "+(endTime-startTime)+"ms");
    }
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public TreeNode searchBST(TreeNode root, int val) {
        if (root == null) return null;
        int rootVal = root.val;
        if (rootVal == val) return root;
        if (val > rootVal) return searchBST(root.right, val);
        return searchBST(root.left, val);
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
