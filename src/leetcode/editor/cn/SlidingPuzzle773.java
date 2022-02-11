/**
 * <p>在一个 2 x 3 的板上（<code>board</code>）有 5 块砖瓦，用数字 <code>1~5</code> 来表示, 以及一块空缺用&nbsp;<code>0</code>&nbsp;来表示.</p>
 *
 * <p>一次移动定义为选择&nbsp;<code>0</code>&nbsp;与一个相邻的数字（上下左右）进行交换.</p>
 *
 * <p>最终当板&nbsp;<code>board</code>&nbsp;的结果是&nbsp;<code>[[1,2,3],[4,5,0]]</code>&nbsp;谜板被解开。</p>
 *
 * <p>给出一个谜板的初始状态，返回最少可以通过多少次移动解开谜板，如果不能解开谜板，则返回 -1 。</p>
 *
 * <p><strong>示例：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>board = [[1,2,3],[4,0,5]]
 * <strong>输出：</strong>1
 * <strong>解释：</strong>交换 0 和 5 ，1 步完成
 * </pre>
 *
 * <pre>
 * <strong>输入：</strong>board = [[1,2,3],[5,4,0]]
 * <strong>输出：</strong>-1
 * <strong>解释：</strong>没有办法完成谜板
 * </pre>
 *
 * <pre>
 * <strong>输入：</strong>board = [[4,1,2],[5,0,3]]
 * <strong>输出：</strong>5
 * <strong>解释：</strong>
 * 最少完成谜板的最少移动次数是 5 ，
 * 一种移动路径:
 * 尚未移动: [[4,1,2],[5,0,3]]
 * 移动 1 次: [[4,1,2],[0,5,3]]
 * 移动 2 次: [[0,1,2],[4,5,3]]
 * 移动 3 次: [[1,0,2],[4,5,3]]
 * 移动 4 次: [[1,2,0],[4,5,3]]
 * 移动 5 次: [[1,2,3],[4,5,0]]
 * </pre>
 *
 * <pre>
 * <strong>输入：</strong>board = [[3,2,4],[1,5,0]]
 * <strong>输出：</strong>14
 * </pre>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>board</code>&nbsp;是一个如上所述的 2 x 3 的数组.</li>
 * <li><code>board[i][j]</code>&nbsp;是一个&nbsp;<code>[0, 1, 2, 3, 4, 5]</code>&nbsp;的排列.</li>
 * </ul>
 * <div><div>Related Topics</div><div><li>广度优先搜索</li><li>数组</li><li>矩阵</li></div></div><br><div><li>👍 244</li><li>👎 0</li></div>
 */

package leetcode.editor.cn;

import java.util.*;

// 773 滑动谜题
public class SlidingPuzzle773 {
    public static void main(String[] args) {
        Solution solution = new SlidingPuzzle773().new Solution();
        long startTime = System.currentTimeMillis();
        int[][] input = {{1,2,3},{4,0,5}};
        System.out.println(solution.slidingPuzzle(input));
        long endTime = System.currentTimeMillis();
        double time = endTime - startTime;
        System.out.println("程序运行时间： " + ((time > 10000) ? time / 1000 + "s" : time + "ms"));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    //自写
    class Solution {
        private final int[][] neighbor = {
                {1, 3},
                {0, 4, 2},
                {1,5},
                {0,4},
                {3,1,5},
                {4,2}
        };

        public int slidingPuzzle(int[][] board) {
            int m = 2, n = 3;
            String target = "123450";
            char[] start = new char[6];
            //将传入board写入start
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    start[i * 3 + j] = (char) (board[i][j] + '0');
                }
            }

            Queue<String> q = new LinkedList<>();
            Set<String> visited = new TreeSet<>();
            q.offer(new String(start));
            int step = 0;

            while (!q.isEmpty()) {
                //当前层大小
                int size = q.size();
                //遍历当前层
                for (int i = 0; i < size; i++) {
                    String cur = q.poll();
                    //符合条件
                    if (cur.equals(target)) return step;
                    int index = 0;
                    //定位index到0的位置
                    while (cur.charAt(index) != '0') index++;
                    //遍历所有
                    for (int neighborIndex : neighbor[index]) {
                        char[] newBoard = cur.toCharArray();
                        swap(newBoard,index,neighborIndex);
                        String newString = new String(newBoard);
                        if (!visited.contains(newString)) {
                            q.offer(newString);
                            visited.add(newString);
                        }
                    }
                }
                step++;
            }

            return -1;
        }

        private void swap(char[] board, int index1, int index2) {
            char temp = board[index1];
            board[index1] = board[index2];
            board[index2] = temp;
        }
    }

    class Solution2 {
        int[][] neighbors = {{1, 3}, {0, 2, 4}, {1, 5}, {0, 4}, {1, 3, 5}, {2, 4}};

        public int slidingPuzzle(int[][] board) {
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < 2; ++i) {
                for (int j = 0; j < 3; ++j) {
                    sb.append(board[i][j]);
                }
            }
            String initial = sb.toString();
            if ("123450".equals(initial)) {
                return 0;
            }

            PriorityQueue<AStar> pq = new PriorityQueue<AStar>((a, b) -> a.f - b.f);
            pq.offer(new AStar(initial, 0));
            Set<String> seen = new HashSet<String>();
            seen.add(initial);

            while (!pq.isEmpty()) {
                AStar node = pq.poll();
                for (String nextStatus : get(node.status)) {
                    if (!seen.contains(nextStatus)) {
                        if ("123450".equals(nextStatus)) {
                            return node.g + 1;
                        }
                        pq.offer(new AStar(nextStatus, node.g + 1));
                        seen.add(nextStatus);
                    }
                }
            }

            return -1;
        }

        // 枚举 status 通过一次交换操作得到的状态
        public List<String> get(String status) {
            List<String> ret = new ArrayList<String>();
            char[] array = status.toCharArray();
            int x = status.indexOf('0');
            for (int y : neighbors[x]) {
                swap(array, x, y);
                ret.add(new String(array));
                swap(array, x, y);
            }
            return ret;
        }

        public void swap(char[] array, int x, int y) {
            char temp = array[x];
            array[x] = array[y];
            array[y] = temp;
        }
    }

    class AStar {
        // 曼哈顿距离
        public int[][] dist = {
                {0, 1, 2, 1, 2, 3},
                {1, 0, 1, 2, 1, 2},
                {2, 1, 0, 3, 2, 1},
                {1, 2, 3, 0, 1, 2},
                {2, 1, 2, 1, 0, 1},
                {3, 2, 1, 2, 1, 0}
        };

        public String status;
        public int f, g, h;

        public AStar(String status, int g) {
            this.status = status;
            this.g = g;
            this.h = getH(status);
            this.f = this.g + this.h;
        }

        // 计算启发函数
        public int getH(String status) {
            int ret = 0;
            for (int i = 0; i < 6; ++i) {
                if (status.charAt(i) != '0') {
                    ret += dist[i][status.charAt(i) - '1'];
                }
            }
            return ret;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
