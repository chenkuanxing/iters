package com.xinghui.entity;
import cn.afterturn.easypoi.excel.annotation.Excel;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import java.util.Date;
@Data
@TableName("s_recode")
public class Recode extends BaseEntity{
    /**
     * 定制任务标题
     */
    private String title;
    /**
     * 发布人
     */
    private String publisher;
//    /**
//     * 发布部门
//     */
//    private String publishDep;
    /**
     * 执行人
     */
    private String performBy;
    /**
     *发布内容
     */
    private String content;
    /**
     *执行时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date performTime;

    @TableField(exist = false)
    private String publisherName;
}
