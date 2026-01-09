package com.cat.sec.filter;

import com.cat.bis.entity.UserB;
import com.cat.bis.service.UserService;
import com.cat.sec.context.MitcatContextHolder;
import com.cat.sec.context.impl.MitcatContextImpl;
import com.cat.sec.util.LoginUser;
import com.cat.sec.util.JwtUtil;
import com.cat.sec.util.SecurityUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

/**
 * token过滤器 验证token有效性
 *
 * @author ruoyi
 */
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter
{
    @Autowired
    private JwtUtil jwtUtil;

    @Resource
    private UserService userService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        //login、swagger跳过
        String token = request.getHeader("Authorization");
        if(request.getServletPath().contains("login")){
            chain.doFilter(request, response);
            return;
        }

        LoginUser loginUser = jwtUtil.getLoginUser(request);


        if (!Objects.isNull(loginUser) && Objects.isNull(SecurityUtils.getAuthentication()))
        {
            jwtUtil.verifyToken(loginUser);
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginUser, null, loginUser.getAuthorities());
            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            String userId = loginUser.getUserId().toString();
            UserB userB = userService.getUserById(userId);
            MitcatContextImpl mitcatContext = new MitcatContextImpl();
            mitcatContext.addProperty("userId", userId);
            mitcatContext.addProperty("userName", userB.getUserName());
            MitcatContextHolder.setContext(mitcatContext);
        }
        chain.doFilter(request, response);
    }
}
