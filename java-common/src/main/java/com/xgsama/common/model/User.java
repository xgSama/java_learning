package com.xgsama.common.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * User
 *
 * @author : xgSama
 * @date : 2021/8/8 11:19:13
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {
    private String uid;
    private String nickName;
    public volatile int age;

}
