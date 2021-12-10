/**
 * <p>给你两个字符串&nbsp;<code>s1</code>&nbsp;和&nbsp;<code>s2</code> ，写一个函数来判断 <code>s2</code> 是否包含 <code>s1</code><strong>&nbsp;</strong>的排列。如果是，返回 <code>true</code> ；否则，返回 <code>false</code> 。</p>
 *
 * <p>换句话说，<code>s1</code> 的排列之一是 <code>s2</code> 的 <strong>子串</strong> 。</p>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>s1 = "ab" s2 = "eidbaooo"
 * <strong>输出：</strong>true
 * <strong>解释：</strong>s2 包含 s1 的排列之一 ("ba").
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>s1= "ab" s2 = "eidboaoo"
 * <strong>输出：</strong>false
 * </pre>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>1 &lt;= s1.length, s2.length &lt;= 10<sup>4</sup></code></li>
 * <li><code>s1</code> 和 <code>s2</code> 仅包含小写字母</li>
 * </ul>
 * <div><div>Related Topics</div><div><li>哈希表</li><li>双指针</li><li>字符串</li><li>滑动窗口</li></div></div><br><div><li>👍 510</li><li>👎 0</li></div>
 */

package leetcode.editor.cn;

import java.util.HashMap;
import java.util.Map;

// 567 字符串的排列
public class PermutationInString567 {
    public static void main(String[] args) {
        Solution solution = new PermutationInString567().new Solution();
        long startTime = System.currentTimeMillis();
        boolean res = solution.checkInclusion("abcdxabcde", "abcdeabcdjx");
        long endTime = System.currentTimeMillis();
        double time = endTime - startTime;
        System.out.println(res);
        System.out.println("程序运行时间： " + ((time > 10000) ? time / 1000 + "s" : time + "ms"));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public boolean checkInclusion(String target, String source) {
            Map<Character, Integer> window = new HashMap<>();
            Map<Character, Integer> need = new HashMap<>();
            for (char c : target.toCharArray()) need.put(c, need.getOrDefault(c, 0) + 1);
            int left = 0, right = 0;
            int valid = 0;
            while (right < source.length()) {
                char inChar = source.charAt(right);
                right++;
                if (need.containsKey(inChar)) {
                    int windowNow = window.getOrDefault(inChar, 0) + 1;
                    window.put(inChar, windowNow);
                    if (windowNow == need.getOrDefault(inChar, 0)) valid++;
                }
                System.out.println(valid);
                //只要长度大于等于（其实就是等于）target就立马收缩
                //如果right加入了一个数，会立马顶掉left的数，保证长度一直与target一致
                while (right - left == target.length()) {
                    //当window满足了就立刻返回
                    if (valid == need.size()) {
                        return true;
                    }
                    char outChar = source.charAt(left);
                    left++;
                    if (need.containsKey(outChar)) {
                        int windowNow = window.getOrDefault(outChar, 0);
                        if (windowNow == need.getOrDefault(outChar, 0)) valid--;
                        window.put(outChar, windowNow - 1);
                    }
                }
            }
            return false;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
