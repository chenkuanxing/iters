package com.xinghui.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xinghui.entity.User;
import com.xinghui.security.CustUserDetails;

public interface UserService extends IService<User> {

    boolean updatePass(String oldPassword, String newPassword, String password);
}
