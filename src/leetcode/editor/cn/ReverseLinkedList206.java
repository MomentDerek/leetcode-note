/**
 * 给你单链表的头节点 head ，请你反转链表，并返回反转后的链表。
 * <p>
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：head = [1,2,3,4,5]
 * 输出：[5,4,3,2,1]
 * <p>
 * <p>
 * 示例 2：
 * <p>
 * <p>
 * 输入：head = [1,2]
 * 输出：[2,1]
 * <p>
 * <p>
 * 示例 3：
 * <p>
 * <p>
 * 输入：head = []
 * 输出：[]
 * <p>
 * <p>
 * <p>
 * <p>
 * 提示：
 * <p>
 * <p>
 * 链表中节点的数目范围是 [0, 5000]
 * -5000 <= Node.val <= 5000
 * <p>
 * <p>
 * <p>
 * <p>
 * 进阶：链表可以选用迭代或递归方式完成反转。你能否用两种方法解决这道题？
 * <p>
 * <p>
 * Related Topics 递归 链表 👍 2247 👎 0
 */

package leetcode.editor.cn;

import com.sun.source.tree.WhileLoopTree;
import leetcode.editor.cn.util.ListNode;
import leetcode.editor.cn.util.TreeUtils;

// 206 反转链表
public class ReverseLinkedList206 {
    public static void main(String[] args) {
        Solution solution = new ReverseLinkedList206().new Solution();
        ListNode input = new ListNode(TreeUtils.StrToIntArray("[1,2,3,4,5]"));
        long startTime = System.currentTimeMillis();
        ListNode result = solution.reverseList(input);
        long endTime = System.currentTimeMillis();
        System.out.println(result);
        System.out.println("程序运行时间： " + (endTime - startTime) + "ms");
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    //秀操作，递归反转
    class Solution {
        public ListNode reverseList(ListNode head) {
            if (head == null || head.next == null) return head;
            ListNode last = reverseList(head.next);
            head.next.next = head;
            head.next = null;
            return last;
        }
    }

    //常规版
    class Solution1 {
        public ListNode reverseList(ListNode head) {
            if (head == null || head.next == null) return head;
            ListNode cur = head, pre = null;
            while (cur.next != null) {
                ListNode tempNext = cur.next;
                cur.next = pre;
                pre = cur;
                cur = tempNext;
            }
            cur.next = pre;
            return cur;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
