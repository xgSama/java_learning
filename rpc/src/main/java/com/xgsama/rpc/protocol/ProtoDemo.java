package com.xgsama.rpc.protocol;

import com.xgsama.rpc.entity.MsgProto;
import lombok.extern.slf4j.Slf4j;

import java.io.*;

/**
 * ProtoDemo
 *
 * @author : xgSama
 * @date : 2021/8/25 23:02:07
 */
@Slf4j
public class ProtoDemo {
    public static MsgProto.Msg buildMsg() {
        MsgProto.Msg.Builder builder = MsgProto.Msg.newBuilder();
        builder.setId(1);
        builder.setContent("梦付千秋星垂野");
        return builder.build();
    }

    public static void main(String[] args) throws IOException {
        serAndDser1();
        serAndDser2();
        serAndDser3();

    }

    public static void serAndDser1() throws IOException {
        MsgProto.Msg msg = buildMsg();
        byte[] data = msg.toByteArray();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        outputStream.write(data);
        data = outputStream.toByteArray();
        MsgProto.Msg res = MsgProto.Msg.parseFrom(data);
        log.info("id = " + res.getId());
        log.info("content = " + res.getContent());
    }

    public static void serAndDser2() throws IOException {
        MsgProto.Msg msg = buildMsg();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        msg.writeTo(outputStream);
        ByteArrayInputStream inputStream = new ByteArrayInputStream(outputStream.toByteArray());


        MsgProto.Msg res = MsgProto.Msg.parseFrom(inputStream);
        log.info("id = " + res.getId());
        log.info("content = " + res.getContent());
    }

    public static void serAndDser3() throws IOException {
        MsgProto.Msg msg = buildMsg();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        msg.writeDelimitedTo(outputStream);
        ByteArrayInputStream inputStream = new ByteArrayInputStream(outputStream.toByteArray());

        MsgProto.Msg res = MsgProto.Msg.parseDelimitedFrom(inputStream);

        log.info("id = " + res.getId());
        log.info("content = " + res.getContent());
    }
}
