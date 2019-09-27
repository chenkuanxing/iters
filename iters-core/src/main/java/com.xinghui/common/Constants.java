package com.xinghui.common;

import java.util.HashMap;
import java.util.Map;

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

    /**
     * 是否有用 0：是 1:否
     */
    static class emailStatus {
        public static final Integer FALSE = 0;
        public static final Integer TRUE = 1;
        public static Map<Integer,String> map = new HashMap<>();
        static{
            map.put(FALSE,"是");
            map.put(TRUE,"否");
        }
    }

}
