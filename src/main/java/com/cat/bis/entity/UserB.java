package com.cat.bis.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.util.Date;

@Data
@TableName("user_b")
public class UserB {

    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 密码
     */
    private String pwd;

    /**
     * 中文名
     */
    private String name;

    /**
     * 角色
     */
    private String userRole;

    /**
     * 权限分组【分组ID】
     */
    private String userGroup;

    /**
     * 联系电话
     */
    private String tel;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date updateTime;

    /**
     * 有效标志
     */
    @TableField(fill = FieldFill.INSERT)
    private String valiFlag;

    public UserB(String userName, String pwd, String email, String tel) {
        this.userName = userName;
        this.pwd = pwd;
        this.email = email;
        this.tel = tel;
        this.userRole = "user";
    }

    public UserB() {
    }
}
