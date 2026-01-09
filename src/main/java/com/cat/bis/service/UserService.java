package com.cat.bis.service;

import com.cat.bis.dto.RegRequestDTO;
import com.cat.bis.dto.UserInfoDTO;
import com.cat.bis.entity.UserB;

public interface UserService {

    UserInfoDTO selectOneByName(String userName);

    Integer insertUser(UserB userB);

    void register(RegRequestDTO regRequestDTO);

    UserB getUserById(String userId);
}
