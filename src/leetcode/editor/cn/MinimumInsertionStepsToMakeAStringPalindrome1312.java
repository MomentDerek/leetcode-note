/**
 * <p>给你一个字符串&nbsp;<code>s</code>&nbsp;，每一次操作你都可以在字符串的任意位置插入任意字符。</p>
 *
 * <p>请你返回让&nbsp;<code>s</code>&nbsp;成为回文串的&nbsp;<strong>最少操作次数</strong>&nbsp;。</p>
 *
 * <p>「回文串」是正读和反读都相同的字符串。</p>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>s = &quot;zzazz&quot;
 * <strong>输出：</strong>0
 * <strong>解释：</strong>字符串 &quot;zzazz&quot; 已经是回文串了，所以不需要做任何插入操作。
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>s = &quot;mbadm&quot;
 * <strong>输出：</strong>2
 * <strong>解释：</strong>字符串可变为 &quot;mbdadbm&quot; 或者 &quot;mdbabdm&quot; 。
 * </pre>
 *
 * <p><strong>示例 3：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>s = &quot;leetcode&quot;
 * <strong>输出：</strong>5
 * <strong>解释：</strong>插入 5 个字符后字符串变为 &quot;leetcodocteel&quot; 。
 * </pre>
 *
 * <p><strong>示例 4：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>s = &quot;g&quot;
 * <strong>输出：</strong>0
 * </pre>
 *
 * <p><strong>示例 5：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>s = &quot;no&quot;
 * <strong>输出：</strong>1
 * </pre>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>1 &lt;= s.length &lt;= 500</code></li>
 * <li><code>s</code>&nbsp;中所有字符都是小写字母。</li>
 * </ul>
 * <div><div>Related Topics</div><div><li>字符串</li><li>动态规划</li></div></div><br><div><li>👍 122</li><li>👎 0</li></div>
 */

package leetcode.editor.cn;

// 1312 让字符串成为回文串的最少插入次数
public class MinimumInsertionStepsToMakeAStringPalindrome1312 {
    public static void main(String[] args) {
        Solution solution = new MinimumInsertionStepsToMakeAStringPalindrome1312().new Solution();
        long startTime = System.currentTimeMillis();
        long endTime = System.currentTimeMillis();
        double time = endTime - startTime;
        System.out.println("程序运行时间： " + ((time > 10000) ? time / 1000 + "s" : time + "ms"));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int minInsertions(String s) {
            int length = s.length();
            //dp[i][j]定义：s[i]到s[j]的最小插入次数
            //base状态为0
            int[][] dp = new int[length][length];
            for (int i = length - 2; i >= 0; i--) {
                for (int j = i + 1; j < length; j++) {
                    char startChar = s.charAt(i);
                    char endChar = s.charAt(j);
                    //两边相等则保持[i + 1][j - 1]的结果
                    if (startChar == endChar) dp[i][j] = dp[i + 1][j - 1];
                    //不相等则取[i][j - 1], [i + 1][j]最小的那个+1,
                    if (startChar != endChar)
                        dp[i][j] = Math.min(dp[i][j - 1] + 1, dp[i + 1][j] + 1);
                }
            }
            return dp[0][length - 1];
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}