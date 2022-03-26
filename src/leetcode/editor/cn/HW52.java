package leetcode.editor.cn;

import java.util.Scanner;

public class HW52 {

    public static void main(String[] args) {
        String st1;
        String st2;
        Scanner sc = new Scanner(System.in);
        st1 = sc.nextLine();
        st2 = sc.nextLine();
        int[][] dp = new int[st1.length() + 1][st2.length() + 1];
        for (int i = 1; i <= st1.length(); i++) {
            dp[i][0] = i;
        }
        for (int i = 1; i <= st2.length(); i++) {
            dp[0][i] = i;
        }
        for (int i = 1; i <= st1.length() ; i++) {
            for (int j = 1; j <= st2.length(); j++) {
                if (st1.charAt(i-1) == st2.charAt(j-1))
                    dp[i][j] = dp[i - 1][j - 1];
                else
                    dp[i][j] = min(
                            dp[i-1][j]+1,
                            dp[i][j-1]+1,
                            dp[i-1][j-1]+1
                    );
            }
        }
        System.out.println(dp[st1.length()][st2.length()]);
    }

    private static int min(int a, int b, int c) {
        return Math.min(a, Math.min(b, c));
    }
}
