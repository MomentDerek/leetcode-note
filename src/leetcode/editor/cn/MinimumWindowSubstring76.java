/**
 * <p>给你一个字符串 <code>s</code> 、一个字符串 <code>t</code> 。返回 <code>s</code> 中涵盖 <code>t</code> 所有字符的最小子串。如果 <code>s</code> 中不存在涵盖 <code>t</code> 所有字符的子串，则返回空字符串 <code>""</code> 。</p>
 *
 * <p> </p>
 *
 * <p><strong>注意：</strong></p>
 *
 * <ul>
 * <li>对于 <code>t</code> 中重复字符，我们寻找的子字符串中该字符数量必须不少于 <code>t</code> 中该字符数量。</li>
 * <li>如果 <code>s</code> 中存在这样的子串，我们保证它是唯一的答案。</li>
 * </ul>
 *
 * <p> </p>
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>s = "ADOBECODEBANC", t = "ABC"
 * <strong>输出：</strong>"BANC"
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>s = "a", t = "a"
 * <strong>输出：</strong>"a"
 * </pre>
 *
 * <p><strong>示例 3:</strong></p>
 *
 * <pre>
 * <strong>输入:</strong> s = "a", t = "aa"
 * <strong>输出:</strong> ""
 * <strong>解释:</strong> t 中两个字符 'a' 均应包含在 s 的子串中，
 * 因此没有符合条件的子字符串，返回空字符串。</pre>
 *
 * <p> </p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>1 <= s.length, t.length <= 10<sup>5</sup></code></li>
 * <li><code>s</code> 和 <code>t</code> 由英文字母组成</li>
 * </ul>
 *
 * <p> </p>
 * <strong>进阶：</strong>你能设计一个在 <code>o(n)</code> 时间内解决此问题的算法吗？<div><div>Related Topics</div><div><li>哈希表</li><li>字符串</li><li>滑动窗口</li></div></div><br><div><li>👍 1478</li><li>👎 0</li></div>
 */

package leetcode.editor.cn;

import java.util.HashMap;
import java.util.Map;

// 76 最小覆盖子串
public class MinimumWindowSubstring76 {
    public static void main(String[] args) {
        Solution solution = new MinimumWindowSubstring76().new Solution();
        long startTime = System.currentTimeMillis();
        String res = solution.minWindow("ADOBECODEBANC", "ABC");
        long endTime = System.currentTimeMillis();
        double time = endTime - startTime;
        System.out.println(res);
        System.out.println("程序运行时间： " + ((time > 10000) ? time / 1000 + "s" : time + "ms"));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public String minWindow(String s, String t) {
            Map<Character, Integer> need = new HashMap<>();
            Map<Character, Integer> window = new HashMap<>();
            for (char c : t.toCharArray()) need.put(c, need.getOrDefault(c, 0) + 1);
            int left = 0, right = 0;
            //存储结果的开始下标和长度
            int start = 0, len = Integer.MAX_VALUE;
            //已经满足了多少个字符，当valid等于need的长度时，意味着need里所有的要求都已经被满足
            int valid = 0;
            //此处判断right的时候窗口区间是[left,right]
            while (right < s.length()) {
                //扩大窗口，注意这里是读取right的值后立马+1，+1后窗口区间是[left,right)
                char inChar = s.charAt(right);
                right++;
                if (need.containsKey(inChar)) {
                    int windowNow = window.getOrDefault(inChar, 0)+1;
                    window.put(inChar, windowNow);
                    //如果window里面的数量满足了条件，则valid++
                    if (windowNow == need.getOrDefault(inChar, 0)) valid++;
                }
                while (valid == need.size()) {
                    if (right - left < len) {
                        start = left;
                        len = right - left;
                    }
                    char outChar = s.charAt(left);
                    left++;
                    if (need.containsKey(outChar)) {
                        int windowNow = window.getOrDefault(outChar, 0);
                        //如果window里面的数量满足了条件，则代表缩小窗口后条件不被满足
                        if (windowNow == need.getOrDefault(outChar,0)) valid--;
                        //将更新缩小后的结果
                        window.put(outChar, windowNow-1);
                    }
                }
            }
            return len == Integer.MAX_VALUE?"":s.substring(start,start+len);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
