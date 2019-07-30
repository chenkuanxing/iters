package com.xinghui.dot;

import lombok.Data;

import java.util.Date;

@Data
public class FileManageDot {

    /**
     * 编号
     */
    private String id;
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
