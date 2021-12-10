/**
 * <p>给定一个字符串 <code>s</code> ，请你找出其中不含有重复字符的 <strong>最长子串 </strong>的长度。</p>
 *
 * <p> </p>
 *
 * <p><strong>示例 1:</strong></p>
 *
 * <pre>
 * <strong>输入: </strong>s = "abcabcbb"
 * <strong>输出: </strong>3
 * <strong>解释:</strong> 因为无重复字符的最长子串是 <code>"abc"，所以其</code>长度为 3。
 * </pre>
 *
 * <p><strong>示例 2:</strong></p>
 *
 * <pre>
 * <strong>输入: </strong>s = "bbbbb"
 * <strong>输出: </strong>1
 * <strong>解释: </strong>因为无重复字符的最长子串是 <code>"b"</code>，所以其长度为 1。
 * </pre>
 *
 * <p><strong>示例 3:</strong></p>
 *
 * <pre>
 * <strong>输入: </strong>s = "pwwkew"
 * <strong>输出: </strong>3
 * <strong>解释: </strong>因为无重复字符的最长子串是 <code>"wke"</code>，所以其长度为 3。
 * 请注意，你的答案必须是 <strong>子串 </strong>的长度，<code>"pwke"</code> 是一个<em>子序列，</em>不是子串。
 * </pre>
 *
 * <p><strong>示例 4:</strong></p>
 *
 * <pre>
 * <strong>输入: </strong>s = ""
 * <strong>输出: </strong>0
 * </pre>
 *
 * <p> </p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>0 <= s.length <= 5 * 10<sup>4</sup></code></li>
 * <li><code>s</code> 由英文字母、数字、符号和空格组成</li>
 * </ul>
 * <div><div>Related Topics</div><div><li>哈希表</li><li>字符串</li><li>滑动窗口</li></div></div><br><div><li>👍 6558</li><li>👎 0</li></div>
 */

package leetcode.editor.cn;

import java.util.HashMap;
import java.util.Map;

// 3 无重复字符的最长子串
public class LongestSubstringWithoutRepeatingCharacters3 {
    public static void main(String[] args) {
        Solution solution = new LongestSubstringWithoutRepeatingCharacters3().new Solution();
        long startTime = System.currentTimeMillis();
        int res = solution.lengthOfLongestSubstring(" ");
        long endTime = System.currentTimeMillis();
        double time = endTime - startTime;
        System.out.println(res);
        System.out.println("程序运行时间： " + ((time > 10000) ? time / 1000 + "s" : time + "ms"));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int lengthOfLongestSubstring(String s) {
            Map<Character, Integer> window = new HashMap<>();
            int res = 0;
            int valid = 0;
            int left = 0, right = 0;
            while (right < s.length()) {
                char nowChar = s.charAt(right);
                right++;
                Integer charNum = window.getOrDefault(nowChar, 0);
                window.put(nowChar, charNum + 1);
                if (charNum == 0) valid++;
                //当前的字有重复则进入此循环，去除前面造成重复的部分
                while (window.getOrDefault(nowChar,0) == 2) {
                    char removeChar = s.charAt(left);
                    left++;
                    Integer removeCharNum = window.getOrDefault(removeChar, 0);
                    if (removeCharNum == 1) valid--;
                    window.put(removeChar, removeCharNum - 1);
                }
                //将当前循环的结果写入
                if (valid > res) res = valid;
            }
            return res;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
