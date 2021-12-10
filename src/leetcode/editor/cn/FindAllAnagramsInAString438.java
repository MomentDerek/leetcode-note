/**
 * <p>给定两个字符串&nbsp;<code>s</code>&nbsp;和 <code>p</code>，找到&nbsp;<code>s</code><strong>&nbsp;</strong>中所有&nbsp;<code>p</code><strong>&nbsp;</strong>的&nbsp;<strong>异位词&nbsp;</strong>的子串，返回这些子串的起始索引。不考虑答案输出的顺序。</p>
 *
 * <p><strong>异位词 </strong>指由相同字母重排列形成的字符串（包括相同的字符串）。</p>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>示例&nbsp;1:</strong></p>
 *
 * <pre>
 * <strong>输入: </strong>s = "cbaebabacd", p = "abc"
 * <strong>输出: </strong>[0,6]
 * <strong>解释:</strong>
 * 起始索引等于 0 的子串是 "cba", 它是 "abc" 的异位词。
 * 起始索引等于 6 的子串是 "bac", 它是 "abc" 的异位词。
 * </pre>
 *
 * <p><strong>&nbsp;示例 2:</strong></p>
 *
 * <pre>
 * <strong>输入: </strong>s = "abab", p = "ab"
 * <strong>输出: </strong>[0,1,2]
 * <strong>解释:</strong>
 * 起始索引等于 0 的子串是 "ab", 它是 "ab" 的异位词。
 * 起始索引等于 1 的子串是 "ba", 它是 "ab" 的异位词。
 * 起始索引等于 2 的子串是 "ab", 它是 "ab" 的异位词。
 * </pre>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>提示:</strong></p>
 *
 * <ul>
 * <li><code>1 &lt;= s.length, p.length &lt;= 3 * 10<sup>4</sup></code></li>
 * <li><code>s</code>&nbsp;和&nbsp;<code>p</code>&nbsp;仅包含小写字母</li>
 * </ul>
 * <div><div>Related Topics</div><div><li>哈希表</li><li>字符串</li><li>滑动窗口</li></div></div><br><div><li>👍 730</li><li>👎 0</li></div>
 */

package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 438 找到字符串中所有字母异位词
public class FindAllAnagramsInAString438 {
    public static void main(String[] args) {
        Solution solution = new FindAllAnagramsInAString438().new Solution();
        long startTime = System.currentTimeMillis();
        List<Integer> res = solution.findAnagrams("cbaebabacd", "abc");
        long endTime = System.currentTimeMillis();
        double time = endTime - startTime;
        System.out.println(res);
        System.out.println("程序运行时间： " + ((time > 10000) ? time / 1000 + "s" : time + "ms"));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    //请参考PermutationInString567
    class Solution {
        public List<Integer> findAnagrams(String source, String target) {
            Map<Character, Integer> window = new HashMap<>();
            Map<Character, Integer> need = new HashMap<>();
            //存储结果
            List<Integer> res = new ArrayList<>();
            for (char c : target.toCharArray()) need.put(c, need.getOrDefault(c, 0) + 1);
            int left = 0, right = 0;
            int valid = 0;
            while (right < source.length()) {
                char inChar = source.charAt(right);
                right++;
                if (need.containsKey(inChar)) {
                    int windowsNow = window.getOrDefault(inChar, 0) + 1;
                    window.put(inChar, windowsNow);
                    if (windowsNow == need.getOrDefault(inChar, 0)) valid++;
                }
                //限制长度
                while (right - left == target.length()) {
                    //满足要求直接加入结果
                    if (valid == need.size()) res.add(left);
                    char outChar = source.charAt(left);
                    left++;
                    if (need.containsKey(outChar)) {
                        int windowsNow = window.getOrDefault(outChar, 0);
                        if (windowsNow == need.getOrDefault(outChar, 0)) valid--;
                        window.put(outChar, windowsNow - 1);
                    }
                }
            }
            return res;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
