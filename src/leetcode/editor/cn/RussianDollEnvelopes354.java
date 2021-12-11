/**
 * <p>给你一个二维整数数组 <code>envelopes</code> ，其中 <code>envelopes[i] = [w<sub>i</sub>, h<sub>i</sub>]</code> ，表示第 <code>i</code> 个信封的宽度和高度。</p>
 *
 * <p>当另一个信封的宽度和高度都比这个信封大的时候，这个信封就可以放进另一个信封里，如同俄罗斯套娃一样。</p>
 *
 * <p>请计算 <strong>最多能有多少个</strong> 信封能组成一组“俄罗斯套娃”信封（即可以把一个信封放到另一个信封里面）。</p>
 *
 * <p><strong>注意</strong>：不允许旋转信封。</p>
 *
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>envelopes = [[5,4],[6,4],[6,7],[2,3]]
 * <strong>输出：</strong>3
 * <strong>解释：</strong>最多信封的个数为 <code>3, 组合为: </code>[2,3] => [5,4] => [6,7]。</pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>envelopes = [[1,1],[1,1],[1,1]]
 * <strong>输出：</strong>1
 * </pre>
 *
 * <p> </p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>1 <= envelopes.length <= 5000</code></li>
 * <li><code>envelopes[i].length == 2</code></li>
 * <li><code>1 <= w<sub>i</sub>, h<sub>i</sub> <= 10<sup>4</sup></code></li>
 * </ul>
 * <div><div>Related Topics</div><div><li>数组</li><li>二分查找</li><li>动态规划</li><li>排序</li></div></div><br><div><li>👍 628</li><li>👎 0</li></div>
 */

package leetcode.editor.cn;

import java.util.Arrays;

// 354 俄罗斯套娃信封问题
public class RussianDollEnvelopes354 {
    public static void main(String[] args) {
        Solution solution = new RussianDollEnvelopes354().new Solution();
        long startTime = System.currentTimeMillis();
        long endTime = System.currentTimeMillis();
        double time = endTime - startTime;
        System.out.println("程序运行时间： " + ((time > 10000) ? time / 1000 + "s" : time + "ms"));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int maxEnvelopes(int[][] envelopes) {
            Arrays.sort(envelopes, (o1, o2) -> {
                //如果宽度相等则将高度逆序排列，这样可以保证后面的操作相等宽度的信封只有一个
                if (o1[0] == o2[0]) return o2[1] - o1[1];
                return o1[0] - o2[0];
            });
            int length = envelopes.length;
            //在对宽进行从小到大排序，对高进行逆序排列，然后对高进行最长子序列求解即可
            //下面是最长子序列的算法
            int[] dp = new int[length];
            Arrays.fill(dp, 1);
            for (int i = 0; i < length; i++) {
                for (int j = 0; j < i; j++) {
                    if (envelopes[i][1] > envelopes[j][1])
                        dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            int res = 1;
            for (int i : dp) {
                if (i > res) res = i;
            }
            return res;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
