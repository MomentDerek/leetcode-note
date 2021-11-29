/**
 * <p>给你一个整数数组 <code>coins</code> ，表示不同面额的硬币；以及一个整数 <code>amount</code> ，表示总金额。</p>
 *
 * <p>计算并返回可以凑成总金额所需的 <strong>最少的硬币个数</strong> 。如果没有任何一种硬币组合能组成总金额，返回 <code>-1</code> 。</p>
 *
 * <p>你可以认为每种硬币的数量是无限的。</p>
 *
 * <p> </p>
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>coins = <code>[1, 2, 5]</code>, amount = <code>11</code>
 * <strong>输出：</strong><code>3</code>
 * <strong>解释：</strong>11 = 5 + 5 + 1</pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>coins = <code>[2]</code>, amount = <code>3</code>
 * <strong>输出：</strong>-1</pre>
 *
 * <p><strong>示例 3：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>coins = [1], amount = 0
 * <strong>输出：</strong>0
 * </pre>
 *
 * <p><strong>示例 4：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>coins = [1], amount = 1
 * <strong>输出：</strong>1
 * </pre>
 *
 * <p><strong>示例 5：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>coins = [1], amount = 2
 * <strong>输出：</strong>2
 * </pre>
 *
 * <p> </p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>1 <= coins.length <= 12</code></li>
 * <li><code>1 <= coins[i] <= 2<sup>31</sup> - 1</code></li>
 * <li><code>0 <= amount <= 10<sup>4</sup></code></li>
 * </ul>
 * <div><div>Related Topics</div><div><li>广度优先搜索</li><li>数组</li><li>动态规划</li></div></div><br><div><li>👍 1573</li><li>👎 0</li></div>
 */

package leetcode.editor.cn;

import java.util.Arrays;

// 322 零钱兑换
public class CoinChange322 {
    public static void main(String[] args) {
        Solution solution = new CoinChange322().new Solution();
        int[] coins = {1, 2, 5};
        System.out.println(solution.coinChange(coins, 100));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        private int[] coins;
        private int[] note;

        public int coinChange(int[] coins, int amount) {
            this.coins = coins;
            note = new int[amount + 1];
            Arrays.fill(note,-1);
            return dp(amount);
        }

        private int dp(int amount) {
            //if (note[amount] != -1) return note[amount];
            if (amount<0) return -1;
            if (amount==0) return 0;
            int result = Integer.MAX_VALUE;
            for (int coin : coins) {
                int subRes = dp(amount - coin);
                if (subRes == -1) continue;
                result = Integer.min(result, subRes+1);
            }
            return result==Integer.MAX_VALUE?-1:result;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}
