/**
 * <p>给定一个二叉树，找出其最小深度。</p>
 *
 * <p>最小深度是从根节点到最近叶子节点的最短路径上的节点数量。</p>
 *
 * <p><strong>说明：</strong>叶子节点是指没有子节点的节点。</p>
 *
 * <p> </p>
 *
 * <p><strong>示例 1：</strong></p>
 * <img alt="" src="https://assets.leetcode.com/uploads/2020/10/12/ex_depth.jpg" style="width: 432px; height: 302px;" />
 * <pre>
 * <strong>输入：</strong>root = [3,9,20,null,null,15,7]
 * <strong>输出：</strong>2
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>root = [2,null,3,null,4,null,5,null,6]
 * <strong>输出：</strong>5
 * </pre>
 *
 * <p> </p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li>树中节点数的范围在 <code>[0, 10<sup>5</sup>]</code> 内</li>
 * <li><code>-1000 <= Node.val <= 1000</code></li>
 * </ul>
 * <div><div>Related Topics</div><div><li>树</li><li>深度优先搜索</li><li>广度优先搜索</li><li>二叉树</li></div></div><br><div><li>👍 637</li><li>👎 0</li></div>
 */

package leetcode.editor.cn;

import leetcode.editor.cn.util.TreeNode;
import leetcode.editor.cn.util.TreeUtils;

import java.util.LinkedList;
import java.util.Queue;

// 111 二叉树的最小深度
public class MinimumDepthOfBinaryTree111 {
    public static void main(String[] args) {
        Solution solution = new MinimumDepthOfBinaryTree111().new Solution();
        TreeNode input = TreeUtils.mkTree("[3,9,20,null,null,15,7]");
        long startTime = System.currentTimeMillis();
        int res = solution.minDepth(input);
        long endTime = System.currentTimeMillis();
        double time = endTime - startTime;
        System.out.println(res);
        System.out.println("程序运行时间： " + ((time > 10000) ? time / 1000 + "s" : time + "ms"));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int minDepth(TreeNode root) {
            if (root == null) return 0;
            Queue<TreeNode> q = new LinkedList<>();
            int depth = 1;
            q.offer(root);
            while (!q.isEmpty()) {
                int size = q.size();
                for (int i = 0; i < size; i++) {
                    TreeNode cur = q.poll();
                    if (cur.left == null && cur.right == null)
                        return depth;
                    if (cur.left != null)
                        q.offer(cur.left);
                    if (cur.right != null)
                        q.offer(cur.right);
                }
                depth++;
            }
            return depth;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
