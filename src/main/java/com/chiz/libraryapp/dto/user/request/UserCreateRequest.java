package com.chiz.libraryapp.dto.user.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class UserCreateRequest {

    @NotBlank(message = "ID를 입력해주세요.")
    private String userid;

    @NotBlank(message = "패스워드를 입력해주세요.")
    private String pwd;
    private String name;
    private int age;


    public UserCreateRequest() {
    }

    public UserCreateRequest(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public UserCreateRequest(String userid, String pwd, String name, int age) {
        this.userid = userid;
        this.pwd = pwd;
        this.name = name;
        this.age = age;
    }
}
