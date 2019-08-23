package com.xinghui.dot;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class LocationStaticExportDot {
    /**
     * 部门名称
     */
    @Excel(name = "部门名称", orderNum = "1", width = 15, mergeVertical = true)
    private String name;
    /**
     * 部门发表文章数目
     */
    @Excel(name = "部门发表文章数目", orderNum = "1", width = 20, mergeVertical = true)
    private Integer count;
    /**
     * 部门发表文章开始时间
     */
    private Date beginsExportTimes;
    /**
     * 部门发表文章开始时间
     */
    @Excel(name = "部门发表文章开始时间", orderNum = "1", width = 20, mergeVertical = true)
    private String beginsExportsTimes;
    /**
     * 发布文章最多的部门
     */
    @Excel(name = "发布文章最多的部门", orderNum = "1", width = 20, mergeVertical = true)
    private String exportMax;
    /**
     * 发布文章最少的部门
     */
    @Excel(name = "发布文章最少的部门", orderNum = "1", width = 20, mergeVertical = true)
    private String exportMin;
    /**
     * 部门发表文章结束时间
     */
    private Date endsExportTimes;
    /**
     * 部门发表文章结束时间
     */
    @Excel(name = "部门发表文章结束时间", orderNum = "1", width = 20, mergeVertical = true)
    private String endsExportsTimes;
    /**
     * 发表文章的总和
     */
    @Excel(name = "发表文章的总和", orderNum = "1", width = 18, mergeVertical = true)
    private Integer exportSum;

    private List<LocationStaticExportDot>  staticExportList;
}
