/**
 * <p>给你一个整数数组 <code>nums</code> ，数组中的元素 <strong>互不相同</strong> 。返回该数组所有可能的子集（幂集）。</p>
 *
 * <p>解集 <strong>不能</strong> 包含重复的子集。你可以按 <strong>任意顺序</strong> 返回解集。</p>
 *
 * <p> </p>
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>nums = [1,2,3]
 * <strong>输出：</strong>[[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>nums = [0]
 * <strong>输出：</strong>[[],[0]]
 * </pre>
 *
 * <p> </p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>1 <= nums.length <= 10</code></li>
 * <li><code>-10 <= nums[i] <= 10</code></li>
 * <li><code>nums</code> 中的所有元素 <strong>互不相同</strong></li>
 * </ul>
 * <div><div>Related Topics</div><div><li>位运算</li><li>数组</li><li>回溯</li></div></div><br><div><li>👍 1469</li><li>👎 0</li></div>
 */

package leetcode.editor.cn;

import leetcode.editor.cn.util.TreeUtils;

import java.util.ArrayList;
import java.util.List;

// 78 子集
public class Subsets78 {
    public static void main(String[] args) {
        Solution solution = new Subsets78().new Solution();
        long startTime = System.currentTimeMillis();
        System.out.println(solution.subsets(TreeUtils.StrToIntArray("[1,2,3]")));
        long endTime = System.currentTimeMillis();
        double time = endTime - startTime;
        System.out.println("程序运行时间： " + ((time > 10000) ? time / 1000 + "s" : time + "ms"));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {

        private int[] nums;
        private List<List<Integer>> result;

        public List<List<Integer>> subsets(int[] nums) {
            this.nums = nums;
            this.result = new ArrayList<>();
            backtrack(0, new ArrayList<>());
            return result;
        }

        private void backtrack(int start, List<Integer> track) {
            //每一次进入都是一个结果，所以每次都放入
            result.add(new ArrayList<>(track));
            for (int i = start; i < nums.length; i++) {
                track.add(nums[i]);
                backtrack(i + 1, track);
                track.remove(track.size()-1);
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
