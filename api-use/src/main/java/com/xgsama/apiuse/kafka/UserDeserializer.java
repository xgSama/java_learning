package com.xgsama.apiuse.kafka;

import com.xgsama.apiuse.entity.User;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Deserializer;

import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

/**
 * UserDeserializer
 *
 * @author : xgSama
 * @date : 2021/11/8 11:44:56
 */
public class UserDeserializer implements Deserializer<User> {
    @Override
    public User deserialize(String topic, byte[] data) {
        if (data == null) {
            return null;
        }
        if (data.length < 8) {
            throw new RuntimeException("Size of ");
        }

        int nameLength, ageLength, genderLength, age;
        String name, gender;
        ByteBuffer buffer = ByteBuffer.wrap(data);

        nameLength = buffer.getInt();
        byte[] nameBytes = new byte[nameLength];
        buffer.get(nameBytes);

        ageLength = buffer.getInt();
        byte[] ageBytes = new byte[ageLength];
        buffer.get(ageBytes);

        genderLength = buffer.getInt();
        byte[] genderBytes = new byte[genderLength];
        buffer.get(genderBytes);

        try {
            name = new String(nameBytes, StandardCharsets.UTF_8);
            gender = new String(genderBytes, StandardCharsets.UTF_8);
            age = Integer.parseInt(new String(ageBytes, StandardCharsets.UTF_8));
        } catch (Exception e) {
            throw new SerializationException("error");
        }


        return new User(name, age, gender);
    }
}
