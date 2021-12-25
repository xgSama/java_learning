package com.xgsama.apiuse.kafka.flink;

import com.github.javafaker.Faker;

import java.util.Locale;

/**
 * FakerTest
 *
 * @author : xgSama
 * @date : 2021/11/4 09:58:22
 */
public class FakerTest {
    public static void main(String[] args) {
        Faker faker = new Faker(Locale.CHINA);

        System.out.println(faker.name().name());
        System.out.println(faker.funnyName().name());
        System.out.println(faker.phoneNumber().cellPhone());
    }
}
