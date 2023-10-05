package com.chiz.libraryapp.dto.user.response;

import com.chiz.libraryapp.domain.user.User;
import lombok.Getter;

@Getter
public class UserResponse {

    private Long id;
    private String name;
    private Integer age;

    public UserResponse() {
    }

    public UserResponse(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.age = user.getAge();
    }

    public UserResponse(Long id, String name, Integer age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }
}
