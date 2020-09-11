package com.xjh.boot.user.controller;

import com.xjh.boot.user.dto.LoginDTO;
import com.xjh.boot.user.dto.ModifyPasswordDTO;
import com.xjh.boot.user.dto.RegisterDTO;
import com.xjh.boot.user.po.User;
import com.xjh.boot.user.service.UserService;
import com.xjh.boot.user.util.redis.RedisOperator;
import com.xjh.boot.user.vo.LoginVO;
import com.xjh.boot.user.vo.UserVO;
import com.xjh.boot.user.vo.result.FailResult;
import com.xjh.boot.user.vo.result.Result;
import com.xjh.boot.user.vo.result.SuccessResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/user")
public class UserController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserService userService;

    @Autowired
    private RedisOperator redisOperator;

    @PostMapping("/register")
    public Result register (@Valid RegisterDTO dto){
        boolean result = userService.register(dto);
        if(result){
            return new SuccessResult<>();
        }
        return new FailResult();
    }

    /**
     * 登陆接口
     *
     * @return
     */
    @PostMapping("/login")
    public Result login(LoginDTO dto) {
        LoginVO vo = userService.login(dto);
        if (vo == null) {
            return new FailResult();
        }
        return new SuccessResult<>(vo);
    }

    /**
     * 修改密码
     * 参数通过 json 传递
     *
     * @param dto
     * @return
     */
    @PutMapping("/password")
    public Result modifyPassword(@Valid @RequestBody ModifyPasswordDTO dto, HttpServletRequest httpServletRequest) {
        String token = httpServletRequest.getHeader("token");
        User user = (User) redisOperator.get(token);
        boolean result = userService.modifyPassword(dto, user);
        if (result) {
            return new SuccessResult<>();
        }
        return new FailResult();
    }

    /**
     * 获取用户信息
     * 参数通过url传递
     *
     * @param id
     * @return
     */
    @GetMapping
    public Result getUser(@NotNull(message = "id不能为空") Integer id) {
        UserVO vo = userService.getUser(id);
        return new SuccessResult<>(vo);
    }


}
