package com.xgsama.apiuse.kafka;

import com.xgsama.apiuse.entity.User;
import org.apache.kafka.common.serialization.Serializer;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * UserSerializer
 *
 * @author : xgSama
 * @date : 2021/11/1 23:10:24
 */
public class UserSerializer implements Serializer<User> {

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
        Serializer.super.configure(configs, isKey);
    }

    @Override
    public byte[] serialize(String topic, User data) {
        if (data == null) {
            return null;
        }

        byte[] name, age, gender;
        if (data.getName() != null) {
            name = data.getName().getBytes(StandardCharsets.UTF_8);
        } else {
            return new byte[0];
        }
        if (data.getAge() != null) {
            age = String.valueOf(data.getAge()).getBytes(StandardCharsets.UTF_8);
        } else {
            return new byte[0];
        }
        if (data.getGender() != null) {
            gender = data.getGender().getBytes(StandardCharsets.UTF_8);
        } else {
            return new byte[0];
        }

        ByteBuffer buffer = ByteBuffer.allocate(4 + 4 + 4 + name.length + age.length + gender.length);
        buffer.putInt(name.length);
        buffer.put(name);
        buffer.putInt(age.length);
        buffer.put(age);
        buffer.putInt(gender.length);
        buffer.put(gender);

        return buffer.array();

    }

    @Override
    public void close() {
        Serializer.super.close();
    }
}
