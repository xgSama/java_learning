package com.xgsama.java.basis.reflect;

import org.junit.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * ReflectionTest
 *
 * @author xgSama
 * @date 2020/11/22 13:57
 */
public class ReflectionTest {
    @Test
    public void test1() {
        Person person1 = new Person("Tom", 12);
        System.out.println(person1.toString());
        person1.show();
    }

    @Test
    public void test2() throws Exception {
        Class<Person> clazz = Person.class;

        // 通过反射操作对象指定的属性，方法
        Constructor<Person> cons = clazz.getConstructor(String.class, int.class);
        Person person = cons.newInstance("Jack", 88);
        System.out.println(person.toString());

        Field age = clazz.getDeclaredField("age");
        age.set(person, 28);
        System.out.println(person.toString());

        Method show = clazz.getMethod("show");
        show.invoke(person);
    }

    @Test
    public void test3() throws Exception {
        // 调用对象私有的属性、方法
        Class<Person> clazz = Person.class;

        Constructor<Person> cons = clazz.getDeclaredConstructor(String.class);
        cons.setAccessible(true);
        Person person = cons.newInstance("private");
        System.out.println(person.toString());

        Field name = clazz.getDeclaredField("name");
        name.setAccessible(true);
        name.set(person, "reflect");
        System.out.println(person.toString());

        Method showNation = clazz.getDeclaredMethod("showNation", String.class);
        showNation.setAccessible(true);
        String china = (String) showNation.invoke(person, "china");
        System.out.println(china);
    }

    // 获取Class实例的方式
    @Test
    public void test4() throws ClassNotFoundException {
        // 1. 调用运行时类的属性：.class
        Class<Person> cls1 = Person.class;
        System.out.println(cls1);

        // 2. 通过运行时类的对象，调用getClass()
        Person p = new Person();
        Class cls2 = p.getClass();
        System.out.println(cls2);

        // 3. 调用Class的静态方法forName(String className)
        Class cls3 = Class.forName("com.xgsama.java.basis.reflect.Person");
        System.out.println(cls3);

        // 4. 使用类的加载器
        ClassLoader classLoader = ReflectionTest.class.getClassLoader();
        Class<?> cls4 = classLoader.loadClass("com.xgsama.java.basis.reflect.Person");
        System.out.println(cls4);

        System.out.println(cls1 == cls2);

    }

    @Test
    public void test5() throws IllegalAccessException, InstantiationException {
        Class<Person> cls = Person.class;

        Method[] methods = cls.getDeclaredMethods();
        for (Method method : methods) {
            System.out.println("========================================");

            // 方法权限修饰符
            System.out.print(Modifier.toString(method.getModifiers()) + "\t");
            // 方法返回值类型
            System.out.print(method.getReturnType() + "\t");
            // 方法名称
            System.out.print(method.getName() + "\t");
            // 形参列表
            Class<?>[] parameterTypes = method.getParameterTypes();
            if (parameterTypes != null && parameterTypes.length != 0) {
                for (Class<?> parameterType : parameterTypes) {
                    System.out.print(parameterType.getName());
                }
            }

            System.out.println();
            Annotation[] annotations = method.getAnnotations();

            for (Annotation annotation : annotations) {
                System.out.println(annotation.annotationType());
                System.out.println(annotation.toString());
            }

            System.out.println("========================================");
        }
    }
}
