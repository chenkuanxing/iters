package com.xinghui.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xinghui.ResultDto;
import com.xinghui.common.Constants;
import com.xinghui.entity.User;
import com.xinghui.security.SecurityUtils;
import com.xinghui.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    /**
     * 获取用户信息
     *
     * @return
     */
    @GetMapping(value = "/userInfo")
    public ResultDto userInfo() {
        return success(userService.getOne(new QueryWrapper<User>().lambda().eq(User::getId, SecurityUtils.getCurrentUserId()).eq(User::getStatus, Constants.status.TRUE)));
    }

    /**
     * 修改密码
     *
     * @return
     */
    @PostMapping(value = "/change")
    public ResultDto change(String oldPassword, String newPassword, String password) {
        return success(userService.updatePass(oldPassword, newPassword, password));
    }


}