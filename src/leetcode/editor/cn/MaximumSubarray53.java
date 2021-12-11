/**
<p>给你一个整数数组 <code>nums</code> ，请你找出一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。</p>

<p><strong>子数组 </strong>是数组中的一个连续部分。</p>

<p>&nbsp;</p>

<p><strong>示例 1：</strong></p>

<pre>
<strong>输入：</strong>nums = [-2,1,-3,4,-1,2,1,-5,4]
<strong>输出：</strong>6
<strong>解释：</strong>连续子数组&nbsp;[4,-1,2,1] 的和最大，为&nbsp;6 。
</pre>

<p><strong>示例 2：</strong></p>

<pre>
<strong>输入：</strong>nums = [1]
<strong>输出：</strong>1
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>nums = [5,4,-1,7,8]
<strong>输出：</strong>23
</pre>

<p>&nbsp;</p>

<p><strong>提示：</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>-10<sup>4</sup> &lt;= nums[i] &lt;= 10<sup>4</sup></code></li>
</ul>

<p>&nbsp;</p>

<p><strong>进阶：</strong>如果你已经实现复杂度为 <code>O(n)</code> 的解法，尝试使用更为精妙的 <strong>分治法</strong> 求解。</p>
<div><div>Related Topics</div><div><li>数组</li><li>分治</li><li>动态规划</li></div></div><br><div><li>👍 4088</li><li>👎 0</li></div>
*/
 
package leetcode.editor.cn;
// 53 最大子数组和
public class MaximumSubarray53{
    public static void main(String[] args) {
        Solution solution = new MaximumSubarray53().new Solution();
        long startTime=System.currentTimeMillis();
        long endTime=System.currentTimeMillis();
        double time = endTime - startTime;
        System.out.println("程序运行时间： " + ((time > 10000) ? time / 1000 + "s" : time + "ms"));
    }
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int maxSubArray(int[] nums) {
        int length = nums.length;
        //dp的定义：以当前数为结尾的最大子数组和
        int[] dp = new int[length];
        dp[0] = nums[0];
        for (int i = 1; i < length; i++) {
            dp[i] = Math.max(nums[i], dp[i - 1] + nums[i]);
        }
        int result = Integer.MIN_VALUE;
        for (int i : dp) {
            if (i>result) result = i;
        }
        return result;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
