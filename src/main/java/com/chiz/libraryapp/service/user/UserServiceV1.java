package com.chiz.libraryapp.service.user;

import com.chiz.libraryapp.dto.user.request.UserCreateRequest;
import com.chiz.libraryapp.dto.user.request.UserUpdateRequest;
import com.chiz.libraryapp.dto.user.response.UserResponse;
import com.chiz.libraryapp.repository.user.UserJdbcRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceV1 implements UserService {

    private final UserJdbcRepository userJdbcRepository;

    public UserServiceV1(UserJdbcRepository userJdbcRepository) {
        this.userJdbcRepository = userJdbcRepository;
    }

    public void saveUser(UserCreateRequest request) {
        userJdbcRepository.saveUser(request);
    }

    public List<UserResponse> getUsers() {
        return userJdbcRepository.getUsers();
    }

    public void updateUser(UserUpdateRequest request) {
        // 사용자 존재 여부 확인
        boolean isUserNotExist = userJdbcRepository.isUserNotExist(request.getId());
        if (isUserNotExist) {
            throw new IllegalArgumentException("No User!!");
        }
        
        // 사용자 이름 수정
        userJdbcRepository.updateUserName(request.getName(), request.getId());
    }

    public void deleteUser(String name) {
        //사용자 존재 여부 확인
        boolean isUserNotExist = userJdbcRepository.isUserNotExist(name);
        if (isUserNotExist) {
            throw new IllegalArgumentException("No User!!");
        }

        //사용자 정보 삭제
        userJdbcRepository.deleteUser(name);
    }
}
