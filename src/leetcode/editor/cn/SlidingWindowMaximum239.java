/**
 * 给你一个整数数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。
 * <p>
 * <p>
 * 返回 滑动窗口中的最大值 。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：nums = [1,3,-1,-3,5,3,6,7], k = 3
 * 输出：[3,3,5,5,6,7]
 * 解释：
 * 滑动窗口的位置                最大值
 * ---------------               -----
 * [1  3  -1] -3  5  3  6  7       3
 * 1 [3  -1  -3] 5  3  6  7       3
 * 1  3 [-1  -3  5] 3  6  7       5
 * 1  3  -1 [-3  5  3] 6  7       5
 * 1  3  -1  -3 [5  3  6] 7       6
 * 1  3  -1  -3  5 [3  6  7]      7
 * <p>
 * <p>
 * 示例 2：
 * <p>
 * <p>
 * 输入：nums = [1], k = 1
 * 输出：[1]
 * <p>
 * <p>
 * <p>
 * <p>
 * 提示：
 * <p>
 * <p>
 * 1 <= nums.length <= 10⁵
 * -10⁴ <= nums[i] <= 10⁴
 * 1 <= k <= nums.length
 * <p>
 * Related Topics 队列 数组 滑动窗口 单调队列 堆（优先队列） 👍 1378 👎 0
 */

package leetcode.editor.cn;

import leetcode.editor.cn.util.TreeUtils;

import java.util.Arrays;
import java.util.LinkedList;

// 239 滑动窗口最大值
public class SlidingWindowMaximum239 {
    public static void main(String[] args) {
        Solution solution = new SlidingWindowMaximum239().new Solution();
        int[] nums = TreeUtils.StrToIntArray("[-7,-8,7,5,7,1,6,0]");
        int k = 4;
        long startTime = System.currentTimeMillis();
        int[] result = solution.maxSlidingWindow(nums, k);
        long endTime = System.currentTimeMillis();
        System.out.println(Arrays.toString(result));
        System.out.println("程序运行时间： " + (endTime - startTime) + "ms");
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int[] maxSlidingWindow(int[] nums, int k) {
            if (nums.length == 0) return null;
            MonotonicQueue q = new MonotonicQueue();
            int[] result = new int[nums.length - k + 1];
            int index = 0;
            while (index < k) {
                q.push(nums[index]);
                index++;
            }
            result[0] = q.max();
            if (index == nums.length) return result;
            while (index < nums.length) {
                q.pop(nums[index-k]);
                q.push(nums[index]);
                result[index-k+1] = q.max();
                index++;
            }
            return result;
        }

        class MonotonicQueue {
            private LinkedList<Integer> q = new LinkedList<>();

            public void push(int n) {
                while (!q.isEmpty() && n > q.getLast())
                    q.pollLast();
                q.offer(n);
            }

            public int max() {
                return q.getFirst();
            }

            public void pop(int n) {
                if (q.getFirst() == n)
                    q.pollFirst();
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
