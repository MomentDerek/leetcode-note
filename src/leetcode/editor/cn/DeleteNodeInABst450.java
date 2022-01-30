/**
 * 给定一个二叉搜索树的根节点 root 和一个值 key，删除二叉搜索树中的 key 对应的节点，并保证二叉搜索树的性质不变。返回二叉搜索树（有可能被更新）的根节
 * 点的引用。
 * <p>
 * 一般来说，删除节点可分为两个步骤：
 * <p>
 * <p>
 * 首先找到需要删除的节点；
 * 如果找到了，删除它。
 * <p>
 * <p>
 * <p>
 * <p>
 * 示例 1:
 * <p>
 * <p>
 * <p>
 * <p>
 * 输入：root = [5,3,6,2,4,null,7], key = 3
 * 输出：[5,4,6,2,null,null,7]
 * 解释：给定需要删除的节点值是 3，所以我们首先找到 3 这个节点，然后删除它。
 * 一个正确的答案是 [5,4,6,2,null,null,7], 如下图所示。
 * 另一个正确答案是 [5,2,6,null,4,null,7]。
 * <p>
 * <p>
 * <p>
 * <p>
 * 示例 2:
 * <p>
 * <p>
 * 输入: root = [5,3,6,2,4,null,7], key = 0
 * 输出: [5,3,6,2,4,null,7]
 * 解释: 二叉树不包含值为 0 的节点
 * <p>
 * <p>
 * 示例 3:
 * <p>
 * <p>
 * 输入: root = [], key = 0
 * 输出: []
 * <p>
 * <p>
 * <p>
 * 提示:
 * <p>
 * <p>
 * 节点数的范围 [0, 10⁴].
 * -10⁵ <= Node.val <= 10⁵
 * 节点值唯一
 * root 是合法的二叉搜索树
 * -10⁵ <= key <= 10⁵
 * <p>
 * <p>
 * <p>
 * <p>
 * 进阶： 要求算法时间复杂度为 O(h)，h 为树的高度。
 * Related Topics 树 二叉搜索树 二叉树 👍 628 👎 0
 */

package leetcode.editor.cn;

import leetcode.editor.cn.util.TreeNode;

import static leetcode.editor.cn.util.TreeUtils.mkTree;
import static leetcode.editor.cn.util.TreeUtils.showImage;

// 450 删除二叉搜索树中的节点
public class DeleteNodeInABst450 {
    public static void main(String[] args) {
        Solution solution = new DeleteNodeInABst450().new Solution();
        String input = "[8,0,31,null,6,28,45,1,7,25,30,32,49,null,4,null,null,9,26,29,null,null,42,47,null,2,5,null,12,null,27,null,null,41,43,46,48,null,3,null,null,10,19,null,null,33,null,null,44,null,null,null,null,null,null,null,11,18,20,null,37,null,null,null,null,14,null,null,22,36,38,13,15,21,24,34,null,null,39,null,null,null,16,null,null,23,null,null,35,null,40,null,17]";
        showImage(input);
        System.out.println("");
        System.out.println("----------");
        System.out.println("");
        long startTime = System.currentTimeMillis();
        showImage(solution.deleteNode(mkTree(input), 1));
        long endTime = System.currentTimeMillis();
        System.out.println("程序运行时间： " + (endTime - startTime) + "ms");
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public TreeNode deleteNode(TreeNode root, int key) {
            if (root == null) return null;
            //将当前位置设置为root，当前位置的父节点为空
            TreeNode pos = root, pre = null;
            while (pos != null) {
                //找到需要删除的节点
                if (key == pos.val) break;
                //记录当前节点位置到pre后，将位置移到下一层
                pre = pos;
                if (key > pos.val) pos = pos.right;
                else pos = pos.left;
            }
            //没找到
            if (pos == null) return root;
            //要删除的节点没有子节点
            if (pos.left == null && pos.right == null) {
                //因为pre为null，所以找到的节点是根节点
                if (pre == null) {
                    //删除根节点后只剩下null
                    return null;
                }
                //将当前节点替换为null
                changeChildNode(pre, pos, null);
                return root;
            }
            //只有左子节点
            if (pos.right == null) {
                //找到的是根节点，删除后直接返回左子树
                if (pre == null) return pos.left;
                //将当前节点替换成左子节点
                changeChildNode(pre, pos, pos.left);
                return root;
            }
            //只有右子节点
            if (pos.left == null) {
                //找到的是根节点，删除后直接返回右子树
                if (pre == null) return pos.right;
                //将当前节点替换成右子节点
                changeChildNode(pre, pos, pos.right);
                return root;
            }
            //左右节点同时存在，此处选择将左子树的最大值替换到当前节点（也可以选择右子树的最小节点）
            //开始遍历左子树
            //遍历的当前位置
            TreeNode leftMax = pos.left;
            //遍历的父节点就是需要删除的节点
            TreeNode maxParent = pos;
            //左子树的最大值就是左子树的最右端
            while (leftMax.right != null) {
                maxParent = leftMax;
                leftMax = leftMax.right;
            }
            //替换值
            pos.val = leftMax.val;
            //将左子树的最大值节点替换成它的左子树（可能是null），因为最大值已经是最右端了，没有右子树
            changeChildNode(maxParent, leftMax, leftMax.left);
            return root;
        }

        /**
         * 已知父节点与子节点，将子节点替换成一个新的节点
         *
         * @param parentNode 父节点
         * @param sonNode    子节点
         * @param newNode    替换的子节点
         */
        private void changeChildNode(TreeNode parentNode, TreeNode sonNode, TreeNode newNode) {
            if (parentNode.left == sonNode) parentNode.left = newNode;
            else if (parentNode.right == sonNode) parentNode.right = newNode;
        }
    }

    //递归写法
    class Solution1 {
        public TreeNode deleteNode(TreeNode root, int key) {
            if (root == null) return null;
            if (key == root.val) {
                if (root.left == null && root.right == null) return null;
                if (root.right == null) return root.left;
                if (root.left == null) return root.right;
                TreeNode max = root.left;
                while (max.right!=null) max = max.right;
                root.val = max.val;
                root.left = deleteNode(root.left, max.val);
            }
            if (key > root.val) root.right = deleteNode(root.right, key);
            if (key < root.val) root.left = deleteNode(root.left, key);
            return root;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
