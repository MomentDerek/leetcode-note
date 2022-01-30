/**
 * 给你一个二叉树的根节点 root ，判断其是否是一个有效的二叉搜索树。
 * <p>
 * 有效 二叉搜索树定义如下：
 * <p>
 * <p>
 * 节点的左子树只包含 小于 当前节点的数。
 * 节点的右子树只包含 大于 当前节点的数。
 * 所有左子树和右子树自身必须也是二叉搜索树。
 * <p>
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：root = [2,1,3]
 * 输出：true
 * <p>
 * <p>
 * 示例 2：
 * <p>
 * <p>
 * 输入：root = [5,1,4,null,null,3,6]
 * 输出：false
 * 解释：根节点的值是 5 ，但是右子节点的值是 4 。
 * <p>
 * <p>
 * <p>
 * <p>
 * 提示：
 * <p>
 * <p>
 * 树中节点数目范围在[1, 10⁴] 内
 * -2³¹ <= Node.val <= 2³¹ - 1
 * <p>
 * Related Topics 树 深度优先搜索 二叉搜索树 二叉树 👍 1398 👎 0
 */

package leetcode.editor.cn;

import leetcode.editor.cn.util.TreeNode;

import java.util.LinkedList;

// 98 验证二叉搜索树
public class ValidateBinarySearchTree98 {
    public static void main(String[] args) {
        Solution solution = new ValidateBinarySearchTree98().new Solution();
        long startTime = System.currentTimeMillis();
        long endTime = System.currentTimeMillis();
        System.out.println("程序运行时间： " + (endTime - startTime) + "ms");
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    //前序遍历写法
    class Solution {
        public boolean isValidBST(TreeNode root) {
            return isValidBST(root, null, null);
        }

        private boolean isValidBST(TreeNode root, TreeNode min, TreeNode max) {
            if (root == null) return true;
            if (min != null && root.val <= min.val) return false;
            if (max != null && root.val >= max.val) return false;
            return isValidBST(root.left, min, root)
                    && isValidBST(root.right, root, max);
        }
    }

    //中序遍历写法
    //因为中序遍历BST的结果是一个升序数组，所以直接对比中序的前一个与当前的节点即可判断是否合法
    class Solution1 {
        //前一个值，因为val的范围覆盖了整个integer，所以设置preVal为long的最小值
        private long preVal = Long.MIN_VALUE;

        public boolean isValidBST(TreeNode root) {
            if (root == null) return true;
            //取左侧的递归值
            boolean left = isValidBST(root.left);
            //如果左侧不满足，直接返回递归结果，提高效率
            if (!left) return false;
            //如果前一个值大于当前值，则不合法
            if (root.val <= preVal) return false;
            preVal = root.val;
            //因为左侧的值已经提前处理，此处只需要返回右侧的结果即可
            //按照逻辑应该是left&&right但此处left一定为true
            return isValidBST(root.right);
        }
    }

    //算法参考，前序遍历的迭代写法（速度肯定不快的啦）
    class Solution2 {
        private LinkedList<TreeNode> stack = new LinkedList<>();
        private LinkedList<Integer> uppers = new LinkedList<>();
        private LinkedList<Integer> lowers = new LinkedList<>();

        public boolean isValidBST(TreeNode root) {
            Integer upper = null, lower = null;
            update(root, upper, lower);
            while (!stack.isEmpty()) {
                root = stack.poll();
                lower = lowers.poll();
                upper = uppers.poll();
                if (root == null) continue;
                if (lower != null && lower >= root.val) return false;
                if (upper != null && upper <= root.val) return false;
                update(root.left,root.val,lower);
                update(root.right,upper,root.val);
            }
            return true;
        }

        private void update(TreeNode root, Integer upper, Integer lower) {
            stack.add(root);
            this.uppers.add(upper);
            this.lowers.add(lower);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
