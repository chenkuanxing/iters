package com.xinghui.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xinghui.entity.Role;

import java.util.List;

public interface RoleService extends IService<Role> {
    List<String> getRoleTag(String id);
}
