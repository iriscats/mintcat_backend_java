package com.cat.sec.util;

import com.cat.common.exception.BusinessException;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * 安全服务工具类
 * @author zhengshunfu
 * @date 2021/2/2 下午4:17
 */
public class SecurityUtils
{
    /**
     * 获取用户账户
     **/
    public static String getUsername() throws BusinessException {
        try
        {
            return getLoginUser().getUsername();
        }
        catch (Exception e)
        {
            throw new BusinessException( HttpStatus.UNAUTHORIZED.value(),"获取用户账户异常");
        }
    }

    /**
     * 获取用户id
     **/
    public static String getUserId() throws BusinessException {
        try
        {
            return getLoginUser().getUserId().toString();
        }
        catch (Exception e)
        {
            throw new BusinessException( HttpStatus.UNAUTHORIZED.value(),"获取用户账户异常");
        }
    }

    /**
     * 获取用户
     **/
    public static LoginUser getLoginUser() throws BusinessException {
        try
        {
            return (LoginUser) getAuthentication().getPrincipal();
        }
        catch (Exception e)
        {
            throw new BusinessException(HttpStatus.UNAUTHORIZED.value(),"获取用户信息异常" );
        }
    }

    /**
     * 获取Authentication
     */
    public static Authentication getAuthentication()
    {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    /**
     * 生成BCryptPasswordEncoder密码
     *
     * @param password 密码
     * @return 加密字符串
     */
    public static String encryptPassword(String password)
    {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(password);
    }

    /**
     * 判断密码是否相同
     *
     * @param rawPassword 真实密码（手动输入旧密码-md5加密后）
     * @param encodedPassword 加密后字符（现登录密码）
     * @return 结果
     */
    public static boolean matchesPassword(String rawPassword, String encodedPassword)
    {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }

    /**
     * 是否为管理员
     *
     * @param userId 用户ID
     * @return 结果
     */
    public static boolean isAdmin(Long userId)
    {
        return userId != null && 1L == userId;
    }
}
