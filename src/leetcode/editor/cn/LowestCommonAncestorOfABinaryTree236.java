/**
给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。 

 百度百科中最近公共祖先的定义为：“对于有根树 T 的两个节点 p、q，最近公共祖先表示为一个节点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个
节点也可以是它自己的祖先）。” 

 

 示例 1： 

 
输入：root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
输出：3
解释：节点 5 和节点 1 的最近公共祖先是节点 3 。
 

 示例 2： 

 
输入：root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
输出：5
解释：节点 5 和节点 4 的最近公共祖先是节点 5 。因为根据定义最近公共祖先节点可以为节点本身。
 

 示例 3： 

 
输入：root = [1,2], p = 1, q = 2
输出：1
 

 

 提示： 

 
 树中节点数目在范围 [2, 10⁵] 内。 
 -10⁹ <= Node.val <= 10⁹ 
 所有 Node.val 互不相同 。 
 p != q 
 p 和 q 均存在于给定的二叉树中。 
 
 Related Topics 树 深度优先搜索 二叉树 👍 1509 👎 0

*/

package leetcode.editor.cn;

import leetcode.editor.cn.util.TreeNode;

// 236 二叉树的最近公共祖先
public class LowestCommonAncestorOfABinaryTree236{
    public static void main(String[] args) {
        Solution solution = new LowestCommonAncestorOfABinaryTree236().new Solution();
        long startTime=System.currentTimeMillis();
        long endTime=System.currentTimeMillis();
        System.out.println("程序运行时间： "+(endTime-startTime)+"ms");
    }
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        //如果root为null，则表示走到了叶节点，本分支找不到p或q
        if (root == null) return null;
        //如果找到了p或q，直接返回
        if (root == p || root == q) return root;
        //找到左侧的p或q
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        //找到右侧的p或q
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        //如果左侧木有，则表明p和q都在右侧，看右侧的递归结果
        if (left == null) return right;
        //同上
        if (right == null) return left;
        //假如左右都有，则当前节点就是公共祖先
        return root;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
