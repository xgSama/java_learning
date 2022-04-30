package com.xgsama.apiuse.poi;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Student
 *
 * @author: xgsama
 * @date: 2022-04-23 14:11:40
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    private String id;
    private String username;
    private String password;
}
