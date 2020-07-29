package com.jaybon.retrofit2ex01;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {

    public long id;
    public String username;
    public String password;
    public String email;
    public Object profile;
    public String createDate;

}