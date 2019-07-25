package com.xinghui.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * @author chenkuanxing
 * @since 20190710
 */

@EqualsAndHashCode(callSuper = true)
@Data
public abstract class BaseEntity extends Model {
    /**
     * 编号
     */
    @TableId
    private String id;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createdTime;

    /**
     * 创建人
     */
    @TableField(fill = FieldFill.INSERT)
    private String createdUser;

    /**
     * 最近修改时间
     */
    @TableField(fill = FieldFill.UPDATE)
    private Date lastChanged;

    /**
     * 最近修改人
     */
    @TableField(fill = FieldFill.UPDATE)
    private String lastChangedUser;

    /**
     * 状态 1：正常 -1：删除
     */

    @TableLogic
    private Integer status;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }
}
