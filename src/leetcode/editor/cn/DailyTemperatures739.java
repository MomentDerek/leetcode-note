/**
 * 请根据每日 气温 列表 temperatures ，请计算在每一天需要等几天才会有更高的温度。如果气温在这之后都不会升高，请在该位置用 0 来代替。
 * <p>
 * 示例 1:
 * <p>
 * <p>
 * 输入: temperatures = [73,74,75,71,69,72,76,73]
 * 输出: [1,1,4,2,1,1,0,0]
 * <p>
 * <p>
 * 示例 2:
 * <p>
 * <p>
 * 输入: temperatures = [30,40,50,60]
 * 输出: [1,1,1,0]
 * <p>
 * <p>
 * 示例 3:
 * <p>
 * <p>
 * 输入: temperatures = [30,60,90]
 * 输出: [1,1,0]
 * <p>
 * <p>
 * <p>
 * 提示：
 * <p>
 * <p>
 * 1 <= temperatures.length <= 10⁵
 * 30 <= temperatures[i] <= 100
 * <p>
 * Related Topics 栈 数组 单调栈 👍 1004 👎 0
 */

package leetcode.editor.cn;

import java.util.Stack;

// 739 每日温度
public class DailyTemperatures739 {
    public static void main(String[] args) {
        Solution solution = new DailyTemperatures739().new Solution();
        long startTime = System.currentTimeMillis();
        long endTime = System.currentTimeMillis();
        System.out.println("程序运行时间： " + (endTime - startTime) + "ms");
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int[] dailyTemperatures(int[] temperatures) {
            int[] res = new int[temperatures.length];
            Stack<Integer> stack = new Stack<>();
            for (int i = temperatures.length - 1; i >= 0; i--) {
                while (!stack.empty() && temperatures[i] >= temperatures[stack.peek()])
                    stack.pop();
                res[i] = stack.empty() ? 0 :(stack.peek() - i);
                stack.push(i);
            }
            return res;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
