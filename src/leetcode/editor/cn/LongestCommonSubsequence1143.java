/**
<p>给定两个字符串 <code>text1</code> 和 <code>text2</code>，返回这两个字符串的最长 <strong>公共子序列</strong> 的长度。如果不存在 <strong>公共子序列</strong> ，返回 <code>0</code> 。</p>

<p>一个字符串的 <strong>子序列</strong><em> </em>是指这样一个新的字符串：它是由原字符串在不改变字符的相对顺序的情况下删除某些字符（也可以不删除任何字符）后组成的新字符串。</p>

<ul>
	<li>例如，<code>"ace"</code> 是 <code>"abcde"</code> 的子序列，但 <code>"aec"</code> 不是 <code>"abcde"</code> 的子序列。</li>
</ul>

<p>两个字符串的 <strong>公共子序列</strong> 是这两个字符串所共同拥有的子序列。</p>

<p> </p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>text1 = "abcde", text2 = "ace" 
<strong>输出：</strong>3  
<strong>解释：</strong>最长公共子序列是 "ace" ，它的长度为 3 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>text1 = "abc", text2 = "abc"
<strong>输出：</strong>3
<strong>解释：</strong>最长公共子序列是 "abc" ，它的长度为 3 。
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>text1 = "abc", text2 = "def"
<strong>输出：</strong>0
<strong>解释：</strong>两个字符串没有公共子序列，返回 0 。
</pre>

<p> </p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 <= text1.length, text2.length <= 1000</code></li>
	<li><code>text1</code> 和 <code>text2</code> 仅由小写英文字符组成。</li>
</ul>
<div><div>Related Topics</div><div><li>字符串</li><li>动态规划</li></div></div><br><div><li>👍 775</li><li>👎 0</li></div>
*/
 
package leetcode.editor.cn;

import java.util.Arrays;

// 1143 最长公共子序列
public class LongestCommonSubsequence1143{
    public static void main(String[] args) {
        Solution solution = new LongestCommonSubsequence1143().new Solution();
        long startTime=System.currentTimeMillis();
        int res = solution.longestCommonSubsequence("abcde", "ace");
        long endTime=System.currentTimeMillis();
        double time = endTime - startTime;
        System.out.println(res);
        System.out.println("程序运行时间： " + ((time > 10000) ? time / 1000 + "s" : time + "ms"));
    }
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int longestCommonSubsequence(String text1, String text2) {
        int length1 = text1.length();
        int length2 = text2.length();
        //dp数组含义：当前坐标的最长公共子序列长度
        int[][] dp = new int[length1+1][length2+1];
        //左上角是原点，右下角是结果，从左上到右下遍历
        for (int i = 1; i <= length1; i++) {
            for (int j = 1; j <= length2; j++) {
                //如果相等则直接在横纵坐标-1的长度+1
                if (text1.charAt(i - 1) == text2.charAt(j - 1))
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                //如果不相等，说明此处的长度和横纵坐标各-1的两种情况中最长的情况相等
                else dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
            }
        }
        return dp[length1][length2];
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
