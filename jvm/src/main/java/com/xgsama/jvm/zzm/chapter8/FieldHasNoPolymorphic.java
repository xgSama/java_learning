package com.xgsama.jvm.zzm.chapter8;

/**
 * 字段不参与多态
 *
 * @author xgSama
 * @date 2021/4/25 16:18
 */
public class FieldHasNoPolymorphic {

    static class Father {
        public int money = 1;

        public Father() {
            money = 2;
            showMeTheMoney();
        }

        public void showMeTheMoney() {
            System.out.println("I am Father, i have $" + money);
        }

        public static void getName() {
            System.out.println("Father");
        }
    }

    static class Son extends Father {
        public int money = 3;

        public Son() {
            money = 4;
            showMeTheMoney();
        }

        @Override
        public void showMeTheMoney() {
            System.out.println("I am Son, i have $" + money);
        }

        public static void getName() {
            System.out.println("Son");
        }
    }

    public static void main(String[] args) {
        // 先声明一个Father 类的对象gay ，然后用Son类对 gay 进行实例化”
        // 即引用类型为Person类，实际代表的是Man类。因此，访问的是Father的属性及静态方法
        Father gay = new Son();
        System.out.println("This gay has $" + gay.money);
        gay.showMeTheMoney();
        System.out.println(gay.getClass());
        gay.getName();

    }
}
