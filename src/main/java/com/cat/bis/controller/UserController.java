package com.cat.bis.controller;

import com.cat.bis.dto.LoginRequestDTO;
import com.cat.common.component.Result;
import com.cat.bis.dto.RegRequestDTO;
import com.cat.bis.dto.UserInfoDTO;
import com.cat.bis.service.UserService;
import com.cat.common.exception.BusinessException;
import com.cat.sec.context.MitcatContext;
import com.cat.sec.context.MitcatContextHolder;
import com.cat.sec.context.impl.MitcatContextImpl;
import com.cat.sec.util.JwtUtil;
import com.cat.sec.util.LoginUser;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 *
 * @author YongWu zheng
 * @version V1.0  Created by 2020/9/20 20:04
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @Resource
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Valid
    @PostMapping("/register")
    public Result register(@RequestBody RegRequestDTO regRequestDTO){
        //用户名密码注册
        if (!StringUtils.isEmpty(regRequestDTO.getUserName())) {
            if (!Objects.equals(regRequestDTO.getPwd(), regRequestDTO.getCheckPwd())){
                return Result.fail("两次输入的密码不一致！");
            }
            UserInfoDTO userInfoDTO = userService.selectOneByName(regRequestDTO.getUserName());
            if (userInfoDTO!=null){
                return Result.fail("该用户名已经存在！");
            }
        }
        //手机号注册
        if (!StringUtils.isEmpty(regRequestDTO.getTel())) {
            //todo 校验验证码
            UserInfoDTO userInfoDTO = userService.selectOneByName(regRequestDTO.getUserName());
            if (userInfoDTO!=null){
                return Result.fail("该手机号已被注册！");
            }
        }
        //邮箱注册
        if (!StringUtils.isEmpty(regRequestDTO.getEmail())) {
            //todo 校验验证码
            UserInfoDTO userInfoDTO = userService.selectOneByName(regRequestDTO.getUserName());
            if (userInfoDTO!=null){
                return Result.fail("该邮箱已被注册！");
            }
        }
        userService.register(regRequestDTO);
        return Result.success(null);
    }

    /**
     * 登录方法
     *
     * @param loginRequestDTO 登录信息
     * @return 结果
     */
    @ApiOperation(value = "登录接口")
    @PostMapping("/login")
    public Result login(@RequestBody LoginRequestDTO loginRequestDTO)
    {
        // 用户验证
        Authentication authentication;
        try
        {
            // 该方法会去调用UserDetailsServiceImpl.loadUserByUsername
            authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequestDTO.getUserName(), loginRequestDTO.getPwd()));
        } catch (Exception e) {
            if (e instanceof BadCredentialsException) {
                throw new BusinessException(HttpStatus.UNAUTHORIZED.value(),"账号密码错误");
            } else {
                throw new BusinessException(e.getMessage());
            }
        }
        //获得loadUserByUsername()方法的结果
        LoginUser loginUser = (LoginUser)authentication.getPrincipal();
        String token = jwtUtil.createToken(loginUser);
        return  Result.success(token);
    }

    @GetMapping("/")
    public String index(@AuthenticationPrincipal UserDetails userDetails, Model model) {
        if (userDetails != null)
        {
            model.addAttribute("username", userDetails.getUsername());
            model.addAttribute("roles", userDetails.getAuthorities());
        }
        else
        {
            model.addAttribute("username", "anonymous");
            model.addAttribute("roles", "ROLE_VISIT");
        }
        return "index";
    }

    @GetMapping("/me")
    @ResponseBody
    public Object getCurrentUser(@AuthenticationPrincipal UserDetails userDetails, Authentication authentication) {
        MitcatContext context = MitcatContextHolder.getContext();
        return context;
    }

}