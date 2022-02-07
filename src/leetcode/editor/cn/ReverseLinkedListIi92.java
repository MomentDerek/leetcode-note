/**
 * 给你单链表的头指针 head 和两个整数 left 和 right ，其中 left <= right 。请你反转从位置 left 到位置 right 的链表节
 * 点，返回 反转后的链表 。
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：head = [1,2,3,4,5], left = 2, right = 4
 * 输出：[1,4,3,2,5]
 * <p>
 * <p>
 * 示例 2：
 * <p>
 * <p>
 * 输入：head = [5], left = 1, right = 1
 * 输出：[5]
 * <p>
 * <p>
 * <p>
 * <p>
 * 提示：
 * <p>
 * <p>
 * 链表中节点数目为 n
 * 1 <= n <= 500
 * -500 <= Node.val <= 500
 * 1 <= left <= right <= n
 * <p>
 * <p>
 * <p>
 * <p>
 * 进阶： 你可以使用一趟扫描完成反转吗？
 * Related Topics 链表 👍 1150 👎 0
 */

package leetcode.editor.cn;

import leetcode.editor.cn.util.ListNode;
import leetcode.editor.cn.util.TreeUtils;

import java.lang.annotation.Retention;

// 92 反转链表 II
public class ReverseLinkedListIi92 {
    public static void main(String[] args) {
        Solution solution = new ReverseLinkedListIi92().new Solution();
        ListNode input = new ListNode(TreeUtils.StrToIntArray("[3,5]"));
        long startTime = System.currentTimeMillis();
        System.out.println(solution.reverseBetween(input, 1, 1));
        long endTime = System.currentTimeMillis();
        System.out.println("程序运行时间： " + (endTime - startTime) + "ms");
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //preNode->leftNode->...->rightNode->nextNode转化为
        //preNode->rightNode->...->leftNode->nextNode
        public ListNode reverseBetween(ListNode head, int left, int right) {
            //用一个虚拟节点指向head，避免很多复杂的判断
            ListNode dummyNode = new ListNode(-1);
            dummyNode.next = head;
            ListNode preNode = dummyNode;
            //将preNode指向leftNode的前一个节点
            for (int i = 0; i < left - 1; i++) {
                preNode = preNode.next;
            }
            ListNode leftNode = preNode.next;
            ListNode rightNode = preNode;
            //定位到rightNode
            for (int i = 0; i < right - (left - 1); i++) {
                rightNode = rightNode.next;
            }
            ListNode nextNode = rightNode.next;
            //断开两端节点
            preNode.next = null;
            rightNode.next = null;
            //反转节点
            reverse(leftNode);
            //接回节点
            leftNode.next = nextNode;
            preNode.next = rightNode;
            return dummyNode.next;
        }

        private void reverse(ListNode head) {
            ListNode preTemp = null;
            ListNode cur = head;
            while (cur != null) {
                ListNode nextTemp = cur.next;
                cur.next = preTemp;
                preTemp = cur;
                cur = nextTemp;
            }
        }
    }

//leetcode submit region end(Prohibit modification and deletion)

}
