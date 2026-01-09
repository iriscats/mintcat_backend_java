package com.cat.bis.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.cat.bis.dto.RegRequestDTO;
import com.cat.bis.dto.UserInfoDTO;
import com.cat.bis.entity.UserB;
import com.cat.bis.mapper.UserBMapper;
import com.cat.bis.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Objects;

/*
 * @Description 用户服务
 * @author huanghm
 * @Date 2025-09-17 16:17
 **/
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserBMapper userBMapper;

    @Resource
    private PasswordEncoder passwordEncoder;

    @Override
    public UserInfoDTO selectOneByName(String userName) {
        UserInfoDTO userInfoDTO = new UserInfoDTO();
        LambdaQueryWrapper<UserB> wrapper = new LambdaQueryWrapper<UserB>();
        wrapper.eq(UserB::getUserName, userName);
        UserB userb = this.userBMapper.selectOne(wrapper);
        if (Objects.isNull(userb)) {
            return null;
        }
        BeanUtils.copyProperties(userb, userInfoDTO);
        return userInfoDTO;
    }

    @Override
    public Integer insertUser(UserB userB) {
        return userBMapper.insert(userB);
    }

    @Override
    public void register(RegRequestDTO regRequestDTO) {
        UserB userB = new UserB();
        BeanUtils.copyProperties(regRequestDTO, userB);
        userB.setPwd(passwordEncoder.encode(userB.getPwd()));
        userBMapper.insert(userB);
    }

    @Override
    public UserB getUserById(String userId) {
        return userBMapper.selectById(userId);
    }
}
