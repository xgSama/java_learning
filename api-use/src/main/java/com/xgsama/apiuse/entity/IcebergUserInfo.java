package com.xgsama.apiuse.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.tomcat.jni.Local;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * IcebergUserInfo
 *
 * @author : xgSama
 * @date : 2022/2/22 11:43:28
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class IcebergUserInfo {
    private Long id;
    private String name;
    private Integer age;
    private LocalDate birthday;
    private String gender;
    private Float height;
    private LocalDateTime recordTime;
}
