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
        int[] coins = {186, 419, 83, 408};
        long startTime = System.currentTimeMillis();
        System.out.println(solution.coinChange(coins, 6249));
        long endTime = System.currentTimeMillis();
        System.out.println("程序运行时间： " + (endTime - startTime)  + "ms");
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    //dp迭代
    class Solution {

        public int coinChange(int[] coins, int amount) {
            //“dp表”
            int[] dpTable = new int[amount + 1];
            Arrays.fill(dpTable, amount + 1);
            dpTable[0] = 0;
            for (int i = 0; i < dpTable.length; i++) {
                for (int coin : coins) {
                    if (i - coin < 0) continue;
                    dpTable[i] = Math.min(dpTable[i], 1 + dpTable[i - coin]);
                }
            }
            int res = dpTable[amount];
            return res == amount+1 ? -1 : res;
        }

    }

    //带备忘录的递归，超时
    /*
     * coins = {186, 419, 83, 408}
     * 6249
     * 运行时间49s
     */
    class Solution2 {
        //“选择”
        private int[] coins;
        //“备忘录”
        private int[] note;
        //测试用计数
        private long num;

        public int coinChange(int[] coins, int amount) {
            this.coins = coins;
            //初始化备忘录
            note = new int[amount + 1];
            Arrays.fill(note, -1);
            //开始迭代
            return dp(amount);
        }

        private int dp(int amount) {
            System.out.println(num++);
            if (amount < 0) return -1;
            if (amount == 0) return 0;
            //查备忘录需要在base判断后，否则可能会超索引（此处可能会-1）
            if (note[amount] != -1) return note[amount];
            //当前金额的结果
            int tempRes = Integer.MAX_VALUE;
            for (int coin : coins) {
                //往下递归
                int subRes = dp(amount - coin);
                //如果递归返回的是-1，则说明此路线无解，换下一个
                if (subRes == -1) continue;
                tempRes = Integer.min(tempRes, subRes + 1);
            }
            int amountRes = tempRes == Integer.MAX_VALUE ? -1 : tempRes;
            note[amount] = amountRes;
            return amountRes;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)


}
