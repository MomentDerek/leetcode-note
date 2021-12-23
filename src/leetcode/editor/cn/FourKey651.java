/**
 * 描述
 * 假设你有一个特殊的键盘，键盘上有如下键:
 *
 * 键1: (A): 在屏幕上打印一个'A'。
 * 键2: (Ctrl-A): 选择整个屏幕。
 * 键3: (Ctrl-C): 复制选择到缓冲区。
 * 键4: (Ctrl-V): 在屏幕上已有的内容后面追加打印缓冲区的内容。
 * 现在，你只能按键盘上N次(使用以上四个键)，找出你可以在屏幕上打印的“A”的最大数量
 *
 * 1 <= N <= 50
 * 答案将在32位有符号整数的范围内。
 * 样例
 * 样例 1:
 *
 * 输入: 3
 * 输出: 3
 * 解释: A, A, A
 * 样例 2:
 *
 * 输入: 7
 * 输出: 9
 * 解释: A, A, A, Ctrl A, Ctrl C, Ctrl V, Ctrl V
 */
package leetcode.editor.cn;

// 四键键盘
public class FourKey651 {
    public static void main(String[] args) {
        Solution solution = new FourKey651().new Solution();
        long startTime = System.currentTimeMillis();
        long endTime = System.currentTimeMillis();
        double time = endTime - startTime;
        System.out.println("程序运行时间： " + ((time > 10000) ? time / 1000 + "s" : time + "ms"));
    }

    class Solution {
        public int maxA(int N) {
            if (N == 1) return 1;
            int[] dp = new int[N + 1];
            for (int i = 1; i <= N; i++) {
                //基础是在上一步的基础上打出一个字母
                dp[i] = dp[i - 1] + 1;
                //从j步开始Ctrl-V
                for (int j = 2; j < i; j++) {
                    //剪切板里面有j-2个字符，按了i-j次，再加上原有的那1倍
                    dp[i] = Math.max(dp[i], dp[j - 2] * (i - j + 1));
                }
            }
            return dp[N];
        }
    }

}

