/**
 * <p>给你一个字符串 <code>s</code> ，找出其中最长的回文子序列，并返回该序列的长度。</p>
 *
 * <p>子序列定义为：不改变剩余字符顺序的情况下，删除某些字符或者不删除任何字符形成的一个序列。</p>
 *
 * <p> </p>
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>s = "bbbab"
 * <strong>输出：</strong>4
 * <strong>解释：</strong>一个可能的最长回文子序列为 "bbbb" 。
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>s = "cbbd"
 * <strong>输出：</strong>2
 * <strong>解释：</strong>一个可能的最长回文子序列为 "bb" 。
 * </pre>
 *
 * <p> </p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>1 <= s.length <= 1000</code></li>
 * <li><code>s</code> 仅由小写英文字母组成</li>
 * </ul>
 * <div><div>Related Topics</div><div><li>字符串</li><li>动态规划</li></div></div><br><div><li>👍 677</li><li>👎 0</li></div>
 */

package leetcode.editor.cn;

// 516 最长回文子序列
public class LongestPalindromicSubsequence516 {
    public static void main(String[] args) {
        Solution solution = new LongestPalindromicSubsequence516().new Solution();
        long startTime = System.currentTimeMillis();
        long endTime = System.currentTimeMillis();
        double time = endTime - startTime;
        System.out.println("程序运行时间： " + ((time > 10000) ? time / 1000 + "s" : time + "ms"));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int longestPalindromeSubseq(String s) {
            int length = s.length();
            //dp[i][j]数组定义：字符串下标i到j的最长回文子序列的长度
            int[][] dp = new int[length][length];
            for (int i = 0; i < length; i++) dp[i][i] = 1;
            //从下往上
            for (int i = length - 2; i >= 0; i--) {
                //从i往右
                for (int j = i+1; j < length; j++) {
                    char leftChar = s.charAt(i);
                    char rightChar = s.charAt(j);
                    if (leftChar == rightChar)
                        //左右相等则直接就是dp[i + 1][j - 1]的长度+2
                        dp[i][j] = dp[i + 1][j - 1] + 2;
                    else
                        //如果左右不相等，则看看左右两边的字符哪个能纳入，
                        //如果能纳入的那个其实就是dp[i + 1][j]和dp[i][j - 1]中最大的那个
                        //如果都不能，其实dp[i + 1][j]，dp[i][j - 1]，dp[i + 1][j - 1] 是相等的
                        dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                }
            }
            //返回右上角（0到length-1）
            return dp[0][length - 1];
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
