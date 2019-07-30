package com.xinghui.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("s_file_manage")
public class FileManage extends BaseEntity {
    /**
     * 文件名称
     */
    private String name;
    /**
     * 文件类型(1:私人 2:工作 3:项目)
     */
    private Integer type;
    /**
     * 文件路径
     */
    private String url;
    /**
     * 文件格式
     */
    private String format;
    /**
     * 文件大小
     */
    private String size;
    /**
     * 1:个人档案  2：公共档案
     */
    private Integer fileType;
}
