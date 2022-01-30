/**
 * 给定二叉搜索树（BST）的根节点 root 和要插入树中的值 value ，将值插入二叉搜索树。 返回插入后二叉搜索树的根节点。 输入数据 保证 ，新值和原始二
 * 叉搜索树中的任意节点值都不同。
 * <p>
 * 注意，可能存在多种有效的插入方式，只要树在插入后仍保持为二叉搜索树即可。 你可以返回 任意有效的结果 。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：root = [4,2,7,1,3], val = 5
 * 输出：[4,2,7,1,3,5]
 * 解释：另一个满足题目要求可以通过的树是：
 * <p>
 * <p>
 * <p>
 * 示例 2：
 * <p>
 * <p>
 * 输入：root = [40,20,60,10,30,50,70], val = 25
 * 输出：[40,20,60,10,30,50,70,null,null,25]
 * <p>
 * <p>
 * 示例 3：
 * <p>
 * <p>
 * 输入：root = [4,2,7,1,3,null,null,null,null,null,null], val = 5
 * 输出：[4,2,7,1,3,5]
 * <p>
 * <p>
 * <p>
 * <p>
 * 提示：
 * <p>
 * <p>
 * 树中的节点数将在 [0, 10⁴]的范围内。
 * -10⁸ <= Node.val <= 10⁸
 * 所有值 Node.val 是 独一无二 的。
 * -10⁸ <= val <= 10⁸
 * 保证 val 在原始BST中不存在。
 * <p>
 * Related Topics 树 二叉搜索树 二叉树 👍 256 👎 0
 */

package leetcode.editor.cn;

import leetcode.editor.cn.util.TreeNode;

// 701 二叉搜索树中的插入操作
public class InsertIntoABinarySearchTree701 {
    public static void main(String[] args) {
        Solution solution = new InsertIntoABinarySearchTree701().new Solution();
        long startTime = System.currentTimeMillis();
        long endTime = System.currentTimeMillis();
        System.out.println("程序运行时间： " + (endTime - startTime) + "ms");
    }
//leetcode submit region begin(Prohibit modification and deletion)
    //递归写法
    class Solution {
        public TreeNode insertIntoBST(TreeNode root, int val) {
            if (root == null) return new TreeNode(val);
            if (val > root.val)
                root.right = insertIntoBST(root.right, val);
            else if (val < root.val)
                root.left = insertIntoBST(root.left, val);
            return root;
        }
    }
    //迭代写法
    class Solution1 {
        public TreeNode insertIntoBST(TreeNode root, int val) {
            if (root == null) return new TreeNode(val);
            //用于记录当前位置
            TreeNode position = root;
            while (true) {
                if (val > position.val) {
                    if (position.right == null) {
                        position.right = new TreeNode(val);
                        return root;
                    }
                    position = position.right;
                }
                else if (val < position.val) {
                    if (position.left == null) {
                        position.left = new TreeNode(val);
                        return root;
                    }
                    position = position.left;
                }
                else return root;
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
