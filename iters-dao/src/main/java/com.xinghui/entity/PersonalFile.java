package com.xinghui.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("s_personal_file")
public class PersonalFile extends BaseEntity {
    /**
     * 文件名称
     */
    private String name;
    /**
     * 项目类型
     */
    private Integer type;
    /**
     * 文件路径
     */
    private String url;
    /**
     * 备注
     */
    private String descs;
    /**
     * 是否有效
     */
    private String isValid;
    /**
     * 1:个人档案  2：公共档案
     */
    private Integer fileType;
}
