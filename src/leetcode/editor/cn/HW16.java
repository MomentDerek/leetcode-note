package leetcode.editor.cn;

import java.util.*;

public class HW16 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] temp = sc.nextLine().split(" ");
        int N = Integer.parseInt(temp[0])/10;
        int m = Integer.parseInt(temp[1]);
        Good[] goods = new Good[m];
        List<Good> mainGoods = new ArrayList<>();
        Map<Integer, Integer[]> sonCache = new HashMap<>();
        for (int i = 0; i < m; i++) {
            Good good = new Good(i,sc.nextInt()/10, sc.nextInt() );
            goods[i] = good;
            int parentId = sc.nextInt();
            if (parentId != 0) {
                if (sonCache.containsKey(parentId)) {
                    Integer[] sonArr = sonCache.get(parentId);
                    sonArr[1] = i;
                    sonCache.put(parentId, sonArr);
                } else {
                    sonCache.put(parentId, new Integer[]{i});
                }
            } else {
                mainGoods.add(good);
            }
        }
        int mainSize = mainGoods.size();
        int[][] dpTable = new int[N + 1][mainSize+1];
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= mainSize; j++) {
                
            }
        }
    }
    static class Good{
        public int id;
        public int price;
        public int importance;

        public Good(int id,int price, int importance) {
            this.id = id;
            this.price = price;
            this.importance = importance;
        }
    }
}
