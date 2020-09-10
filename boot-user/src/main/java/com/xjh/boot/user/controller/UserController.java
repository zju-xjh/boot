package com.xjh.boot.user.controller;

import com.xjh.boot.user.dto.RegisterDTO;
import com.xjh.boot.user.service.UserService;
import com.xjh.boot.user.vo.result.FailResult;
import com.xjh.boot.user.vo.result.Result;
import com.xjh.boot.user.vo.result.SuccessResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public Result register (@Valid RegisterDTO dto){
        boolean result = userService.register(dto);
        if(result){
            return new SuccessResult<>();
        }
        return new FailResult();
    }


}
