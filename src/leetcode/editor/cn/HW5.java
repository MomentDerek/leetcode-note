package leetcode.editor.cn;

import java.util.Scanner;

public class HW5 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String numString = sc.next();
        numString = numString.substring(2);
        int result = 0;
        for (int i = numString.length()-1; i >= 0; i--) {
            char nowChar = numString.charAt(i);
            double pow = Math.pow(16, numString.length() - i - 1);
            if (nowChar <= '9') {
                result+=(nowChar-'0')*(pow);
            }
            else result+=(nowChar-'A'+10)*(pow);
        }
        System.out.printf("%d",result);
    }
}
