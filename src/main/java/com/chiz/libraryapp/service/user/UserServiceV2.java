package com.chiz.libraryapp.service.user;

import com.chiz.libraryapp.domain.user.User;
import com.chiz.libraryapp.repository.user.UserRepository;
import com.chiz.libraryapp.dto.user.request.UserCreateRequest;
import com.chiz.libraryapp.dto.user.request.UserUpdateRequest;
import com.chiz.libraryapp.dto.user.response.UserResponse;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Primary
@Service
public class UserServiceV2 implements UserService{

    private final UserRepository userRepository;

    public UserServiceV2(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public void saveUser(UserCreateRequest request) {
        userRepository.save(new User(request.getName(), request.getAge()));
    }

    @Transactional(readOnly = true)
    public List<UserResponse> getUsers() {
        return userRepository.findAll().stream()
                .map(UserResponse::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public void updateUser(UserUpdateRequest request) {
        User user = userRepository.findById(request.getId())
                .orElseThrow(() -> new IllegalArgumentException("@@No User@@"));

        user.updateName(request.getName());
        userRepository.save(user);
    }

    @Transactional
    public void deleteUser(String name) {
        User user = userRepository.findByName(name)
                .orElseThrow(() -> new IllegalArgumentException("@@No User@@"));

        userRepository.delete(user);
    }
}
