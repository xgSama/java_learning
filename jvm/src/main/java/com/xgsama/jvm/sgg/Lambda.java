package com.xgsama.jvm.sgg;

/**
 * 体会invokedynamic指令
 *
 * @author xgSama
 * @date 2021/4/25 14:43
 */
@FunctionalInterface
interface Func {
    boolean func(String str);
}

public class Lambda {
    public void lambda(Func func) {
        return;
    }

    public static void main(String[] args) {
        Lambda lambda = new Lambda();

        Func func = s -> {
            return true;
        };

        lambda.lambda(func);

        lambda.lambda(s -> {
            return true;
        });

    }
}
