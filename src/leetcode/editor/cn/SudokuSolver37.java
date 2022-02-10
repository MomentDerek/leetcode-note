/**
 * <p>编写一个程序，通过填充空格来解决数独问题。</p>
 *
 * <p>数独的解法需<strong> 遵循如下规则</strong>：</p>
 *
 * <ol>
 * <li>数字 <code>1-9</code> 在每一行只能出现一次。</li>
 * <li>数字 <code>1-9</code> 在每一列只能出现一次。</li>
 * <li>数字 <code>1-9</code> 在每一个以粗实线分隔的 <code>3x3</code> 宫内只能出现一次。（请参考示例图）</li>
 * </ol>
 *
 * <p>数独部分空格内已填入了数字，空白格用 <code>'.'</code> 表示。</p>
 *
 * <p> </p>
 *
 * <div class="top-view__1vxA">
 * <div class="original__bRMd">
 * <div>
 * <p><strong>示例：</strong></p>
 * <img src="https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2021/04/12/250px-sudoku-by-l2g-20050714svg.png" style="height:250px; width:250px" />
 * <pre>
 * <strong>输入：</strong>board = [["5","3",".",".","7",".",".",".","."],["6",".",".","1","9","5",".",".","."],[".","9","8",".",".",".",".","6","."],["8",".",".",".","6",".",".",".","3"],["4",".",".","8",".","3",".",".","1"],["7",".",".",".","2",".",".",".","6"],[".","6",".",".",".",".","2","8","."],[".",".",".","4","1","9",".",".","5"],[".",".",".",".","8",".",".","7","9"]]
 * <strong>输出：</strong>[["5","3","4","6","7","8","9","1","2"],["6","7","2","1","9","5","3","4","8"],["1","9","8","3","4","2","5","6","7"],["8","5","9","7","6","1","4","2","3"],["4","2","6","8","5","3","7","9","1"],["7","1","3","9","2","4","8","5","6"],["9","6","1","5","3","7","2","8","4"],["2","8","7","4","1","9","6","3","5"],["3","4","5","2","8","6","1","7","9"]]
 * <strong>解释：</strong>输入的数独如上图所示，唯一有效的解决方案如下所示：
 *
 * <img src=" https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2021/04/12/250px-sudoku-by-l2g-20050714_solutionsvg.png" style="height:250px; width:250px" />
 * </pre>
 *
 * <p> </p>
 *
 * <p><strong>提示：</strong></p>
 *
 * <ul>
 * <li><code>board.length == 9</code></li>
 * <li><code>board[i].length == 9</code></li>
 * <li><code>board[i][j]</code> 是一位数字或者 <code>'.'</code></li>
 * <li>题目数据 <strong>保证</strong> 输入数独仅有一个解</li>
 * </ul>
 * </div>
 * </div>
 * </div>
 * <div><div>Related Topics</div><div><li>数组</li><li>回溯</li><li>矩阵</li></div></div><br><div><li>👍 1109</li><li>👎 0</li></div>
 */

package leetcode.editor.cn;

// 37 解数独
public class SudokuSolver37 {
    public static void main(String[] args) {
        Solution solution = new SudokuSolver37().new Solution();
        long startTime = System.currentTimeMillis();
        long endTime = System.currentTimeMillis();
        double time = endTime - startTime;
        System.out.println("程序运行时间： " + ((time > 10000) ? time / 1000 + "s" : time + "ms"));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        char[][] board;

        public void solveSudoku(char[][] board) {
            this.board = board;
            backtrack(0, 0);
        }

        private boolean backtrack(int i, int j) {
            int m = 9, n = 9;
            if (j == n) {
                return backtrack(i + 1, 0);
            }
            if (i == m) return true;

            if (board[i][j] != '.') {
                return backtrack(i, j + 1);
            }

            for (char k = '1'; k <= '9'; k++) {
                if (!isValid(i, j, k)) continue;
                board[i][j] = k;
                if (backtrack(i, j + 1)) return true;
                board[i][j] = '.';
            }
            return false;
        }

        private boolean isValid(int i, int j, char c) {
            for (int k = 0; k < 9; k++) {
                if (board[i][k] == c) return false;
                if (board[k][j] == c) return false;
                if (board[(i / 3) * 3 + k / 3][(j / 3) * 3 + k % 3] == c) return false;
            }
            return true;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
