/**
 * /**
 * nums1 中数字 x 的 下一个更大元素 是指 x 在 nums2 中对应位置 右侧 的 第一个 比 x 大的元素。
 * <p>
 * 给你两个 没有重复元素 的数组 nums1 和 nums2 ，下标从 0 开始计数，其中nums1 是 nums2 的子集。
 * <p>
 * 对于每个 0 <= i < nums1.length ，找出满足 nums1[i] == nums2[j] 的下标 j ，并且在 nums2 确定 nums2
 * <p>
 * [j] 的 下一个更大元素 。如果不存在下一个更大元素，那么本次查询的答案是 -1 。
 * <p>
 * 返回一个长度为 nums1.length 的数组 ans 作为答案，满足 ans[i] 是如上所述的 下一个更大元素 。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：nums1 = [4,1,2], nums2 = [1,3,4,2].
 * 输出：[-1,3,-1]
 * 解释：nums1 中每个值的下一个更大元素如下所述：
 * - 4 ，用加粗斜体标识，nums2 = [1,3,4,2]。不存在下一个更大元素，所以答案是 -1 。
 * - 1 ，用加粗斜体标识，nums2 = [1,3,4,2]。下一个更大元素是 3 。
 * - 2 ，用加粗斜体标识，nums2 = [1,3,4,2]。不存在下一个更大元素，所以答案是 -1 。
 * <p>
 * 示例 2：
 * <p>
 * <p>
 * 输入：nums1 = [2,4], nums2 = [1,2,3,4].
 * 输出：[3,-1]
 * 解释：nums1 中每个值的下一个更大元素如下所述：
 * - 2 ，用加粗斜体标识，nums2 = [1,2,3,4]。下一个更大元素是 3 。
 * - 4 ，用加粗斜体标识，nums2 = [1,2,3,4]。不存在下一个更大元素，所以答案是 -1 。
 * <p>
 * <p>
 * <p>
 * <p>
 * 提示：
 * <p>
 * <p>
 * 1 <= nums1.length <= nums2.length <= 1000
 * 0 <= nums1[i], nums2[i] <= 10⁴
 * nums1和nums2中所有整数 互不相同
 * nums1 中的所有整数同样出现在 nums2 中
 * <p>
 * <p>
 * <p>
 * <p>
 * 进阶：你可以设计一个时间复杂度为 O(nums1.length + nums2.length) 的解决方案吗？
 * Related Topics 栈 数组 哈希表 单调栈 👍 636 👎 0
 */

package leetcode.editor.cn;

import leetcode.editor.cn.util.ListNode;
import leetcode.editor.cn.util.TreeUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;


// 496 下一个更大元素 I
public class NextGreaterElementI496 {
    public static void main(String[] args) {
        Solution solution = new NextGreaterElementI496().new Solution();
        int[] nums1 = TreeUtils.StrToIntArray("[4,1,2]");
        int[] nums2 = TreeUtils.StrToIntArray("[1,3,4,2]");
        long startTime = System.currentTimeMillis();
        int[] result = solution.nextGreaterElement(nums1, nums2);
        long endTime = System.currentTimeMillis();
        System.out.println(Arrays.toString(nums1));
        System.out.println(Arrays.toString(nums2));
        System.out.println(Arrays.toString(result));
        System.out.println("程序运行时间： " + (endTime - startTime) + "ms");
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int[] nextGreaterElement(int[] nums1, int[] nums2) {
            Map<Integer, Integer> cache = new HashMap<>();
            Stack<Integer> stack = new Stack<>();
            for (int i = nums2.length - 1; i >= 0; i--) {
                while (!stack.empty() && nums2[i] >= stack.peek()) {
                    stack.pop();
                }
                cache.put(nums2[i], stack.empty() ? -1 : stack.peek());
                stack.push(nums2[i]);
            }
            int[] res = new int[nums1.length];
            for (int i = 0; i < res.length; i++) {
                res[i] = cache.getOrDefault(nums1[i], -1);
            }
            return res;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
