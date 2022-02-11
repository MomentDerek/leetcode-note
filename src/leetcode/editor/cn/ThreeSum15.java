/**
 * <p>给你一个包含 <code>n</code> 个整数的数组 <code>nums</code>，判断 <code>nums</code> 中是否存在三个元素 <em>a，b，c ，</em>使得 <em>a + b + c = </em>0 ？请你找出所有和为 <code>0</code> 且不重复的三元组。</p>
 *
 * <p><strong>注意：</strong>答案中不可以包含重复的三元组。</p>
 *
 * <p> </p>
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>nums = [-1,0,1,2,-1,-4]
 * <strong>输出：</strong>[[-1,-1,2],[-1,0,1]]
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>nums = []
 * <strong>输出：</strong>[]
 * </pre>
 *
 * <p><strong>示例 3：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>nums = [0]
 * <strong>输出：</strong>[]
 * </pre>
 *
 * <p> </p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>0 <= nums.length <= 3000</code></li>
 * <li><code>-10<sup>5</sup> <= nums[i] <= 10<sup>5</sup></code></li>
 * </ul>
 * <div><div>Related Topics</div><div><li>数组</li><li>双指针</li><li>排序</li></div></div><br><div><li>👍 4289</li><li>👎 0</li></div>
 */

package leetcode.editor.cn;

import leetcode.editor.cn.util.TreeUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// 15 三数之和
public class ThreeSum15 {
    public static void main(String[] args) {
        Solution solution = new ThreeSum15().new Solution();
        long startTime = System.currentTimeMillis();
        System.out.println(solution.threeSum(TreeUtils.StrToIntArray("[-1,0,1,2,-1,-4]")));
        long endTime = System.currentTimeMillis();
        double time = endTime - startTime;
        System.out.println("程序运行时间： " + ((time > 10000) ? time / 1000 + "s" : time + "ms"));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        int[] nums;

        public List<List<Integer>> threeSum(int[] nums) {
            this.nums = nums;
            Arrays.sort(nums);
            List<List<Integer>> result = new ArrayList<>();
            for (int i = 0; i < nums.length; i++) {
                List<List<Integer>> subResults = twoSum(-nums[i], i + 1);
                for (List<Integer> subResult : subResults) {
                    subResult.add(nums[i]);
                    result.add(subResult);
                }
                while ((i < nums.length - 1) && nums[i] == nums[i + 1]) i++;
            }
            return result;
        }

        private List<List<Integer>> twoSum(int target, int start) {
            List<List<Integer>> result = new ArrayList<>();
            int low = start, high = nums.length - 1;
            while (low < high) {
                int left = nums[low], right = nums[high];
                int sum = left + right;
                if (sum < target)
                    while (low < high && nums[low] == left) low++;
                else if (sum > target)
                    while (low < high && nums[high] == right) high--;
                else {
                    result.add(new ArrayList<>(Arrays.asList(left,right)));
                    while (low < high && nums[low] == left) low++;
                    while (low < high && nums[high] == right) high--;
                }
            }
            return result;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
