package com.jaybon.firestoreex01;

import com.google.firebase.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    private int id;
    private String username;
    private String password;
    private String email;
    private Timestamp createDate;
}
