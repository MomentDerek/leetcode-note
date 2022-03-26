package leetcode.editor.cn;

import java.util.Locale;
import java.util.Scanner;

public class HW2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        char[] s = sc.nextLine().toLowerCase(Locale.ROOT).toCharArray();
        char resultChar = sc.next().toLowerCase(Locale.ROOT).charAt(0);
        int result = 0;
        for (int i = 0; i < s.length; i++) {
            if (s[i] == resultChar) result++;
        }
        System.out.println(result);
    }
}
