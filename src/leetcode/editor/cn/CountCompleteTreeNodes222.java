/**
 * 给你一棵 完全二叉树 的根节点 root ，求出该树的节点个数。
 * <p>
 * 完全二叉树 的定义如下：在完全二叉树中，除了最底层节点可能没填满外，其余每层节点数都达到最大值，并且最下面一层的节点都集中在该层最左边的若干位置。若最底层为第
 * h 层，则该层包含 1~ 2ʰ 个节点。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：root = [1,2,3,4,5,6]
 * 输出：6
 * <p>
 * <p>
 * 示例 2：
 * <p>
 * <p>
 * 输入：root = []
 * 输出：0
 * <p>
 * <p>
 * 示例 3：
 * <p>
 * <p>
 * 输入：root = [1]
 * 输出：1
 * <p>
 * <p>
 * <p>
 * <p>
 * 提示：
 * <p>
 * <p>
 * 树中节点的数目范围是[0, 5 * 10⁴]
 * 0 <= Node.val <= 5 * 10⁴
 * 题目数据保证输入的树是 完全二叉树
 * <p>
 * <p>
 * <p>
 * <p>
 * 进阶：遍历树来统计节点是一种时间复杂度为 O(n) 的简单解决方案。你可以设计一个更快的算法吗？
 * Related Topics 树 深度优先搜索 二分查找 二叉树 👍 609 👎 0
 */

package leetcode.editor.cn;

import leetcode.editor.cn.util.TreeNode;

import java.awt.event.HierarchyListener;

// 222 完全二叉树的节点个数
public class CountCompleteTreeNodes222 {
    public static void main(String[] args) {
        Solution solution = new CountCompleteTreeNodes222().new Solution();
        long startTime = System.currentTimeMillis();
        long endTime = System.currentTimeMillis();
        System.out.println("程序运行时间： " + (endTime - startTime) + "ms");
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //此处有重点：完全二叉树一定会有一端是满二叉树
        public int countNodes(TreeNode root) {
            //满二叉树算法
            TreeNode l = root, r = root;
            int lh = 0, rh = 0;
            while (l != null) {
                lh++;
                l = l.left;
            }
            while (r != null) {
                rh++;
                r = r.right;
            }
            if (lh == rh) return (int) Math.pow(2, lh) - 1;
            //普通二叉树算法，但其实只会计算一边，因为另一边是满二叉树
            return 1 + countNodes(root.left) + countNodes(root.right);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
