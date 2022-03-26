package leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HW28 {
    static List<Integer> jiShu;
    static List<Integer> ouShu;
    static int[] ouShuMatch;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            int n = sc.nextInt();
            int[] input = new int[n];
            for (int i = 0; i < n; i++) {
                input[i] = sc.nextInt();
            }
            ouShu = new ArrayList<>();
            jiShu = new ArrayList<>();
            for (int i : input) {
                if (i % 2 == 0) ouShu.add(i);
                else jiShu.add(i);
            }
            ouShuMatch = new int[ouShu.size()];
            int result = 0;
            for (int i:jiShu) {
                boolean[] ouShuVisited = new boolean[ouShu.size()];
                if (tryMatch(ouShuVisited, i)) {
                    result++;
                }
            }
            System.out.println(result);
        }
    }

    private static boolean tryMatch(boolean[] ouShuVisited, int x) {
        for (int i = 0; i < ouShu.size(); i++) {
            if (isSuShu(ouShu.get(i) + x) && !ouShuVisited[i]) {
                ouShuVisited[i] = true;
                if (ouShuMatch[i] == 0 || tryMatch(ouShuVisited, ouShuMatch[i])) {
                    ouShuMatch[i] = x;
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean isSuShu(int number) {
        for (int i = 2; i <= Math.sqrt(number); i++) {
            if (number % i == 0) return false;
        }
        return true;
    }
}

class HW28Ans {

    //这里包含了判断素数的方法
    //小技巧！！！素数不是偶数，那么和是素数的话就是奇数+偶数
    //那么可以分成两堆,一堆偶数，一堆奇数
    //匈牙利算法，先到先得 能让就让
    //有机会上，没机会创造机会也要上
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Scanner scan = new Scanner(System.in);
        while (scan.hasNext()) {
            int n = scan.nextInt();
            int[] tempArray = new int[n];
            for (int i = 0; i < n; i++) {
                tempArray[i] = scan.nextInt();
            }
            //偶数
            ArrayList<Integer> evens = new ArrayList<Integer>();
            //奇数
            ArrayList<Integer> odds = new ArrayList<Integer>();
            for (int i = 0; i < n; i++) {
                if ((tempArray[i] & 1) != 1) {
                    evens.add(tempArray[i]);
                } else {
                    odds.add(tempArray[i]);
                }
            }
            //下面开始才是重头戏
            //用于标记那个奇数匹配了偶数,直接记录奇数的值，而不是奇数在奇数数组中的下标
            int[] evensMatch = new int[evens.size()];
            int result = 0;
            //遍历奇数去匹配偶数
            for (int i = 0; i < odds.size(); i++) {
                //每一步重新创建，也就是相当于清空
                //used数组用于标记某个某数位置是否
                int[] used = new int[evens.size()];
                //这里采用了匈牙利算法，先到先得
                if (find(odds.get(i), evens, used, evensMatch)) {
                    result++;
                }
            }
            System.out.println(result);
        }
    }

    public static boolean isPrime(int num) {
        for (int i = 2; i * i <= num; i++) {
            if (num % i == 0) {
                return false;
            }
            if (num == 1) {
                return false;
            }
        }
        return true;
    }

    /**
     * @param x          当前的奇数
     * @param evens      偶数列表
     * @param used       已经使用过的偶数
     * @param evensMatch 偶数被匹配的情况（元素是奇数）
     */
    public static boolean find(int x, ArrayList<Integer> evens, int[] used, int[] evensMatch) {
        //遍历偶数
        //去检查当前传入的奇数能否与偶数哪些数匹配
        for (int i = 0; i < evens.size(); i++) {
            //如果当前偶数与传入的奇数匹配，并且当前偶数位还没有匹配过奇数
            if (isPrime(x + evens.get(i)) && used[i] == 0) {
                //设置当前偶数位匹配为true，也就是 1
                used[i] = 1;
                //如果第i个偶数没有伴侣
                //或者第i个偶数原来有伴侣，并且该伴侣能够重新找到伴侣的话(这里有递归调用)
                //则奇数x可以设置为第i个偶数的伴侣
                //这里采用了匈牙利算法，能让则让
                if (evensMatch[i] == 0 || find(evensMatch[i], evens, used, evensMatch)) {
                    evensMatch[i] = x;
                    return true;
                }
            }
        }
        //遍历完偶数都没有可以与传入奇数做伴侣的，该奇数只能孤独终老了
        return false;
    }

}
