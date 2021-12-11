/**
 * <p>给你一个整数数组 <code>nums</code> ，找到其中最长严格递增子序列的长度。</p>
 *
 * <p>子序列是由数组派生而来的序列，删除（或不删除）数组中的元素而不改变其余元素的顺序。例如，<code>[3,6,2,7]</code> 是数组 <code>[0,3,1,6,2,2,7]</code> 的子序列。</p>
 *
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>nums = [10,9,2,5,3,7,101,18]
 * <strong>输出：</strong>4
 * <strong>解释：</strong>最长递增子序列是 [2,3,7,101]，因此长度为 4 。
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>nums = [0,1,0,3,2,3]
 * <strong>输出：</strong>4
 * </pre>
 *
 * <p><strong>示例 3：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>nums = [7,7,7,7,7,7,7]
 * <strong>输出：</strong>1
 * </pre>
 *
 * <p> </p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>1 <= nums.length <= 2500</code></li>
 * <li><code>-10<sup>4</sup> <= nums[i] <= 10<sup>4</sup></code></li>
 * </ul>
 *
 * <p> </p>
 *
 * <p><b>进阶：</b></p>
 *
 * <ul>
 * <li>你可以设计时间复杂度为 <code>O(n<sup>2</sup>)</code> 的解决方案吗？</li>
 * <li>你能将算法的时间复杂度降低到 <code>O(n log(n))</code> 吗?</li>
 * </ul>
 * <div><div>Related Topics</div><div><li>数组</li><li>二分查找</li><li>动态规划</li></div></div><br><div><li>👍 2073</li><li>👎 0</li></div>
 */

package leetcode.editor.cn;

import java.util.Arrays;

// 300 最长递增子序列
public class LongestIncreasingSubsequence300 {
    public static void main(String[] args) {
        Solution solution = new LongestIncreasingSubsequence300().new Solution();
        long startTime = System.currentTimeMillis();
        long endTime = System.currentTimeMillis();
        double time = endTime - startTime;
        System.out.println("程序运行时间： " + ((time > 10000) ? time / 1000 + "s" : time + "ms"));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int lengthOfLIS(int[] nums) {
            //初始化
            int[] dp = new int[nums.length];
            Arrays.fill(dp, 1);
            //从头开始遍历
            for (int i = 0; i < nums.length; i++) {
                //dp[i]的定义是nums[0]到nums[i]的最长递增子序列数
                //那么dp[i]其实就是dp[0]到dp[i-1]里，num[]对应的值比num[i]小的数中对应的dp最大值+1
                for (int j = 0; j < i; j++) {
                    //确保j对应的nums比i对应的小
                    if (nums[i] > nums[j])
                        //在符合的j中找到对应的最大的dp值
                        dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            int result = 0;
            //重新遍历数组，找到最大的那个结果
            for (int i : dp) {
                if (i > result) {
                    result = i;
                }
            }
            return result;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
