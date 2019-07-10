package com.xinghui.serviceImpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xinghui.service.UserService;
import com.xinghui.entity.User;
import com.xinghui.exception.CustException;
import com.xinghui.mapper.UserMapper;
import com.xinghui.security.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public boolean updatePass(String oldPassword, String newPassword, String password) {
        User user = SecurityUtils.getCurrentUser();
        if (!StringUtils.isEmpty(oldPassword)) {
            if (!StringUtils.isEmpty(newPassword)) {
                if (!StringUtils.isEmpty(password)) {
                    if (password.equals(newPassword)) {
                        if (!passwordEncoder.matches(oldPassword, user.getPassword())) {
                            throw new CustException("原始密码校验错误!");
                        }
                        user.setPassword(passwordEncoder.encode(newPassword));
                    } else {
                        throw new CustException("新密码和确认密码不匹配!");
                    }
                } else {
                    throw new CustException("请输入确认新密码!");
                }
            } else {
                throw new CustException("请输入新密码!");
            }
        } else {
            throw new CustException("请输入原始密码!");
        }
        return this.updateById(user);
    }

}
