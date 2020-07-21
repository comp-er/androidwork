package com.lwm.activityex02;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User implements Serializable { // 직렬화 타입 Serializable
    private int id;
    private String username;
    private String password;
}
