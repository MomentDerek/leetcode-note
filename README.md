# 算法小抄代码实现与笔记

加入了不少自己的注解和优化

## 目录

### 1. 核心套路篇

#### 1.2 动态规划解题套路框架

1.2.1 斐波那契数列 -> 斐波那契数FibonacciNumber509

1.2.2 凑零钱问题 -> 零钱兑换CoinChange322

#### 1.3 回溯算法解题套路框架

1.3.1 全排列问题 -> 全排列Permutations46

1.3.2 N皇后问题 -> N皇后NQueen51

#### 1.4 BFS算法套路框架

1.4.2 二叉树的最小高度 -> 二叉树的最小深度MinimumDepthOfBinaryTree111

1.4.3 解开密码锁的最少次数 -> 打开转盘锁OpenTheLock752

#### 1.5 双指针技巧套路框架

1.5.1.1 判断链表的环 -> 环形链表LinkedListCycle141

1.5.1.2 找到链表的环的起点 -> 环形链表IILinkedListCycleIi142

1.5.2.2 两数之和 -> 两数之和II-输入有序数组TwoSumIiInputArrayIsSorted167

#### 1.7 滑动窗口

1.7.1 最小覆盖子串 ->  最小覆盖子串MinimumWindowSubstring76

1.7.2 字符串排列 -> 字符串的排列PermutationInString567

1.7.3 找所有字母异位词 -> 找到字符串中所有字母异位词FindAllAnagramsInAString438

1.7.4 最长无重复子串 -> 无重复字符的最长子串LongestSubstringWithoutRepeatingCharacters3

### 2. 动态规划系列

2.1 最长递增子序列 -> 最长递增子序列LongestIncreasingSubsequence300

> ##### 注意2.1有二分法贪心算法，暂未学习，跳过

2.2 信封嵌套问题（二维递增子序列）-> 俄罗斯套娃信封问题RussianDollEnvelopes354

2.3 最大子数组问题 -> 最大子数组和MaximumSubarray53

> ##### 注意2.3有分治法，暂未学习，参考官方题解

2.5 最长公共子序列 -> 最长公共子序列LongestCommonSubsequence1143

2.6 编辑距离 -> 编辑距离EditDistance72

2.7 最长回文子序列 -> 最长回文子序列LongestPalindromicSubsequence516

> 2.8有状态压缩，看不懂

2.9 以最小插入次数构造回文串 -> 让字符串成为回文串的最少插入次数MinimumInsertionStepsToMakeAStringPalindrome1312

2.10 正则表达式 -> 正则表达式匹配RegularExpressionMatching10

2.11 四键键盘问题（不同的定义产生不同的解法） -> 4键键盘FourKey651

2.12 高楼扔鸡蛋 -> 鸡蛋掉落SuperEggDrop887

2.13 高楼扔鸡蛋进阶 -> 鸡蛋掉落SuperEggDrop887

2.14 戳气球问题

2.15 0-1背包问题

2.16 子集书包问题

2.17 完全书包问题

2.18.1 打家劫舍1

2.18.2 打家劫舍2

2.18.3 打家劫舍3

### 3. 数据结构系列

3.1 手把手教你写LRU缓存淘汰算法 -> LRU 缓存LruCache146

3.2 层层拆解，帮你手写LFU算法 -> LFU 缓存LfuCache460

3.3.1 判断BST的合法性 -> 验证二叉搜索树ValidateBinarySearchTree98

3.3.2 在BST中查找一个数是否存在 -> 二叉搜索树中的搜索SearchInABinarySearchTree700

3.3.3 在BST中插入一个数 -> 二叉搜索树中的插入操作InsertIntoABinarySearchTree701

3.3.4 在BST中删除一个数 -> 删除二叉搜索树中的节点DeleteNodeInABst450

3.4 完全二叉树的节点数为什么那么难算 -> 完全二叉树的节点个数CountCompleteTreeNodes222

3.5 用各种遍历序列化和反序列化二叉树 -> 二叉树的序列化与反序列化SerializeAndDeserializeBinaryTree297