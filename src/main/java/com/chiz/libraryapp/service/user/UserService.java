package com.chiz.libraryapp.service.user;

import com.chiz.libraryapp.dto.user.request.UserCreateRequest;
import com.chiz.libraryapp.dto.user.request.UserUpdateRequest;
import com.chiz.libraryapp.dto.user.response.UserResponse;

import java.util.List;

public interface UserService {

    void saveUser(UserCreateRequest request);
    List<UserResponse> getUsers();
    void updateUser(UserUpdateRequest request);
    void deleteUser(String name);
}
