/**
 * <p><strong>n&nbsp;皇后问题</strong> 研究的是如何将 <code>n</code>&nbsp;个皇后放置在 <code>n×n</code> 的棋盘上，并且使皇后彼此之间不能相互攻击。</p>
 *
 * <p>给你一个整数 <code>n</code> ，返回所有不同的&nbsp;<strong>n<em>&nbsp;</em>皇后问题</strong> 的解决方案。</p>
 *
 * <div class="original__bRMd">
 * <div>
 * <p>每一种解法包含一个不同的&nbsp;<strong>n 皇后问题</strong> 的棋子放置方案，该方案中 <code>'Q'</code> 和 <code>'.'</code> 分别代表了皇后和空位。</p>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>示例 1：</strong></p>
 * <img alt="" src="https://assets.leetcode.com/uploads/2020/11/13/queens.jpg" style="width: 600px; height: 268px;" />
 * <pre>
 * <strong>输入：</strong>n = 4
 * <strong>输出：</strong>[[".Q..","...Q","Q...","..Q."],["..Q.","Q...","...Q",".Q.."]]
 * <strong>解释：</strong>如上图所示，4 皇后问题存在两个不同的解法。
 * </pre>
 *
 * <p><strong>示例 2：</strong></p>
 *
 * <pre>
 * <strong>输入：</strong>n = 1
 * <strong>输出：</strong>[["Q"]]
 * </pre>
 *
 * <p>&nbsp;</p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>1 &lt;= n &lt;= 9</code></li>
 * </ul>
 * </div>
 * </div>
 * <div><div>Related Topics</div><div><li>数组</li><li>回溯</li></div></div><br><div><li>👍 1117</li><li>👎 0</li></div>
 */

package leetcode.editor.cn;


import java.util.*;

// 51 N 皇后（回溯算法）
public class NQueens51 {
    public static void main(String[] args) {
        Solution2 solution = new NQueens51().new Solution2();
        long startTime = System.currentTimeMillis();
        List<List<String>> res = solution.solveNQueens(11);
        long endTime = System.currentTimeMillis();
        double time = endTime - startTime;
        System.out.println(res);
        System.out.println(res.size());
        System.out.println("程序运行时间： " + ((time > 10000) ? time / 1000 + "s" : time + "ms"));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        //结果
        List<List<String>> res;
        //棋盘
        char[][] board;
        //棋盘大小
        int size;

        public List<List<String>> solveNQueens(int n) {
            //初始化数据
            board = generateBoard(n);
            res = new ArrayList<>();
            size = n;
            backTrack(0);
            return res;
        }

        private void backTrack(int row) {
            if (row == size) {
                addResult();
                return;
            }
            for (int col = 0; col < size; col++) {
                if (!isValid(board, row, col)) continue;
                board[row][col] = 'Q';
                backTrack(row + 1);
                board[row][col] = '.';
            }
        }

        private boolean isValid(char[][] board, int row, int col) {
            for (int i = 0; i < size; i++)
                if (board[i][col] == 'Q') return false;
            for (int i = row - 1, j = col + 1; i >= 0 && j < size; i--, j++)
                if (board[i][j] == 'Q') return false;
            for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--)
                if (board[i][j] == 'Q') return false;
            return true;
        }

        //生成棋盘
        private char[][] generateBoard(int n) {
            char[][] board = new char[n][n];
            //踩坑：此处不能直接fill一个char[]进board中，因为fill对于对象只是复制引用
            for (char[] row : board) {
                Arrays.fill(row, '.');
            }
            return board;
        }

        //添加结果
        private void addResult() {
            List<String> result = new ArrayList<>();
            for (char[] row : board) {
                result.add(new String(row));
            }
            res.add(result);
        }
    }

    //使用判断数组，用空间换时间（快10%）
    class Solution2 {
        //结果
        List<List<String>> result;
        //棋盘大小
        int size;
        //棋盘
        char[][] board;
        //列是否存在Q
        boolean[] col;
        //对角线是否存在Q
        /*右上到左下的对角线，行坐标和列坐标的差相等
        dg[0]为左上角board[0][0]的元素，
        dg[1]为board[0][1]和gird[1][0]，...
        下标特征是[col+row]*/
        boolean[] dg;
        //反对角线是否存在Q
        /*左上到右下的对角线，行坐标和列坐标的和相等
        udg[0]为左下角board[size-1][0]的元素，
        ud1[1]为board[size-2][0]和gird[size-1][1]，...
        下标特征是[col+row-size+1]*/
        boolean[] udg;

        public List<List<String>> solveNQueens(int n) {
            //初始化
            size = n;
            board = new char[n][n];
            col = new boolean[n];
            dg = new boolean[2 * n - 1];
            udg = new boolean[2 * n - 1];
            result = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    board[i][j] = '.';
                }
            }
            //开始递归
            trackBack(0);
            return result;
        }

        private void trackBack(int row) {
            //满足结束条件
            if (row == size) {
                //提交路径（结果）
                addResult();
            }
            //遍历子节点列表
            //当前在第row行，从第0列到n-1列进行循环
            for (int col = 0; col < size; col++) {
                //排除不符合规则的子节点
                if (this.col[col] || dg[col + row] || udg[col + size - 1 - row]) {
                    continue;
                }
                //选择子节点
                board[row][col] = 'Q';
                //当前列和对角线设置为ture，即已经用过
                this.col[col] = dg[col + row] = udg[col - row + size - 1] = true;
                //递归进下一行
                trackBack(row + 1);
                //撤销选择
                board[row][col] = '.';
                this.col[col] = dg[col + row] = udg[col - row + size - 1] = false;
            }
        }

        //添加结果
        private void addResult() {
            List<String> result = new ArrayList<>();
            for (char[] row : board) {
                result.add(new String(row));
            }
            this.result.add(result);
        }
    }

    //使用哈希表
    class Solution3 {

        private Set<Integer> col;
        private Set<Integer> main;
        private Set<Integer> sub;
        private int n;
        private List<List<String>> res;

        public List<List<String>> solveNQueens(int n) {
            this.n = n;
            res = new ArrayList<>();
            if (n == 0) {
                return res;
            }

            col = new HashSet<>();
            main = new HashSet<>();
            sub = new HashSet<>();

            Deque<Integer> path = new ArrayDeque<>();
            dfs(0, path);
            return res;
        }

        private void dfs(int row, Deque<Integer> path) {
            if (row == n) {
                List<String> board = convert2board(path);
                res.add(board);
                return;
            }

            // 针对每一列，尝试是否可以放置
            for (int i = 0; i < n; i++) {
                if (!col.contains(i) && !main.contains(row - i) && !sub.contains(row + i)) {
                    path.addLast(i);
                    col.add(i);
                    main.add(row - i);
                    sub.add(row + i);

                    dfs(row + 1, path);

                    sub.remove(row + i);
                    main.remove(row - i);
                    col.remove(i);
                    path.removeLast();
                }
            }
        }

        private List<String> convert2board(Deque<Integer> path) {
            List<String> board = new ArrayList<>();
            for (Integer num : path) {
                StringBuilder row = new StringBuilder();
                row.append(".".repeat(Math.max(0, n)));
                row.replace(num, num + 1, "Q");
                board.add(row.toString());
            }
            return board;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}
