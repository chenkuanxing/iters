package com.xinghui.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xinghui.entity.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleMapper extends BaseMapper<Role> {
    List<String> getRoleTag(@Param("id") String id);
}
