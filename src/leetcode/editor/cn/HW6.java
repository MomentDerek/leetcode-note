package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HW6 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();
        List<Integer> result = new ArrayList<>();
        while (num>1){
            int pre = num;
            for (int i = 2; i < Math.sqrt(num); i++) {
                if (num % i == 0) {
                    result.add(i);
                    num = num/i;
                    break;
                }
            }
            if (num == pre) break;
        }
        for (Integer integer : result) {
            System.out.print(integer+" ");
        }
        System.out.println(num);
    }
}
