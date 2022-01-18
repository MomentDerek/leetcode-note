/**
 * <div class="title__3Vvk">请你设计并实现一个满足&nbsp; <a href="https://baike.baidu.com/item/LRU" target="_blank">LRU (最近最少使用) 缓存</a> 约束的数据结构。</div>
 *
 * <div class="title__3Vvk">实现 <code>LRUCache</code> 类：</div>
 *
 * <div class="original__bRMd">
 * <div>
 * <ul>
 * <li><code>LRUCache(int capacity)</code> 以 <strong>正整数</strong> 作为容量&nbsp;<code>capacity</code> 初始化 LRU 缓存</li>
 * <li><code>int get(int key)</code> 如果关键字 <code>key</code> 存在于缓存中，则返回关键字的值，否则返回 <code>-1</code> 。</li>
 * <li><code>void put(int key, int value)</code>&nbsp;如果关键字&nbsp;<code>key</code> 已经存在，则变更其数据值&nbsp;<code>value</code> ；如果不存在，则向缓存中插入该组&nbsp;<code>key-value</code> 。如果插入操作导致关键字数量超过&nbsp;<code>capacity</code> ，则应该 <strong>逐出</strong> 最久未使用的关键字。</li>
 * </ul>
 *
 * <p>函数 <code>get</code> 和 <code>put</code> 必须以 <code>O(1)</code> 的平均时间复杂度运行。</p>
 * </div>
 * </div>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>示例：</strong></p>
 *
 * <pre>
 * <strong>输入</strong>
 * ["LRUCache", "put", "put", "get", "put", "get", "put", "get", "get", "get"]
 * [[2], [1, 1], [2, 2], [1], [3, 3], [2], [4, 4], [1], [3], [4]]
 * <strong>输出</strong>
 * [null, null, null, 1, null, -1, null, -1, 3, 4]
 *
 * <strong>解释</strong>
 * LRUCache lRUCache = new LRUCache(2);
 * lRUCache.put(1, 1); // 缓存是 {1=1}
 * lRUCache.put(2, 2); // 缓存是 {1=1, 2=2}
 * lRUCache.get(1);    // 返回 1
 * lRUCache.put(3, 3); // 该操作会使得关键字 2 作废，缓存是 {1=1, 3=3}
 * lRUCache.get(2);    // 返回 -1 (未找到)
 * lRUCache.put(4, 4); // 该操作会使得关键字 1 作废，缓存是 {4=4, 3=3}
 * lRUCache.get(1);    // 返回 -1 (未找到)
 * lRUCache.get(3);    // 返回 3
 * lRUCache.get(4);    // 返回 4
 * </pre>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>1 &lt;= capacity &lt;= 3000</code></li>
 * <li><code>0 &lt;= key &lt;= 10000</code></li>
 * <li><code>0 &lt;= value &lt;= 10<sup>5</sup></code></li>
 * <li>最多调用 <code>2 * 10<sup>5</sup></code> 次 <code>get</code> 和 <code>put</code></li>
 * </ul>
 * <div><div>Related Topics</div><div><li>设计</li><li>哈希表</li><li>链表</li><li>双向链表</li></div></div><br><div><li>👍 1843</li><li>👎 0</li></div>
 */

package leetcode.editor.cn;

import java.util.HashMap;
import java.util.LinkedHashMap;

// 146 LRU 缓存
public class LruCache146 {
    public static void main(String[] args) {
        LRUCache lruCache = new LruCache146().new LRUCache(2);
        long startTime = System.currentTimeMillis();
        lruCache.put(1, 1);
        lruCache.put(2, 2);
        System.out.println(lruCache.get(1));
        lruCache.put(3, 3);
        System.out.println(lruCache.get(2));
        lruCache.put(4, 4);
        System.out.println(lruCache.get(1));
        System.out.println(lruCache.get(3));
        System.out.println(lruCache.get(4));
        long endTime = System.currentTimeMillis();
        double time = endTime - startTime;
        System.out.println("程序运行时间： " + ((time > 10000) ? time / 1000 + "s" : time + "ms"));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class LRUCache {

        //哈希表
        private HashMap<Integer, Node> map;
        //双向链表
        private DoubleList cache;
        //最大容量
        private int cap;

        public int get(int key) {
            //通过哈希表判断是否存在
            if (!map.containsKey(key)) return -1;
            //移至队头
            makeRecently(key);
            //通过哈希表查询值
            return map.get(key).val;
        }

        public void put(int key, int value) {
            //如果已存在，删除再插入
            if (map.containsKey(key)) {
                deleteKey(key);
                addRecently(key, value);
                return;
            }
            //如果满了，先移除一个最远的
            if (cap == cache.size()) {
                removeLeastRecently();
            }
            //执行插入
            addRecently(key, value);
        }

        public LRUCache(int capacity) {
            this.cap = capacity;
            map = new HashMap<>();
            cache = new DoubleList();
        }

        private void makeRecently(int key) {
            //获取节点
            Node x = map.get(key);
            //从链表中删除再插入
            cache.remove(x);
            cache.addLast(x);
        }

        private void addRecently(int key, int val) {
            Node x = new Node(key, val);
            //插入链表和哈希表中
            cache.addLast(x);
            map.put(key, x);
        }

        private void deleteKey(int key) {
            Node x = map.get(key);
            //从链表和哈希表中删除
            cache.remove(x);
            map.remove(key);
        }

        private void removeLeastRecently() {
            //移除最开头的，因为最新的都是从末尾插入
            Node deletedNode = cache.removeFirst();
            map.remove(deletedNode.key);
        }


    }

    class Node {
        public int key, val;
        public Node prev, next;

        public Node(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }

    class DoubleList {
        private Node head, tail;
        private int size;

        public DoubleList() {
            head = new Node(0, 0);
            tail = new Node(0, 0);
            //头的next是尾
            head.next = tail;
            //尾的pre是头
            tail.prev = head;
            size = 0;
        }

        public void addLast(Node x) {
            //x节点的next是尾，pre是尾的pre（原先的最后节点）
            x.prev = tail.prev;
            x.next = tail;
            //尾的pre（原先的最后节点）的next是x节点
            tail.prev.next = x;
            //尾的pre切换为x节点（此操作要最后进行）
            tail.prev = x;
            size++;
        }

        public void remove(Node x) {
            x.prev.next = x.next;
            x.next.prev = x.prev;
            size--;
        }

        public Node removeFirst() {
            if (head.next == tail) return null;
            Node first = head.next;
            remove(first);
            return first;
        }

        public int size() {
            return size;
        }
    }

    class LRUCache2 {
        private LinkedHashMap<Integer, Integer> cache;
        private int cap;

        public LRUCache2(int capacity) {
            cache = new LinkedHashMap<>();
            this.cap = capacity;
        }

        public int get(int key) {
            if (!cache.containsKey(key)) {
                return -1;
            }
            return moveRecently(key);
        }

        public void put(int key, int val) {
            if (cache.containsKey(key)) {
                cache.put(key, val);
                moveRecently(key);
                return;
            }
            if (cache.size() == cap) {
                cache.remove(cache.keySet().iterator().next());
            }
            cache.put(key, val);
        }

        private int moveRecently(int key) {
            Integer val = cache.get(key);
            cache.remove(key);
            cache.put(key, val);
            return val;
        }
    }

//leetcode submit region end(Prohibit modification and deletion)

}
