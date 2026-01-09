package com.cat.bis.dto;

import lombok.Data;

/*
 * @Description TODO
 * @author huanghm
 * @Date 2025-09-17 16:26
 **/
@Data
public class UserInfoDTO {

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
}
