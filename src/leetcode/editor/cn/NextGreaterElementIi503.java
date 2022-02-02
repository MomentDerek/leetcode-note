/**
 * 给定一个循环数组（最后一个元素的下一个元素是数组的第一个元素），输出每个元素的下一个更大元素。数字 x 的下一个更大的元素是按数组遍历顺序，这个数字之后的第一个
 * 比它更大的数，这意味着你应该循环地搜索它的下一个更大的数。如果不存在，则输出 -1。
 * <p>
 * 示例 1:
 * <p>
 * <p>
 * 输入: [1,2,1]
 * 输出: [2,-1,2]
 * 解释: 第一个 1 的下一个更大的数是 2；
 * 数字 2 找不到下一个更大的数；
 * 第二个 1 的下一个最大的数需要循环搜索，结果也是 2。
 * <p>
 * <p>
 * 注意: 输入数组的长度不会超过 10000。
 * Related Topics 栈 数组 单调栈 👍 552 👎 0
 */

package leetcode.editor.cn;

import java.util.Stack;

// 503 下一个更大元素 II
public class NextGreaterElementIi503 {
    public static void main(String[] args) {
        Solution solution = new NextGreaterElementIi503().new Solution();
        long startTime = System.currentTimeMillis();
        long endTime = System.currentTimeMillis();
        System.out.println("程序运行时间： " + (endTime - startTime) + "ms");
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int[] nextGreaterElements(int[] nums) {
            int n = nums.length;
            int[] res = new int[n];
            Stack<Integer> stack = new Stack<>();
            for (int i = 2 * n - 1; i >= 0; i--) {
                while (!stack.empty() && nums[i % n] >= stack.peek())
                    stack.pop();
                res[i % n] = stack.empty() ? -1 : stack.peek();
                stack.push(nums[i % n]);
            }
            return res;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
