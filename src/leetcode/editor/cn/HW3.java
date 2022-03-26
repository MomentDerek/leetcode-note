package leetcode.editor.cn;

import java.util.Comparator;
import java.util.Scanner;
import java.util.SortedSet;
import java.util.TreeSet;

public class HW3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        SortedSet<Integer> cache = new TreeSet<>(Comparator.comparingInt(o -> o));
        int account = sc.nextInt();
        for (int i = 0; i < account; i++) {
            cache.add(sc.nextInt());
        }
        for (Integer integer : cache) {
            System.out.println(integer);
        }
    }
}
