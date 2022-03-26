package leetcode.editor.cn;

import java.util.Scanner;

public class HW7 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        double v = sc.nextDouble();
        if ((v-(int)v)>=0.5) System.out.println((int)v+1);
        else System.out.println(((int)v));
    }
}
