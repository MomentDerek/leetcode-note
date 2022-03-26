package leetcode.editor.cn;

import java.util.Arrays;
import java.util.Scanner;

public class HW98 {

    static int[] goodsCount = new int[6];
    static int[] moneyCount = new int[4];
    final static int[] MONEY = {1, 2, 5, 10};
    final static String[] GOOD = {"A1", "A2", "A3", "A4", "A5", "A6"};
    final static int[] GOOD_MONEY = {2, 3, 4, 5, 8, 6};
    static int userMoney = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        String[] inputs = input.split(";");
        for (String order : inputs) {
            switch (order.charAt(0)) {
                case 'r':
                    init(order);
                    break;
                case 'p':
                    paid(Integer.parseInt(order.split(" ")[1]));
                    break;
                case 'b':
                    buy(order.split(" ")[1]);
                    break;
                case 'c':
                    coinOut();
                    break;
                case 'q':
                    queue(order);
                    break;
            }
        }
    }

    private static void queue(String order) {
        try {
            order = order.split(" ")[1];
        } catch (Exception e) {
            System.out.println("E010:Parameter error");
            return;
        }
        if (order.equals("0")) {
            for (int i = 0; i < GOOD.length; i++) {
                System.out.println(GOOD[i]+" "+GOOD_MONEY[i]+" "+goodsCount[i]);
            }
        }
        else if (order.equals("1")) {
            for (int i = 0; i < MONEY.length; i++) {
                System.out.println(MONEY[i] + " yuan coin number=" + moneyCount[i]);
            }
        }
        else {
            System.out.println("E010:Parameter error");
        }
    }


    private static void init(String order) {
        String[] orders = order.split(" ");
        String[] goods = orders[1].split("-");
        for (int i = 0; i < goods.length; i++) {
            goodsCount[i] += Integer.parseInt(goods[i]);
        }
        String[] moneys = orders[2].split("-");
        for (int i = 0; i < moneys.length; i++) {
            moneyCount[i] += Integer.parseInt(moneys[i]);
        }
        System.out.println("S001:Initialization is successful");
    }

    private static void paid(Integer moneyNum) {
        int moneyIndex = Arrays.binarySearch(MONEY, moneyNum);
        if (moneyIndex <= -1) {
            System.out.println("E002:Denomination error");
            return;
        }
        if ((moneyCount[0] + 2 * moneyCount[1]) < moneyNum) {
            System.out.println("E003:Change is not enough, pay fail");
            return;
        }
        if (isGoodsEmpty()) {
            System.out.println("E005:All the goods sold out");
            return;
        }
        userMoney += moneyNum;
        moneyCount[moneyIndex]++;
        System.out.println("S002:Pay success,balance=" + userMoney);
    }

    private static void buy(String goodName) {
        int goodIndex = Arrays.binarySearch(GOOD, goodName);
        if (goodIndex == -1) {
            System.out.println("E006:Goods does not exist");
            return;
        }
        if (goodsCount[goodIndex] == 0) {
            System.out.println("E007:The goods sold out");
            return;
        }
        if (userMoney < GOOD_MONEY[goodIndex]) {
            System.out.println("E008:Lack of balance");
            return;
        }
        goodsCount[goodIndex]--;
        userMoney -= GOOD_MONEY[goodIndex];
        System.out.println("S003:Buy success,balance=" + userMoney);
    }

    private static void coinOut() {
        if (userMoney == 0) {
            System.out.println("E009:Work failure");
            return;
        }
        int[] outCount = new int[4];
        for (int i = moneyCount.length - 1; i >= 0; i--) {
            while (MONEY[i] <= userMoney && moneyCount[i] > 0) {
                userMoney -= MONEY[i];
                outCount[i]++;
                moneyCount[i]--;
            }
        }
        for (int i = 0; i < MONEY.length; i++) {
            System.out.println(MONEY[i] + " yuan coin number=" + outCount[i]);
        }
    }

    private static boolean isGoodsEmpty() {
        for (int good : goodsCount) {
            if (good != 0) return false;
        }
        return true;
    }
}
