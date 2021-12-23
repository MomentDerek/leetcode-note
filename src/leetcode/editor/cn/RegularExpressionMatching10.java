/**
 * <p>给你一个字符串&nbsp;<code>s</code>&nbsp;和一个字符规律&nbsp;<code>p</code>，请你来实现一个支持 <code>'.'</code>&nbsp;和&nbsp;<code>'*'</code>&nbsp;的正则表达式匹配。</p>
 *
 * <ul>
 * <li><code>'.'</code> 匹配任意单个字符</li>
 * <li><code>'*'</code> 匹配零个或多个前面的那一个元素</li>
 * </ul>
 *
 * <p>所谓匹配，是要涵盖&nbsp;<strong>整个&nbsp;</strong>字符串&nbsp;<code>s</code>的，而不是部分字符串。</p>
 * &nbsp;
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>s = "aa" p = "a"
 * <strong>输出：</strong>false
 * <strong>解释：</strong>"a" 无法匹配 "aa" 整个字符串。
 * </pre>
 *
 * <p><strong>示例 2:</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>s = "aa" p = "a*"
 * <strong>输出：</strong>true
 * <strong>解释：</strong>因为 '*' 代表可以匹配零个或多个前面的那一个元素, 在这里前面的元素就是 'a'。因此，字符串 "aa" 可被视为 'a' 重复了一次。
 * </pre>
 *
 * <p><strong>示例&nbsp;3：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>s = "ab" p = ".*"
 * <strong>输出：</strong>true
 * <strong>解释：</strong>".*" 表示可匹配零个或多个（'*'）任意字符（'.'）。
 * </pre>
 *
 * <p><strong>示例 4：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>s = "aab" p = "c*a*b"
 * <strong>输出：</strong>true
 * <strong>解释：</strong>因为 '*' 表示零个或多个，这里 'c' 为 0 个, 'a' 被重复一次。因此可以匹配字符串 "aab"。
 * </pre>
 *
 * <p><strong>示例 5：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>s = "mississippi" p = "mis*is*p*."
 * <strong>输出：</strong>false</pre>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>1 &lt;= s.length&nbsp;&lt;= 20</code></li>
 * <li><code>1 &lt;= p.length&nbsp;&lt;= 30</code></li>
 * <li><code>s</code>&nbsp;可能为空，且只包含从&nbsp;<code>a-z</code>&nbsp;的小写字母。</li>
 * <li><code>p</code>&nbsp;可能为空，且只包含从&nbsp;<code>a-z</code>&nbsp;的小写字母，以及字符&nbsp;<code>.</code>&nbsp;和&nbsp;<code>*</code>。</li>
 * <li>保证每次出现字符&nbsp;<code>*</code> 时，前面都匹配到有效的字符</li>
 * </ul>
 * <div><div>Related Topics</div><div><li>递归</li><li>字符串</li><li>动态规划</li></div></div><br><div><li>👍 2586</li><li>👎 0</li></div>
 */

package leetcode.editor.cn;

import java.util.HashMap;
import java.util.Map;

// 10 正则表达式匹配
public class RegularExpressionMatching10 {
    public static void main(String[] args) {
        Solution solution = new RegularExpressionMatching10().new Solution();
        long startTime = System.currentTimeMillis();
        boolean res = solution.isMatch("aa", "ab*c*a");
        long endTime = System.currentTimeMillis();
        double time = endTime - startTime;
        System.out.println(res);
        System.out.println("程序运行时间： " + ((time > 10000) ? time / 1000 + "s" : time + "ms"));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution2 {
        private String s;
        private String p;
        private Map<String, Boolean> note;

        public boolean isMatch(String s, String p) {
            this.s = s;
            this.p = p;
            note = new HashMap<>();
            return dp(0, 0);
        }

        private boolean dp(int sIndex, int pIndex) {
            //判断是否满足结束条件
            //如果表达式到头了，字符串还没到头，则匹配失败，反之匹配成功
            if (pIndex == p.length()) return sIndex == s.length();
            //如果字符串到头，表达式还没到头，那表达式剩下的字符串中"a*"类型成对出现，则也匹配成功
            if (sIndex == s.length()) {
                if ((p.length() - pIndex) % 2 != 0)
                    return false;
                for (; pIndex + 1 < p.length(); pIndex += 2) {
                    if (p.charAt(pIndex + 1) != '*')
                        return false;
                }
                return true;
            }

            String key = sIndex + "." + pIndex;
            if (note.containsKey(key)) return note.get(key);

            boolean result = false;
            //如果s当前的字符和p一样
            if (s.charAt(sIndex) == p.charAt(pIndex) || p.charAt(pIndex) == '.') {
                //p还没到头，且p后为*
                if (pIndex + 1 < p.length() && p.charAt(pIndex + 1) == '*')
                    //通配符匹配多次
                    result = dp(sIndex + 1, pIndex)
                            //通配符匹配0次
                            || dp(sIndex, pIndex + 2);
                //p与s完整匹配
                else
                    result = dp(sIndex + 1, pIndex + 1);
            } else {
                //p未到头，如果为p后为*，则此时这个*只能匹配0次
                if (pIndex + 1 < p.length() && p.charAt(pIndex + 1) == '*')
                    result = dp(sIndex, pIndex + 2);
                //如果还不为*，那匹配失败
                else
                    result = false;
            }
            note.put(key, result);
            return result;
        }
    }

    class Solution {
        public boolean isMatch(String s, String p) {
            int sLen = s.length();
            int pLen = p.length();
            boolean[][] dp = new boolean[sLen + 1][pLen + 1];
            char[] sChars = s.toCharArray();
            char[] pChars = p.toCharArray();
            //p和s都是空串，匹配
            dp[0][0] = true;
            //从p的第二个字符开始，如果是*，则用它干掉一个字符（第一个直接是*的话是不符合题目要求的）
            for (int i = 2; i <= pLen; i++) {
                if (pChars[i - 1] == '*')
                    dp[0][i] = dp[0][i - 2];
            }
            for (int i = 1; i <= sLen; i++) {
                for (int j = 1; j <= pLen; j++) {
                    if (sChars[i - 1] == pChars[j - 1] || pChars[j - 1] == '.')
                        dp[i][j] = dp[i - 1][j - 1];
                    else if (pChars[j - 1] == '*') {
                        if (sChars[i - 1] == pChars[j - 2] || pChars[j - 2] == '.')
                            //s串当前的字符被p串的*抵消，看s串的前一个字符与*代表字符的匹配结果（此时*匹配一次及以上）
                            dp[i][j] = dp[i - 1][j]
                                    //s串当前的字符不与*和*之前的字符进行匹配，这与下面的else是类似的
                                    // 比如a*b*a或者a*a*这种情况，此时*匹配0次
                                    || dp[i][j - 2];
                        else
                            //p串的前一个字符加上*抵消掉了，取j-2位置的结果（此时*匹配0次）
                            dp[i][j] = dp[i][j - 2];
                    }
                    //此处不需要再加个else了，因为dp[i][j]默认就是false
                }
            }
            return dp[sChars.length][pChars.length];
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}
