package leetcode.editor.cn;

import java.util.Scanner;
import java.util.TreeSet;

public class HW44 {
    static int[][] input;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        input = new int[9][9];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                input[i][j] = sc.nextInt();
            }
        }
        if (trySolve(0, 0)) printResult();
        else System.out.println(-1);
    }

    private static boolean trySolve(int row, int col) {
        if (row == 9) return true;
        if (input[row][col] != 0) {
            boolean ok = true;
            for (int i = 0; i < 9; i++) {
                if (!ok) break;
                for (int j = 0; j < 9; j++) {
                    if (input[i][j] == 0) {
                        row = i;
                        col = j;
                        ok = false;
                        break;
                    }
                }
            }
            if (ok) return true;
        }
        Integer[] lastNum = getLastNum(row, col);
        for (Integer num : lastNum) {
            input[row][col] = num;
            boolean result;
            if (col == 8) result = trySolve(row + 1, 0);
            else result = trySolve(row, col + 1);
            if (result) return true;
            input[row][col] = 0;
        }
        return false;
    }

    private static Integer[] getLastNum(int row, int col) {
        TreeSet<Integer> temp = new TreeSet<>();
        for (int i = 1; i <= 9; i++) {
            temp.add(i);
        }
        int rowFirst = row - row % 3;
        int colFirst = col - col % 3;
        for (int i = 0; i < 9; i++) {
            temp.remove(input[i][col]);
            temp.remove(input[row][i]);
            temp.remove(input[rowFirst + i / 3][colFirst + i % 3]);
        }
        return temp.toArray(new Integer[0]);
    }

    private static void printResult() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(input[i][j] + " ");
            }
            System.out.println();
        }
    }
}
