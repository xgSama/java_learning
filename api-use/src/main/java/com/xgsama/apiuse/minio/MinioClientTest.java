package com.xgsama.apiuse.minio;

import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.UploadObjectArgs;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * MinioClient
 *
 * @author : xgSama
 * @date : 2021/9/12 00:07:47
 */
public class MinioClientTest {
    private static final String ENDPOINT = "http://xgsama:9555";
    private static final String ACCESSKEY = "xgsama";
    private static final String SECRETKEY = "xgsama__";


    public static void main(String[] args) throws Exception {
        MinioClient minioClient =
                MinioClient.builder()
                        .endpoint(ENDPOINT)
                        .credentials(ACCESSKEY, SECRETKEY)
                        .build();

        File file = new File("/Users/xgSama/IdeaProjects/java_learning/README.md");
        FileInputStream fileInputStream = new FileInputStream(file);


//        minioClient.uploadObject(
//                UploadObjectArgs.builder()
//                        .bucket("xgsama-mall")
//                        .object("README.md")
//                        .filename("/Users/xgSama/IdeaProjects/java_learning/README.md")
//                        .build());

        minioClient.putObject(PutObjectArgs.builder()
                .bucket("halo")
                .stream(fileInputStream, -1, PutObjectArgs.MAX_PART_SIZE)
                .object("README1.md")
                .build());


    }
}
