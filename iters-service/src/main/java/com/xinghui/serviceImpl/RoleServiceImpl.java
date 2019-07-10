package com.xinghui.serviceImpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xinghui.entity.Role;
import com.xinghui.mapper.RoleMapper;
import com.xinghui.service.RoleService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {
    @Override
    public List<String> getRoleTag(String id) {
        return baseMapper.getRoleTag(id);
    }
}
