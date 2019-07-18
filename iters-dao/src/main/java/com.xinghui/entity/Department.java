package com.xinghui.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("s_department")
public class Department extends BaseEntity {
    /**
     * 部门名称
     */
    private String name;
    /**
     * 父级编号
     */
    private String parentId;
}
