/**
 * 给你一个单链表的头节点 head ，请你判断该链表是否为回文链表。如果是，返回 true ；否则，返回 false 。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：head = [1,2,2,1]
 * 输出：true
 * <p>
 * <p>
 * 示例 2：
 * <p>
 * <p>
 * 输入：head = [1,2]
 * 输出：false
 * <p>
 * <p>
 * <p>
 * <p>
 * 提示：
 * <p>
 * <p>
 * 链表中节点数目在范围[1, 10⁵] 内
 * 0 <= Node.val <= 9
 * <p>
 * <p>
 * <p>
 * <p>
 * 进阶：你能否用 O(n) 时间复杂度和 O(1) 空间复杂度解决此题？
 * Related Topics 栈 递归 链表 双指针 👍 1249 👎 0
 */

package leetcode.editor.cn;

import leetcode.editor.cn.util.ListNode;
import leetcode.editor.cn.util.TreeUtils;

import java.util.Stack;

// 234 回文链表
public class PalindromeLinkedList234 {
    public static void main(String[] args) {
        Solution solution = new PalindromeLinkedList234().new Solution();
        ListNode input = new ListNode(TreeUtils.StrToIntArray("[1,2,3,2,1]"));
        long startTime = System.currentTimeMillis();
        boolean result = solution.isPalindrome(input);
        long endTime = System.currentTimeMillis();
        System.out.println(result);
        System.out.println(input);
        System.out.println("程序运行时间： " + (endTime - startTime) + "ms");
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution1 {
        private ListNode left;

        public boolean isPalindrome(ListNode head) {
            left = head;
            return traverse(head);
        }

        private boolean traverse(ListNode right) {
            if (right == null) return true;
            boolean res = traverse(right.next);
            res = res && (right.val == left.val);
            left = left.next;
            return res;
        }
    }

    class Solution {
        public boolean isPalindrome(ListNode head) {
            if(head == null || head.next == null) {
                return true;
            }
            ListNode slow = head, fast = head;
            //用于反转链表，cur是slow，pre是cur的前节点
            ListNode cur = head, pre = null;
            while(fast != null && fast.next != null) {
                cur = slow;
                slow = slow.next;
                fast = fast.next.next;
                //将cur的指针指向pre，然后更新pre
                cur.next = pre;
                pre = cur;
            }
            //用于边遍历边反转回来，需要在判断中点之前，因为slow可能会在判断后改变
            pre = slow;
            //单数链表节点，双指针结束后指向中点，需要往后一个
            if(fast != null) {
                slow = slow.next;
            }
            while(cur != null && slow != null) {
                if(cur.val != slow.val) {
                    reverse(cur, pre);
                    return false;
                }
                ListNode preTemp = cur.next;
                cur.next = pre;
                pre = cur;
                cur = preTemp;
                slow = slow.next;
            }
            return true;
        }

        private ListNode reverse(ListNode curNode, ListNode preNode) {
            ListNode pre = preNode, cur = curNode;
            while (cur != null) {
                ListNode next = cur.next;
                cur.next = pre;
                pre = cur;
                cur = next;
            }
            return pre;
        }
    }


    //自写方案
    class Solution2 {
        public boolean isPalindrome(ListNode head) {
            Stack<Integer> stack = new Stack<>();
            ListNode fast = head, slow = head;
            while (fast != null && fast.next != null) {
                stack.push(slow.val);
                fast = fast.next.next;
                slow = slow.next;
            }
            if (fast != null) slow = slow.next;
            while (!stack.isEmpty()) {
                if (stack.pop() != slow.val) return false;
                slow = slow.next;
            }
            return true;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
