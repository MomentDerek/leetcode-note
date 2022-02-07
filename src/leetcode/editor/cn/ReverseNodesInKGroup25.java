/**
<p>给你一个链表，每 <em>k </em>个节点一组进行翻转，请你返回翻转后的链表。</p>

<p><em>k </em>是一个正整数，它的值小于或等于链表的长度。</p>

<p>如果节点总数不是 <em>k </em>的整数倍，那么请将最后剩余的节点保持原有顺序。</p>

<p><strong>进阶：</strong></p>

<ul>
	<li>你可以设计一个只使用常数额外空间的算法来解决此问题吗？</li>
	<li><strong>你不能只是单纯的改变节点内部的值</strong>，而是需要实际进行节点交换。</li>
</ul>

<p> </p>

<p><strong>示例 1：</strong></p>
<img alt="" src="https://assets.leetcode.com/uploads/2020/10/03/reverse_ex1.jpg" style="width: 542px; height: 222px;" />
<pre>
<strong>输入：</strong>head = [1,2,3,4,5], k = 2
<strong>输出：</strong>[2,1,4,3,5]
</pre>

<p><strong>示例 2：</strong></p>
<img alt="" src="https://assets.leetcode.com/uploads/2020/10/03/reverse_ex2.jpg" style="width: 542px; height: 222px;" />
<pre>
<strong>输入：</strong>head = [1,2,3,4,5], k = 3
<strong>输出：</strong>[3,2,1,4,5]
</pre>

<p><strong>示例 3：</strong></p>

<pre>
<strong>输入：</strong>head = [1,2,3,4,5], k = 1
<strong>输出：</strong>[1,2,3,4,5]
</pre>

<p><strong>示例 4：</strong></p>

<pre>
<strong>输入：</strong>head = [1], k = 1
<strong>输出：</strong>[1]
</pre>

<ul>
</ul>

<p><strong>提示：</strong></p>

<ul>
	<li>列表中节点的数量在范围 <code>sz</code> 内</li>
	<li><code>1 <= sz <= 5000</code></li>
	<li><code>0 <= Node.val <= 1000</code></li>
	<li><code>1 <= k <= sz</code></li>
</ul>
<div><div>Related Topics</div><div><li>递归</li><li>链表</li></div></div><br><div><li>👍 1460</li><li>👎 0</li></div>
*/
 
package leetcode.editor.cn;

import leetcode.editor.cn.util.ListNode;

import javax.xml.crypto.dsig.keyinfo.KeyInfoFactory;

// 25 K 个一组翻转链表
public class ReverseNodesInKGroup25{
    public static void main(String[] args) {
        Solution solution = new ReverseNodesInKGroup25().new Solution();
        long startTime=System.currentTimeMillis();
        long endTime=System.currentTimeMillis();
        double time = endTime - startTime;
        System.out.println("程序运行时间： " + ((time > 10000) ? time / 1000 + "s" : time + "ms"));
    }
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null) return null;
        ListNode rightNext = head;
        for (int i = 0; i < k; i++) {
            if (rightNext == null) return head;
            rightNext = rightNext.next;
        }
        ListNode newHead = reverse(head, rightNext);
        head.next = reverseKGroup(rightNext, k);
        return newHead;
    }

    //翻转head到lastNext的前一个节点（左闭右开）
    private ListNode reverse(ListNode head, ListNode lastNext) {
        ListNode pre = null, cur = head;
        while (cur != lastNext) {
            ListNode nextTemp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = nextTemp;
        }
        return pre;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
