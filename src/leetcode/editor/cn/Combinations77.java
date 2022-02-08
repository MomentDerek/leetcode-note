/**
 * <p>给定两个整数 <code>n</code> 和 <code>k</code>，返回范围 <code>[1, n]</code> 中所有可能的 <code>k</code> 个数的组合。</p>
 *
 * <p>你可以按 <strong>任何顺序</strong> 返回答案。</p>
 *
 * <p> </p>
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>n = 4, k = 2
 * <strong>输出：</strong>
 * [
 * [2,4],
 * [3,4],
 * [2,3],
 * [1,2],
 * [1,3],
 * [1,4],
 * ]</pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>n = 1, k = 1
 * <strong>输出：</strong>[[1]]</pre>
 *
 * <p> </p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>1 <= n <= 20</code></li>
 * <li><code>1 <= k <= n</code></li>
 * </ul>
 * <div><div>Related Topics</div><div><li>数组</li><li>回溯</li></div></div><br><div><li>👍 852</li><li>👎 0</li></div>
 */

package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

// 77 组合
public class Combinations77 {
    public static void main(String[] args) {
        Solution solution = new Combinations77().new Solution();
        long startTime = System.currentTimeMillis();
        System.out.println(solution.combine(4, 2));
        long endTime = System.currentTimeMillis();
        double time = endTime - startTime;
        System.out.println("程序运行时间： " + ((time > 10000) ? time / 1000 + "s" : time + "ms"));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        int n, k;
        List<List<Integer>> result;

        public List<List<Integer>> combine(int n, int k) {
            this.n = n;
            this.k = k;
            this.result = new ArrayList<>();
            backtrack(1, new ArrayList<>());
            return result;
        }

        private void backtrack(int start, List<Integer> track) {
            //层数够了再放入
            if (track.size() == k) {
                result.add(new ArrayList<>(track));
                return;
            }
            for (int i = start; i <= n; i++) {
                track.add(i);
                backtrack(i + 1, track);
                track.remove(track.size() - 1);
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)
}
