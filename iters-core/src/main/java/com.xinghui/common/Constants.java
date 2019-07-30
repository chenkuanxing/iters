package com.xinghui.common;

public interface Constants {

    /**
     * 是否有用 -1：false 1:true
     */
    class status {
        public static final Integer FALSE = -1;
        public static final Integer TRUE = 1;
    }

    /**
     * 文件类型(1:私人 2:工作 3:项目)
     */
    class fileType {
        public static final Integer PRIVATE = 1;
        public static final Integer JOB = 2;
        public static final Integer PROJECT = 3;
    }

}
