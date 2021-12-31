/**
 * <p>给你 <code>k</code> 枚相同的鸡蛋，并可以使用一栋从第 <code>1</code> 层到第 <code>n</code> 层共有 <code>n</code> 层楼的建筑。</p>
 *
 * <p>已知存在楼层 <code>f</code> ，满足 <code>0 <= f <= n</code> ，任何从<strong> 高于</strong> <code>f</code> 的楼层落下的鸡蛋都会碎，从 <code>f</code> 楼层或比它低的楼层落下的鸡蛋都不会破。</p>
 *
 * <p>每次操作，你可以取一枚没有碎的鸡蛋并把它从任一楼层 <code>x</code> 扔下（满足 <code>1 <= x <= n</code>）。如果鸡蛋碎了，你就不能再次使用它。如果某枚鸡蛋扔下后没有摔碎，则可以在之后的操作中 <strong>重复使用</strong> 这枚鸡蛋。</p>
 *
 * <p>请你计算并返回要确定 <code>f</code> <strong>确切的值</strong> 的 <strong>最小操作次数</strong> 是多少？</p>
 *
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>k = 1, n = 2
 * <strong>输出：</strong>2
 * <strong>解释：</strong>
 * 鸡蛋从 1 楼掉落。如果它碎了，肯定能得出 f = 0 。
 * 否则，鸡蛋从 2 楼掉落。如果它碎了，肯定能得出 f = 1 。
 * 如果它没碎，那么肯定能得出 f = 2 。
 * 因此，在最坏的情况下我们需要移动 2 次以确定 f 是多少。
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>k = 2, n = 6
 * <strong>输出：</strong>3
 * </pre>
 *
 * <p><strong>示例 3：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>k = 3, n = 14
 * <strong>输出：</strong>4
 * </pre>
 *
 * <p> </p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>1 <= k <= 100</code></li>
 * <li><code>1 <= n <= 10<sup>4</sup></code></li>
 * </ul>
 * <div><div>Related Topics</div><div><li>数学</li><li>二分查找</li><li>动态规划</li></div></div><br><div><li>👍 728</li><li>👎 0</li></div>
 */

package leetcode.editor.cn;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

// 887 鸡蛋掉落
public class SuperEggDrop887 {
    public static void main(String[] args) {
        Solution solution = new SuperEggDrop887().new Solution();
        long startTime = System.currentTimeMillis();
        int res = solution.superEggDrop(3, 14);
        long endTime = System.currentTimeMillis();
        double time = endTime - startTime;
        System.out.println(res);
        System.out.println("程序运行时间： " + ((time > 10000) ? time / 1000 + "s" : time + "ms"));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution2 {

        public int superEggDrop(int k, int n) {
            if (k == 1) {
                return n;
            }
            if (n == 0) {
                return 0;
            }
            int res = Integer.MAX_VALUE;
            for (int i = 1; i <= n; i++) {
                res = Math.min(res,
                        Math.max(
                                //
                                superEggDrop(k - 1, i - 1),
                                superEggDrop(k, n - i)) + 1);
            }
            return res;
        }
    }

    class Solution1 {
        public int superEggDrop(int eggNum, int floorNum) {
            //第一维为鸡蛋数，第二维为楼层数，dp数组含义为该条件下的最小操作次数
            int[][] dp = new int[eggNum+1][floorNum + 1];
            for (int i = 1; i <= eggNum; i++) {
                for (int j = 1; j <= floorNum; j++) {
                    dp[i][j] = j;
                }
            }
            //初始化base
            //鸡蛋只有1个，那肯定得一层层测
            //鸡蛋为0个，肯定测不出来，为0次
            for (int i = 0; i <= floorNum; i++) {
                dp[1][i] = i;
                dp[0][i] = 0;
            }
            //楼层有0层，无论如何都是0次
            //楼层只有1层，那0个鸡蛋测不出来，0次（包含在上面鸡蛋0个的情况了）
            //0个以上能测的出来，为1次
            for (int i = 1; i < eggNum; i++) {
                dp[i][0] = 0;
                dp[i][1] = 1;
            }
            //开始遍历dp表
            for (int i = 2; i <= eggNum; i++) {
                for (int j = 2; j <= floorNum; j++) {
                    //做选择
                    for (int k = 1; k < j; k++) {
                        dp[i][j] = Math.min(dp[i][j],Math.max(dp[i - 1][k - 1], dp[i][j-k]) + 1);
                    }
                }
            }
            return dp[eggNum][floorNum];
        }
    }

    class Solution {
        private Map<String,Integer> note;
        public int superEggDrop(int k, int n) {
            note = new HashMap<>();
            return dp(k, n);
        }

        public int dp(int k, int n) {
            if (k == 1) {
                return n;
            }
            if (n == 0) {
                return 0;
            }
            if (note.containsKey(k+","+n)) return note.get(k + "," + n);
            int res = Integer.MAX_VALUE;
            //for (int i = 1; i <= n; i++) {
            //    res = Math.min(res,
            //            Math.max(
            //                    //
            //                    dp(k - 1, i - 1),
            //                    dp(k, n - i)) + 1);
            //}
            int low = 1, high = n;
            while (low <= high) {
                int mid = (low+high)/2;
                int broken = dp(k - 1, n - 1);
                int notBroken = dp(k, n - mid);
                if (broken > notBroken) {
                    high = mid - 1;
                    res = Math.min(res, broken + 1);
                } else {
                    low = mid + 1;
                    res = Math.min(res, notBroken + 1);
                }
            }
            note.put(k + "," + n, res);
            return res;
        }
    }

//leetcode submit region end(Prohibit modification and deletion)

}
