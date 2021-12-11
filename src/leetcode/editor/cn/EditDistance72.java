/**
 * <p>给你两个单词 <code>word1</code> 和 <code>word2</code>，请你计算出将 <code>word1</code> 转换成 <code>word2</code><em> </em>所使用的最少操作数 。</p>
 *
 * <p>你可以对一个单词进行如下三种操作：</p>
 *
 * <ul>
 * <li>插入一个字符</li>
 * <li>删除一个字符</li>
 * <li>替换一个字符</li>
 * </ul>
 *
 * <p> </p>
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>word1 = "horse", word2 = "ros"
 * <strong>输出：</strong>3
 * <strong>解释：</strong>
 * horse -> rorse (将 'h' 替换为 'r')
 * rorse -> rose (删除 'r')
 * rose -> ros (删除 'e')
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>word1 = "intention", word2 = "execution"
 * <strong>输出：</strong>5
 * <strong>解释：</strong>
 * intention -> inention (删除 't')
 * inention -> enention (将 'i' 替换为 'e')
 * enention -> exention (将 'n' 替换为 'x')
 * exention -> exection (将 'n' 替换为 'c')
 * exection -> execution (插入 'u')
 * </pre>
 *
 * <p> </p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>0 <= word1.length, word2.length <= 500</code></li>
 * <li><code>word1</code> 和 <code>word2</code> 由小写英文字母组成</li>
 * </ul>
 * <div><div>Related Topics</div><div><li>字符串</li><li>动态规划</li></div></div><br><div><li>👍 1976</li><li>👎 0</li></div>
 */

package leetcode.editor.cn;

// 72 编辑距离
public class EditDistance72 {
    public static void main(String[] args) {
        Solution solution = new EditDistance72().new Solution();
        long startTime = System.currentTimeMillis();
        int res = solution.minDistance("horse", "ros");
        long endTime = System.currentTimeMillis();
        double time = endTime - startTime;
        System.out.println(res);
        System.out.println("程序运行时间： " + ((time > 10000) ? time / 1000 + "s" : time + "ms"));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int minDistance(String source, String target) {
            int lenS = source.length();
            int lenT = target.length();
            int[][] dp = new int[lenS + 1][lenT + 1];
            for (int i = 1; i <= lenS; i++) dp[i][0] = i;
            for (int j = 1; j <= lenT; j++) dp[0][j] = j;
            for (int i = 1; i <= lenS; i++) {
                for (int j = 1; j <= lenT; j++) {
                    char s = source.charAt(i - 1);
                    char t = target.charAt(j - 1);
                    //相等则啥都不做
                    if (s == t) dp[i][j] = dp[i - 1][j - 1];
                    else {
                        dp[i][j] = min(
                                //增加
                                dp[i][j - 1] + 1,
                                //删除
                                dp[i - 1][j] + 1,
                                //替换
                                dp[i - 1][j - 1] + 1
                        );
                    }
                }
            }
            return dp[lenS][lenT];
        }

        private int min(int a, int b, int c) {
            return Math.min(
                    a,
                    Math.min(b, c));
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
