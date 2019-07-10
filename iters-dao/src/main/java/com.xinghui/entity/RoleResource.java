package com.xinghui.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("s_role_resource")
public class RoleResource extends BaseEntity{
    /**
     * 角色编号
     */
    private String role_id;
    /**
     * 资源编号
     */
    private String resource_id;
    /**
     * 2部分选中，1全选，0未选中
     */
    private Integer checked;
}
