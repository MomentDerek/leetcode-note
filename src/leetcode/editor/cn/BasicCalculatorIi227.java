/**
 * <p>给你一个字符串表达式 <code>s</code> ，请你实现一个基本计算器来计算并返回它的值。</p>
 *
 * <p>整数除法仅保留整数部分。</p>
 *
 * <div class="original__bRMd">
 * <div>
 * <p> </p>
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>s = "3+2*2"
 * <strong>输出：</strong>7
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>s = " 3/2 "
 * <strong>输出：</strong>1
 * </pre>
 *
 * <p><strong>示例 3：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>s = " 3+5 / 2 "
 * <strong>输出：</strong>5
 * </pre>
 *
 * <p> </p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>1 <= s.length <= 3 * 10<sup>5</sup></code></li>
 * <li><code>s</code> 由整数和算符 <code>('+', '-', '*', '/')</code> 组成，中间由一些空格隔开</li>
 * <li><code>s</code> 表示一个 <strong>有效表达式</strong></li>
 * <li>表达式中的所有整数都是非负整数，且在范围 <code>[0, 2<sup>31</sup> - 1]</code> 内</li>
 * <li>题目数据保证答案是一个 <strong>32-bit 整数</strong></li>
 * </ul>
 * </div>
 * </div>
 * <div><div>Related Topics</div><div><li>栈</li><li>数学</li><li>字符串</li></div></div><br><div><li>👍 529</li><li>👎 0</li></div>
 */

package leetcode.editor.cn;

import java.util.Stack;

// 227 基本计算器 II
public class BasicCalculatorIi227 {
    public static void main(String[] args) {
        Solution solution = new BasicCalculatorIi227().new Solution();
        long startTime = System.currentTimeMillis();
        System.out.println(Character.isDigit('+'));
        System.out.println(solution.calculate(" 3/2 "));
        long endTime = System.currentTimeMillis();
        double time = endTime - startTime;
        System.out.println("程序运行时间： " + ((time > 10000) ? time / 1000 + "s" : time + "ms"));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int calculate(String s) {
            //先处理前后的空格（因为会影响对最后一个数的判断）
            s = s.trim();
            Stack<Integer> stack = new Stack<>();
            int num = 0;
            //记录当前数的符号
            char preSign = '+';
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                //跳过空格
                if (c == ' ') continue;
                //如果是数字
                if (Character.isDigit(c))
                    num = 10 * num + (c - '0');
                //如果是符号或者是最后一个数，则将当前的数连同他的符号放入栈中
                if (!Character.isDigit(c) || i == s.length() - 1) {
                    int pre;
                    switch (preSign) {
                        case '+':
                            stack.push(num);
                            break;
                        case '-':
                            stack.push(-num);
                            break;
                        case '*':
                            pre = stack.pop();
                            stack.push(pre * num);
                            break;
                        case '/':
                            pre = stack.pop();
                            stack.push(pre / num);
                            break;
                    }
                    preSign = c;
                    num = 0;
                }
            }
            int result = 0;
            while (!stack.empty()) {
                result += stack.pop();
            }
            return result;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
