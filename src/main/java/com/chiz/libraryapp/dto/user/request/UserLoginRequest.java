package com.chiz.libraryapp.dto.user.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class UserLoginRequest {

    @NotBlank(message = "아이디를 입력해주세요")
    private String userid;

    @NotBlank(message = "비밀번호를 입력해주세요")
    private String pwd;

    public UserLoginRequest() {
    }

    public UserLoginRequest(String userid, String pwd) {
        this.userid = userid;
        this.pwd = pwd;
    }
}
