/**
 * /**
 * /**
 * <p><strong>斐波那契数</strong>，通常用 <code>F(n)</code> 表示，形成的序列称为 <strong>斐波那契数列</strong> 。该数列由 <code>0</code> 和 <code>1</code> 开始，后面的每一项数字都是前面两项数字的和。也就是：</p>
 *
 * <pre>
 * F(0) = 0，F(1) = 1
 * F(n) = F(n - 1) + F(n - 2)，其中 n > 1
 * </pre>
 *
 * <p>给你 <code>n</code> ，请计算 <code>F(n)</code> 。</p>
 *
 * <p> </p>
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>2
 * <strong>输出：</strong>1
 * <strong>解释：</strong>F(2) = F(1) + F(0) = 1 + 0 = 1
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>3
 * <strong>输出：</strong>2
 * <strong>解释：</strong>F(3) = F(2) + F(1) = 1 + 1 = 2
 * </pre>
 *
 * <p><strong>示例 3：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>4
 * <strong>输出：</strong>3
 * <strong>解释：</strong>F(4) = F(3) + F(2) = 2 + 1 = 3
 * </pre>
 *
 * <p> </p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>0 <= n <= 30</code></li>
 * </ul>
 * <div><div>Related Topics</div><div><li>递归</li><li>记忆化搜索</li><li>数学</li><li>动态规划</li></div></div><br><div><li>👍 349</li><li>👎 0</li></div>
 */

package leetcode.editor.cn;

// 509 斐波那契数（动态规划）
public class FibonacciNumber509 {
    public static void main(String[] args) {
        Solution solution = new FibonacciNumber509().new Solution();
        long startTime = System.currentTimeMillis();
        int result = solution.fib(30);
        long endTime = System.currentTimeMillis();
        double time = endTime - startTime;
        System.out.println(result);
        System.out.println("程序运行时间： " + ((time > 10000) ? time / 1000 + "s" : time + "ms"));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    //dp迭代解法
    class Solution {
        public int fib(int n) {
            if (n == 0) return 0;
            if (n == 1 || n == 2) return 1;
            int first = 0;
            int second = 1;
            int result = 0;
            for (int i = 2; i <= n; i++) {
                result = first + second;
                first = second;
                second = result;
            }
            return result;
        }
    }

    //带备忘录的递归解法
    class Solution2 {
        int[] note;

        public int fib(int n) {
            note = new int[n + 1];
            return dp(n);
        }

        private int dp(int n) {
            if (n == 0) return 0;
            if (n == 1 || n == 2) return 1;
            if (note[n] != 0) return note[n];
            note[n] = dp(n - 1) + dp(n - 2);
            return note[n];
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}
