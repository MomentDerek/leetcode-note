package leetcode.editor.cn.util;

/**
 * @author BookShu(构建原作者),m0_45291994(构建改造者),Blankj(打印函数作者)
 */
public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;

    TreeNode(int x) {
        val = x;
    }

    public String toString() {
        return Integer.toString(val);
    }

    /**
     * 竖向打印二叉树
     *
     * @param root 二叉树根节点
     */
    public static void print(TreeNode root) {
        print(root, 0);
    }

    //测试代码：String str = "[3,9,20,null,null,15,7]";
    //输出：int []arr = {3, 9, 20, Integer.MAX_VALUE, Integer.MAX_VALUE, 15, 7};
    private static int[] StrToIntArray(String str) {
        str = str.substring(1, str.length() - 1);
        String[] valueChars = str.split(",");
        int[] arr = new int[valueChars.length];
        try {
            for (int i = 0; i < arr.length; i++) {
                if (valueChars[i].equals("null")) {
                    arr[i] = Integer.MAX_VALUE;
                } else {
                    arr[i] = Integer.parseInt(valueChars[i]);
                }
            }
        } catch (NumberFormatException e) {
            System.out.println("StrToIntArray Fail：字符转换为Integer失败，请检查输入字符");
        }
        return arr;
    }

    //测试输入：String str = "[3,9,20,null,null,15,7]";
    public static TreeNode mkTree(String str) {
        int[] arr = StrToIntArray(str);
        //先将输入的Integer数组转化为TreeNode数组
        TreeNode[] nodes = new TreeNode[arr.length + 1];
        for (int i = 1; i < nodes.length; i++) {
            if (arr[i - 1] != Integer.MAX_VALUE) {
                nodes[i] = new TreeNode(arr[i - 1]);
            } else {
                nodes[i] = null;
            }
        }
        //临时root
        TreeNode node;
        //注意，这里如果是小于等于，则表示要遍历非完美树的最后一层，需要对node数组进行下标判断的操作
        for (int i = 1; i <= nodes.length / 2; i++) {
            node = nodes[i];
            if (node == null) continue;
            node.left = 2 * i < nodes.length ? nodes[2 * i] : null;
            node.right = 2 * i + 1 < nodes.length ? nodes[2 * i + 1] : null;
        }
        return nodes[1];
    }

    private static final String space = "        ";

    private static void print(TreeNode node, int deep) {
        if (node == null) {
            printSpace(deep);
            System.out.println("#");
            return;
        }
        print(node.right, deep + 1);
        printSpace(deep);
        printNode(node.val);
        print(node.left, deep + 1);
    }

    private static void printSpace(int count) {
        for (int i = 0; i < count; i++) {
            System.out.print(space);
        }
    }

    private static void printNode(int val) {
        StringBuilder res = new StringBuilder(val + " <");
        int spaceNum = space.length() - res.length();
        res.append(" ".repeat(Math.max(0, spaceNum)));
        System.out.println(res);
    }
}