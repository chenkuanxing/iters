package com.xinghui.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("s_resource")
public class Resource extends BaseEntity{
    /**
     * 资源名称
     */
    private String name;
    /**
     * 父级编号
     */
    private String pid;
    /**
     * 链接地址
     */
    private String url;
    /**
     * 序号
     */
    private Integer sort;
    /**
     * 描述
     */
    private String remark;
    /**
     * 资源标记
     */
    private String resource_tag;
}
