/**
 * <p>给定一个不含重复数字的数组 <code>nums</code> ，返回其 <strong>所有可能的全排列</strong> 。你可以 <strong>按任意顺序</strong> 返回答案。</p>
 *
 * <p> </p>
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>nums = [1,2,3]
 * <strong>输出：</strong>[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>nums = [0,1]
 * <strong>输出：</strong>[[0,1],[1,0]]
 * </pre>
 *
 * <p><strong>示例 3：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>nums = [1]
 * <strong>输出：</strong>[[1]]
 * </pre>
 *
 * <p> </p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>1 <= nums.length <= 6</code></li>
 * <li><code>-10 <= nums[i] <= 10</code></li>
 * <li><code>nums</code> 中的所有整数 <strong>互不相同</strong></li>
 * </ul>
 * <div><div>Related Topics</div><div><li>数组</li><li>回溯</li></div></div><br><div><li>👍 1674</li><li>👎 0</li></div>
 */

package leetcode.editor.cn;

import java.util.*;

// 46 全排列 （回溯算法）
public class Permutations46 {
    public static void main(String[] args) {
        Solution solution = new Permutations46().new Solution();
        int[] nums = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        long startTime = System.currentTimeMillis();
        List<List<Integer>> permute = solution.permute(nums);
        long endTime = System.currentTimeMillis();
        double time = endTime - startTime;
        System.out.println(permute);
        System.out.println("程序运行时间： " + ((time > 10000) ? time / 1000 + "s" : time + "ms"));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    //带used判断表的版本（本机测试会快20%）
    class Solution {
        //结果
        List<List<Integer>> res;
        //已经使用过的数字的记录表
        boolean[] used;
        //所有的数字
        int[] nums;

        public List<List<Integer>> permute(int[] nums) {
            //初始化
            this.res = new ArrayList<>();
            this.used = new boolean[nums.length];
            this.nums = nums;
            //开始递归
            trackBack(0, new ArrayList<>());
            return res;
        }

        private void trackBack( int depth,
                               List<Integer> path) {
            //满足结束条件
            if (depth == nums.length) {
                //提交路径（结果）
                res.add(new ArrayList<>(path));
                return;
            }
            //遍历子节点列表
            for (int i = 0; i < nums.length; i++) {
                //排除不符合规则的子节点
                if (!used[i]) {
                    //选择子节点
                    path.add(nums[i]);
                    used[i] = true;
                    trackBack(depth + 1, path);
                    //撤销选择
                    used[i] = false;
                    path.remove(path.size()-1);
                }
            }
        }
    }
    //原始版本（取消了LinkedList，因为LinkedList在加入res数组耗费的时间非常多）
    class Solution2 {
        //结果
        List<List<Integer>> res;
        int[] nums;

        public List<List<Integer>> permute(int[] nums) {
            //初始化
            res = new ArrayList<>();
            this.nums = nums;
            //开始递归
            trackBack(new ArrayList<>());
            return res;
        }

        private void trackBack(ArrayList<Integer> track) {
            //满足结束条件
            if (track.size() == nums.length) {
                //提交路径（结果）
                res.add(new ArrayList<>(track));
                return;
            }
            //遍历子节点列表
            for (int num : nums) {
                //排除不符合规则的子节点
                if (track.contains(num)) continue;
                //选择子节点
                track.add(num);
                trackBack(track);
                //撤销选择
                track.remove(track.size()-1);
            }
        }
    }

    //leetcode submit region end(Prohibit modification and deletion)

}
