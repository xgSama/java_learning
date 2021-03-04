package com.xgsama.algorithm.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * Backtracking
 *
 * @author xgSama
 * @date 2020/12/17 10:05
 */
public class Backtracking {

    public static void main(String[] args) {
        System.out.println(combinationSum3(3, 9));
    }

    public static List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> res = new ArrayList<>();
        dfs(res, new ArrayList<Integer>(), k, 1, n);
        return res;
    }

    /**
     * @param res   最终结果list
     * @param list  当前dfs寻找的一组数
     * @param k     k个数
     * @param start 起始数
     * @param n     和
     */
    private static void dfs(List<List<Integer>> res, List<Integer> list, int k, int start, int n) {
        // 终止条件，如果满足这个条件，再往下找也没什么意义了
        if (list.size() == k || n <= 0) {
            // 如果找到一组合适的就把他加入到集合list中
            if (list.size() == k && n == 0)
                res.add(new ArrayList<>(list));
            return;
        }
        //通过循环，分别遍历9个子树
        for (int i = start; i <= 9; i++) {
            // 添加
            list.add(i);
            // 递归
            dfs(res, list, k, i + 1, n - i);
            // 撤销操作
            list.remove(list.size() - 1);
        }
    }
}