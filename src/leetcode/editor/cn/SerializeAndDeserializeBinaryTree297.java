/**
 * 序列化是将一个数据结构或者对象转换为连续的比特位的操作，进而可以将转换后的数据存储在一个文件或者内存中，同时也可以通过网络传输到另一个计算机环境，采取相反方式重
 * 构得到原数据。
 * <p>
 * 请设计一个算法来实现二叉树的序列化与反序列化。这里不限定你的序列 / 反序列化算法执行逻辑，你只需要保证一个二叉树可以被序列化为一个字符串并且将这个字符串反序
 * 列化为原始的树结构。
 * <p>
 * 提示: 输入输出格式与 LeetCode 目前使用的方式一致，详情请参阅 LeetCode 序列化二叉树的格式。你并非必须采取这种方式，你也可以采用其他的方法
 * 解决这个问题。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：root = [1,2,3,null,null,4,5]
 * 输出：[1,2,3,null,null,4,5]
 * <p>
 * <p>
 * 示例 2：
 * <p>
 * <p>
 * 输入：root = []
 * 输出：[]
 * <p>
 * <p>
 * 示例 3：
 * <p>
 * <p>
 * 输入：root = [1]
 * 输出：[1]
 * <p>
 * <p>
 * 示例 4：
 * <p>
 * <p>
 * 输入：root = [1,2]
 * 输出：[1,2]
 * <p>
 * <p>
 * <p>
 * <p>
 * 提示：
 * <p>
 * <p>
 * 树中结点数在范围 [0, 10⁴] 内
 * -1000 <= Node.val <= 1000
 * <p>
 * Related Topics 树 深度优先搜索 广度优先搜索 设计 字符串 二叉树 👍 745 👎 0
 */

package leetcode.editor.cn;

import leetcode.editor.cn.util.TreeNode;
import leetcode.editor.cn.util.TreeUtils;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

// 297 二叉树的序列化与反序列化
public class SerializeAndDeserializeBinaryTree297 {
    public static void main(String[] args) {
        Codec solution = new SerializeAndDeserializeBinaryTree297().new Codec();
        long startTime = System.currentTimeMillis();
        TreeNode input = TreeUtils.mkTree("[]");
        System.out.println(input);
        System.out.println(solution.serialize(input));
        System.out.println(solution.deserialize(solution.serialize(input)));
        long endTime = System.currentTimeMillis();
        System.out.println("程序运行时间： " + (endTime - startTime) + "ms");
    }

    /**
     *
     */
    //leetcode submit region begin(Prohibit modification and deletion)
    public class Codec {
        private final String CUT = ",";
        private final String NULL = "null";

        public String serialize(TreeNode root) {
            StringBuilder sb = new StringBuilder();
            //serializeFront(root, sb);
            //serializeBack(root, sb);
            serializeLevel(root, sb);
            if (sb.length() != 0)sb.deleteCharAt(sb.length() - 1);
            return "[" + sb.toString() + "]";
        }

        public TreeNode deserialize(String data) {
            //if (data.equals("[]")) return null;
            String[] strArr = data.substring(1, data.length() - 1).split(CUT);
            LinkedList<String> strList = new LinkedList<>(List.of(strArr));
            //return deserializeFront(strList);
            //return deserializeBack(strList);
            return deserializeLevel(strList);
        }

        /**
         * 前序遍历序列化
         */
        private void serializeFront(TreeNode root, StringBuilder sb) {
            if (root == null) {
                sb.append(NULL).append(CUT);
                return;
            }
            sb.append(root.val).append(CUT);
            serializeFront(root.left, sb);
            serializeFront(root.right, sb);
        }

        /**
         * 前序遍历反序列化
         */
        private TreeNode deserializeFront(LinkedList<String> strList) {
            //序列化结束
            if (strList.isEmpty()) return null;
            //拉取第一个节点（当前节点），如果为null则返回（没有左右节点）
            String first = strList.poll();
            if (first.equals(NULL)) return null;
            //如果有，则递归生成当前节点的左右子树
            TreeNode root = new TreeNode(Integer.parseInt(first));
            root.left = deserializeFront(strList);
            root.right = deserializeFront(strList);

            return root;
        }

        /**
         * 后序遍历序列化
         */
        private void serializeBack(TreeNode root, StringBuilder sb) {
            if (root == null) {
                sb.append(NULL).append(CUT);
                return;
            }
            serializeBack(root.left, sb);
            serializeBack(root.right, sb);
            sb.append(root.val).append(CUT);
        }

        /**
         * 后序遍历反序列化
         */
        private TreeNode deserializeBack(LinkedList<String> strArr) {
            if (strArr.isEmpty()) return null;
            String last = strArr.removeLast();
            if (last.equals(NULL)) return null;
            TreeNode root = new TreeNode(Integer.parseInt(last));
            root.right = deserializeBack(strArr);
            root.left = deserializeBack(strArr);
            return root;
        }

        /**
         * 层序遍历序列化
         */
        private void serializeLevel(TreeNode root, StringBuilder sb) {
            if (root == null) {
                sb.append(NULL).append(CUT);
                return;
            }
            Queue<TreeNode> queue = new LinkedList<>();
            queue.offer(root);
            while (!queue.isEmpty()) {
                TreeNode now = queue.poll();
                if (now == null) {
                    sb.append(NULL).append(CUT);
                    continue;
                }
                sb.append(now.val).append(CUT);
                queue.offer(now.left);
                queue.offer(now.right);
            }
        }

        /**
         * 层序遍历反序列化
         */
        private TreeNode deserializeLevel(LinkedList<String> strArr) {
            if (strArr.isEmpty()) return null;
            String rootStr = strArr.get(0);
            if (rootStr.equals(NULL)) return null;
            TreeNode root = new TreeNode(Integer.parseInt(rootStr));
            Queue<TreeNode> queue = new LinkedList<>();
            queue.offer(root);
            for (int i = 1; i < strArr.size(); i++) {
                TreeNode now = queue.poll();
                String left = strArr.get(i);
                if (!left.equals(NULL)) {
                    TreeNode leftNode = new TreeNode(Integer.parseInt(left));
                    now.left = leftNode;
                    queue.offer(leftNode);
                }
                i++;
                String right = strArr.get(i);
                if (!right.equals(NULL)) {
                    TreeNode rightNode = new TreeNode(Integer.parseInt(right));
                    now.right = rightNode;
                    queue.offer(rightNode);
                }
            }
            return root;
        }
    }

//leetcode submit region end(Prohibit modification and deletion)

}
