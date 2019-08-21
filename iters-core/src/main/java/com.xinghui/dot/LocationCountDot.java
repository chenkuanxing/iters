package com.xinghui.dot;

import lombok.Data;

import java.util.List;

@Data
public class LocationCountDot {
    /**
     * 总和
     */
    private Integer sum;
    /**
     * 发布最多的部门
     */
    private String max;
    /**
     * 发布最少的部门
     */
    private String min;

    private List<LocationStaticDot> locationStaticDotList;
}
