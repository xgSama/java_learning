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
            // 创建一个新的list，和原来的list撇清关系，对当前list的修改并不会影响到之前的list
            list.add(i);
            // 递归
            dfs(res, list, k, i + 1, n - i);
            // 撤销操作
            list.remove(list.size()-1);
        }
    }
}
