/**
 * <p>你有一个带有四个圆形拨轮的转盘锁。每个拨轮都有10个数字： <code>'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'</code> 。每个拨轮可以自由旋转：例如把 <code>'9'</code> 变为 <code>'0'</code>，<code>'0'</code> 变为 <code>'9'</code> 。每次旋转都只能旋转一个拨轮的一位数字。</p>
 *
 * <p>锁的初始数字为 <code>'0000'</code> ，一个代表四个拨轮的数字的字符串。</p>
 *
 * <p>列表 <code>deadends</code> 包含了一组死亡数字，一旦拨轮的数字和列表里的任何一个元素相同，这个锁将会被永久锁定，无法再被旋转。</p>
 *
 * <p>字符串 <code>target</code> 代表可以解锁的数字，你需要给出解锁需要的最小旋转次数，如果无论如何不能解锁，返回 <code>-1</code> 。</p>
 *
 * <p> </p>
 *
 * <p><strong>示例 1:</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>deadends = ["0201","0101","0102","1212","2002"], target = "0202"
 * <strong>输出：</strong>6
 * <strong>解释：</strong>
 * 可能的移动序列为 "0000" -> "1000" -> "1100" -> "1200" -> "1201" -> "1202" -> "0202"。
 * 注意 "0000" -> "0001" -> "0002" -> "0102" -> "0202" 这样的序列是不能解锁的，
 * 因为当拨动到 "0102" 时这个锁就会被锁定。
 * </pre>
 *
 * <p><strong>示例 2:</strong></p>
 *
 * <pre>
 * <strong>输入:</strong> deadends = ["8888"], target = "0009"
 * <strong>输出：</strong>1
 * <strong>解释：</strong>
 * 把最后一位反向旋转一次即可 "0000" -> "0009"。
 * </pre>
 *
 * <p><strong>示例 3:</strong></p>
 *
 * <pre>
 * <strong>输入:</strong> deadends = ["8887","8889","8878","8898","8788","8988","7888","9888"], target = "8888"
 * <strong>输出：</strong>-1
 * <strong>解释：
 * </strong>无法旋转到目标数字且不被锁定。
 * </pre>
 *
 * <p><strong>示例 4:</strong></p>
 *
 * <pre>
 * <strong>输入:</strong> deadends = ["0000"], target = "8888"
 * <strong>输出：</strong>-1
 * </pre>
 *
 * <p> </p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>1 <= deadends.length <= 500</code></li>
 * <li><code><font face="monospace">deadends[i].length == 4</font></code></li>
 * <li><code><font face="monospace">target.length == 4</font></code></li>
 * <li><code>target</code> <strong>不在</strong> <code>deadends</code> 之中</li>
 * <li><code>target</code> 和 <code>deadends[i]</code> 仅由若干位数字组成</li>
 * </ul>
 * <div><div>Related Topics</div><div><li>广度优先搜索</li><li>数组</li><li>哈希表</li><li>字符串</li></div></div><br><div><li>👍 420</li><li>👎 0</li></div>
 */

package leetcode.editor.cn;

import java.util.*;

// 752 打开转盘锁
public class OpenTheLock752 {
    public static void main(String[] args) {
        Solution solution = new OpenTheLock752().new Solution();
        long startTime = System.currentTimeMillis();
        int res = solution.openLock(new String[]{"8887","8889","8878","8898","8788","8988","7888","9888"}, "8888");
        long endTime = System.currentTimeMillis();
        double time = endTime - startTime;
        System.out.println(res);
        System.out.println("程序运行时间： " + ((time > 10000) ? time / 1000 + "s" : time + "ms"));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    //单向bfs，传统逻辑，比下面的方法慢120%
    class Solution {
        public int openLock(String[] deadEnds, String target) {
            //创建并写入死亡数字
            Set<String> dead = new HashSet<>(Arrays.asList(deadEnds));
            //bfs队列
            Queue<String> q = new LinkedList<>();
            Set<String> visited = new HashSet<>();
            //解锁次数
            int res = 0;
            q.offer("0000");
            visited.add("0000");
            while (!q.isEmpty()) {
                int size = q.size();
                for (int i = 0; i < size; i++) {
                    String cur = q.poll();
                    //判断结束条件
                    if (Objects.equals(cur, target)) return res;
                    //死亡后忽略
                    if (dead.contains(cur)) continue;
                    //将临近的结果放入队列
                    for (int index = 0; index < 4; index++) {
                        String add = add(cur, index);
                        if (!visited.contains(add)) {
                            visited.add(add);
                            q.offer(add);
                        }
                        String minus = minus(cur, index);
                        if (!visited.contains(minus)) {
                            visited.add(minus);
                            q.offer(minus);
                        }
                    }
                }
                res++;
            }
            return -1;
        }

        private String add(String source, int index) {
            char[] temp = source.toCharArray();
            if (temp[index] == '9')
                temp[index] = '0';
            else
                temp[index] += 1;
            return new String(temp);
        }

        private String minus(String source, int index) {
            char[] temp = source.toCharArray();
            if (temp[index] == '0')
                temp[index] = '9';
            else
                temp[index] -= 1;
            return new String(temp);
        }
    }

    //课本代码，使用双向bfs，且使用一定的优化
    class Solution2 {
        int openLock(String[] deadends, String target) {
            Set<String> deads = new HashSet<>(Arrays.asList(deadends));
            // 用集合不用队列，可以快速判断元素是否存在
            Set<String> q1 = new HashSet<>();
            Set<String> q2 = new HashSet<>();
            Set<String> visited = new HashSet<>();

            int step = 0;
            q1.add("0000");
            q2.add(target);

            while (!q1.isEmpty() && !q2.isEmpty()) {
                //哈希集合在遍历的过程中不能修改，用 temp 存储扩散结果
                //存储的是当前扩散出去的节点（下一步需要遍历的节点），不包括已经遍历过的节点
                Set<String> temp = new HashSet<>();

                /*将q1中的所有节点向周围扩散*/
                for (String cur : q1) {
                    /* 判断是否到达终点 */
                    if (deads.contains(cur))
                        continue;
                    if (q2.contains(cur))
                        return step;
                    visited.add(cur);

                    /*将一个节点的未遍历相邻节点加入集合*/
                    for (int j = 0; j < 4; j++) {
                        String up = add(cur, j);
                        if (!visited.contains(up))
                            temp.add(up);
                        String down = minus(cur, j);
                        if (!visited.contains(down))
                            temp.add(down);
                    }
                }
                /* 在这里增加步数 */
                step++;
                // temp 相当于 q1
                // 这里交换 q1 q2，下一轮 while 就是扩散 q2
                q1 = q2;
                q2 = temp;
            }
            return -1;
        }

        private String add(String source, int index) {
            char[] temp = source.toCharArray();
            if (temp[index] == '9')
                temp[index] = '0';
            else
                temp[index] += 1;
            return new String(temp);
        }

        private String minus(String source, int index) {
            char[] temp = source.toCharArray();
            if (temp[index] == '0')
                temp[index] = '9';
            else
                temp[index] -= 1;
            return new String(temp);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
