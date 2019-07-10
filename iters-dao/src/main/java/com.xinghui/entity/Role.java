package com.xinghui.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("s_role")
public class Role extends BaseEntity {

    /**
     * 角色名
     */
    private String roleName;
    /**
     * 角色标签
     */
    private String roleTag;
    /**
     * 默认权限 1：全部 2：部分
     */
    private Integer dataType;
}
