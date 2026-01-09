package com.cat.bis.dto;

import io.swagger.annotations.ApiOperation;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/*
 * @Description 注册请求参数
 * @author huanghm
 * @Date 2025-09-17 16:03
 **/
@Data
public class RegRequestDTO {

    /**
     * 用户名
     */
    private String userName;

    /**
     * 联系电话
     */
    private String tel;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 密码
     */
    private String pwd;

    /**
     * 确认密码
     */
    private String checkPwd;

    /**
     * 验证码
     */
    private String verifyCode;

}
