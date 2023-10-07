package com.chiz.libraryapp.controller.user;

import com.chiz.libraryapp.dto.user.request.UserCreateRequest;
import com.chiz.libraryapp.dto.user.request.UserDeleteRequest;
import com.chiz.libraryapp.dto.user.request.UserLoginRequest;
import com.chiz.libraryapp.dto.user.request.UserUpdateRequest;
import com.chiz.libraryapp.dto.user.response.UserResponse;
import com.chiz.libraryapp.service.user.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    //로그인 페이지
    @GetMapping("/user/login")
    public String loginUserForm(Model model) {
        model.addAttribute("userLoginRequest", new UserLoginRequest());
        return "user/loginUserForm";
    }

    //로그인
    @PostMapping("/user/login")
    public String loginUser(@Valid @ModelAttribute("userLoginRequest") UserLoginRequest request, BindingResult bindingResult) {
        if (bindingResult.hasErrors()){
            return "user/loginUserForm";
        }

        return "redirect:/";
    }

    // 회원가입 페이지
    @GetMapping("/user/create")
    public String saveUserForm(Model model) {
        model.addAttribute("userCreateRequest", new UserCreateRequest());
        return "user/saveUserForm";
    }

    //회원가입
    @PostMapping("/user/create")
    public String saveUser(@Valid @ModelAttribute("userCreateRequest") UserCreateRequest request, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "user/saveUserForm";
        }
        userService.saveUser(request);

        return "redirect:/user";
    }

    //회원목록
    @GetMapping("/user")
    public String getUsers(Model model) {
        List<UserResponse> users = userService.getUsers();
        model.addAttribute("users", users);

        return "user/userList";
    }

    //유저 정보 수정
    @PutMapping("/user")
    public String updateUser(UserUpdateRequest request) {
        userService.updateUser(request);

        return "redirect:/user";
    }

    //유저 삭제
    @DeleteMapping("/user")
    public String deleteUser(UserDeleteRequest request) {
        userService.deleteUser(request.getName());

        return "redirect:/user";
    }

}
