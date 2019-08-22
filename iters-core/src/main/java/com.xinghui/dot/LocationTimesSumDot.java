package com.xinghui.dot;

import lombok.Data;

import java.util.List;

@Data
public class LocationTimesSumDot {
    /**
     * 部门发表文章开始时间
     */
    private String beginssTimess;
    /**
     * 部门发表文章结束时间
     */
    private String endssTimess;

    private List<LocationTimesDot> locationTimesDotsList;

}
