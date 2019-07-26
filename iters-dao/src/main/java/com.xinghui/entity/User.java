package com.xinghui.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("s_user")
public class User extends BaseEntity{
    /**
     * 账号
     */
    private String userCode;
    /**
     * 密码
     */
    private String password;
    /**
     * 用户名
     */
    private String userName;
    /**
     * 性别
     */
    private Integer sex;

    /**
     * 手机号码
     */
    private String mobileNo;
    /**
     * 邮件
     */
    private String email;
    /**
     * 角色编号
     */
    private String roleId;
    /**
     * 部门编号
     */
    private String depId;
}
