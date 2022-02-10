/**
 * <p>数字 <code>n</code>&nbsp;代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 <strong>有效的 </strong>括号组合。</p>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>n = 3
 * <strong>输出：</strong>["((()))","(()())","(())()","()(())","()()()"]
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>n = 1
 * <strong>输出：</strong>["()"]
 * </pre>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>1 &lt;= n &lt;= 8</code></li>
 * </ul>
 * <div><div>Related Topics</div><div><li>字符串</li><li>动态规划</li><li>回溯</li></div></div><br><div><li>👍 2351</li><li>👎 0</li></div>
 */

package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

// 22 括号生成
public class GenerateParentheses22 {
    public static void main(String[] args) {
        Solution solution = new GenerateParentheses22().new Solution();
        long startTime = System.currentTimeMillis();
        long endTime = System.currentTimeMillis();
        double time = endTime - startTime;
        System.out.println("程序运行时间： " + ((time > 10000) ? time / 1000 + "s" : time + "ms"));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        private List<String> result = new ArrayList<>();
        private char[] track;

        public List<String> generateParenthesis(int n) {
            track = new char[2 * n];
            backtrack(n, n, 0);
            return result;
        }

        /**
         * @param left 剩余的左括号
         * @param right 剩余的右括号
         * @param index 当前位置
         */
        private void backtrack(int left, int right, int index) {
            //剩余的左右括号都小于0，跳过
            if (left < 0 || right < 0) return;
            //剩余的左括号大于右括号（即结果中左括号的数量大于右括号），跳过
            if (left > right) return;
            //完成条件，添加结果
            if (left == 0 && right == 0) {
                result.add(new String(track).trim());
                return;
            }
            track[index] = '(';
            backtrack(left - 1, right, index + 1);

            track[index] = ')';
            backtrack(left, right - 1, index + 1);
            track[index] = 0;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
