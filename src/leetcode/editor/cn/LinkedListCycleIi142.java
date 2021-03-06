/**
 * <p>给定一个链表，返回链表开始入环的第一个节点。&nbsp;如果链表无环，则返回&nbsp;<code>null</code>。</p>
 *
 * <p>如果链表中有某个节点，可以通过连续跟踪 <code>next</code> 指针再次到达，则链表中存在环。 为了表示给定链表中的环，评测系统内部使用整数 <code>pos</code> 来表示链表尾连接到链表中的位置（索引从 0 开始）。如果 <code>pos</code> 是 <code>-1</code>，则在该链表中没有环。<strong>注意：<code>pos</code> 不作为参数进行传递</strong>，仅仅是为了标识链表的实际情况。</p>
 *
 * <p><strong>不允许修改 </strong>链表。</p>
 *
 * <ul>
 * </ul>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>示例 1：</strong></p>
 *
 * <p><img alt="" src="https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2018/12/07/circularlinkedlist.png" style="height: 97px; width: 300px;" /></p>
 *
 * <pre>
 * <strong>输入：</strong>head = [3,2,0,-4], pos = 1
 * <strong>输出：</strong>返回索引为 1 的链表节点
 * <strong>解释：</strong>链表中有一个环，其尾部连接到第二个节点。
 * </pre>
 *
 * <p><strong>示例&nbsp;2：</strong></p>
 *
 * <p><img alt="" src="https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2018/12/07/circularlinkedlist_test2.png" style="height: 74px; width: 141px;" /></p>
 *
 * <pre>
 * <strong>输入：</strong>head = [1,2], pos = 0
 * <strong>输出：</strong>返回索引为 0 的链表节点
 * <strong>解释：</strong>链表中有一个环，其尾部连接到第一个节点。
 * </pre>
 *
 * <p><strong>示例 3：</strong></p>
 *
 * <p><img alt="" src="https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2018/12/07/circularlinkedlist_test3.png" style="height: 45px; width: 45px;" /></p>
 *
 * <pre>
 * <strong>输入：</strong>head = [1], pos = -1
 * <strong>输出：</strong>返回 null
 * <strong>解释：</strong>链表中没有环。
 * </pre>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li>链表中节点的数目范围在范围 <code>[0, 10<sup>4</sup>]</code> 内</li>
 * <li><code>-10<sup>5</sup> &lt;= Node.val &lt;= 10<sup>5</sup></code></li>
 * <li><code>pos</code> 的值为 <code>-1</code> 或者链表中的一个有效索引</li>
 * </ul>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>进阶：</strong>你是否可以使用 <code>O(1)</code> 空间解决此题？</p>
 * <div><div>Related Topics</div><div><li>哈希表</li><li>链表</li><li>双指针</li></div></div><br><div><li>👍 1283</li><li>👎 0</li></div>
 */

package leetcode.editor.cn;

import leetcode.editor.cn.util.ListNode;

// 142 环形链表 II
public class LinkedListCycleIi142 {
    public static void main(String[] args) {
        Solution solution = new LinkedListCycleIi142().new Solution();
        ListNode input = new ListNode(new int[]{1});
        //input.next.next.next.next = input.next;
        long startTime = System.currentTimeMillis();
        ListNode res = solution.detectCycle(input);
        long endTime = System.currentTimeMillis();
        double time = endTime - startTime;
        System.out.println(res == null ? "null" : res.val);
        System.out.println("程序运行时间： " + ((time > 10000) ? time / 1000 + "s" : time + "ms"));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    public class Solution {
        public ListNode detectCycle(ListNode head) {
            ListNode fast, slow;
            fast = slow = head;
            boolean circleFlag = false;
            while (fast != null && fast.next != null) {
                fast = fast.next.next;
                slow = slow.next;
                if (fast == slow) {
                    circleFlag = true;
                    break;
                }
            }
            if (!circleFlag) return null;
            //这里是找环的起始点，在判断完环是否存在后，有这么个关系：
            //环的起始点result，链表头head，当前位置now
            //从head到result的距离为a
            //从result到now的距离为b
            //因为fast是绕了一圈追上slow的，slow走了a+b，fast走了2a+2b，由此将两者作差，可知一圈环为a+b
            //再回顾一下now，是result走距离b得到的，再走距离a即可完成一圈环，回到result
            //同时，从head再走距离a也可得到result
            //所以用两个指针，从now和head以相同的速度同时出发，碰头的地点自然就是result
            slow = head;
            while (slow != fast) {
                slow = slow.next;
                fast = fast.next;
            }
            return slow;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
