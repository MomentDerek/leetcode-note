package leetcode.editor.cn.util;

/**
 * 链表类
 * @author Moment
 */
public class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
    ListNode(int x, int next) { val = x; this.next = new ListNode(next); }

    /**
     * 链表节点的构造函数
     * @param arr 使用arr数组为参数，创建一个链表，当前的ListNode为链表头节点
     */
    public ListNode(int[] arr){
        if(arr == null || arr.length == 0) {
            throw new IllegalArgumentException("arr can not be empty");
        }

        this.val = arr[0];
        ListNode cur = this;
        for(int i = 1; i < arr.length; i ++){
            cur.next = new ListNode(arr[i]);
            cur = cur.next;
        }
    }

    /**
     * 以当前节点为头节点的链表信息字符串 方便查看
     * @return 转化为字符串的结果
     */
    @Override
    public String toString(){
        StringBuilder res = new StringBuilder();
        ListNode cur = this;
        while(cur != null){
            res.append(cur.val).append("->");
            cur = cur.next;
        }
        res.append("NULL");
        return res.toString();
    }
}