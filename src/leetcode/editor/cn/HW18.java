package leetcode.editor.cn;

import java.util.Arrays;
import java.util.Scanner;

public class HW18 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] result = new int[7];
        int index = 0;
        while (sc.hasNextLine()) {
            String[] input = sc.nextLine().split("~");
            if (input.length != 2) break;
            Integer[] ips;
            Integer[] masks;
            try {
                ips = Arrays.stream(input[0].split("\\.")).map(Integer::parseInt).toArray(Integer[]::new);
                masks = Arrays.stream(input[1].split("\\.")).map(Integer::parseInt).toArray(Integer[]::new);
            } catch (Exception e) {
                result[5]++;
                continue;
            }
            if (ips[0] == 0 || ips[0] == 127) continue;

            //目前是否还全是0
            boolean isZero = true;
            boolean maskIsWrong = false;
            int oneCount = 0;
            for (int i = masks.length - 1; i >= 0; i--) {
                int temp = masks[i];
                int bitCount = 0;
                while (temp != 0) {
                    //如果前面都是0，现在是1
                    if ((temp & 1) == 1 && isZero) isZero = false;
                    //如果前面出现了1，而现在是0
                    if ((temp & 1) == 0 && !isZero) {
                        maskIsWrong = true;
                        break;
                    }
                    //统计1的个数
                    if ((temp & 1) == 1) oneCount++;
                    bitCount++;
                    temp = temp >> 1;
                }
                if (maskIsWrong) break;
                if (bitCount != 8 && !isZero) {
                    maskIsWrong = true;
                    break;
                }
            }
            if (maskIsWrong||isZero||oneCount == 32) {
                result[5]++;
                continue;
            }
            Integer head = ips[0];
            //私有地址
            if (head == 10 ||
                    (head == 172 && ips[1] <= 31 && ips[1] >= 16) ||
                    (head == 192 && ips[1] == 168)) {
                result[6]++;
            }
            if (head >= 1 && head <= 126) {
                result[0]++;
            } else if (head >= 128 && head <= 191) {
                result[1]++;
            } else if (head >= 192 && head <= 223) {
                result[2]++;
            } else if (head >= 224 && head <= 239) {
                result[3]++;
            } else if (head >= 240) {
                result[4]++;
            }
        }
        for (int number : result) {
            System.out.print(number+" ");
        }
    }
}
