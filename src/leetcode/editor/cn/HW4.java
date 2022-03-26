package leetcode.editor.cn;

import java.util.Scanner;

public class HW4 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input = sc.next();
        int resultCount = input.length() / 8;
        for (int i = 0; i < resultCount; i++) {
            System.out.println(input.substring(i * 8, (i + 1) * 8));
        }
        StringBuilder sb = new StringBuilder();
        if (input.length() % 8 != 0) {
            for (int i = 0; i < ((resultCount + 1) * 8 - input.length()); i++) {
                sb.append("0");
            }
        }
        System.out.println(input.substring(resultCount * 8) + sb);
    }
}
