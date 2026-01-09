package com.cat.bis.dto;

/**
 * @author zhengshunfu
 * @date 2021/2/4 上午10:15
 */
public class LoginRequestDTO {

    /**
     * 用户名
     */
    private String userName;

    /**
     * 用户密码
     */
    private String pwd;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
}
