package com.xinghui.dot;

import lombok.Data;

import java.util.Date;

@Data
public class DepartmentDot {

    /**
     * 编号
     */
    private String id;
    /**
     * 部门名称
     */
    private String name;
    /**
     * 父级编号
     */
    private String parentId;
}
