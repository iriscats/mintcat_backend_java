package com.cat.common.config;

/*
 * @Description mybatisPlus自动填充
 * @author huanghm
 * @Date 2024-01-03 11:12
 **/

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Date;

@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        if (!(authentication instanceof AnonymousAuthenticationToken) && authentication.isAuthenticated()) {
//            this.setFieldValByName("crterId", userId, metaObject);
//            this.setFieldValByName("crterName", userName, metaObject);
//            this.setFieldValByName("opterId", userId, metaObject);
//            this.setFieldValByName("opterName", userName, metaObject);
//            model.addAttribute("username", authentication.getName());
//            model.addAttribute("roles", authentication.getAuthorities());
//        } else {
//            model.addAttribute("username", "anonymous");
//            model.addAttribute("roles", "ROLE_VISIT");
//        }
        this.setFieldValByName("createTime", new Date(), metaObject);
        this.setFieldValByName("optTime", LocalDateTime.now(), metaObject);
        this.setFieldValByName("valiFlag", "0", metaObject);

        Class<?> createTime = metaObject.getGetterType("createTime");
        if (createTime == LocalDateTime.class) {
            this.setFieldValByName("updateTime", LocalDateTime.now(), metaObject);
        } else if(createTime == Date.class) {
            this.setFieldValByName("updateTime", new Date(), metaObject);
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
//        if (Objects.nonNull(AuthContextHolder.getCurrentUser())) {
//            Long userId = AuthContextHolder.getCurrentUser().getId();
//            String userName = AuthContextHolder.getCurrentUser().getUsername();
//            this.setFieldValByName("opterId", userId, metaObject);
//            this.setFieldValByName("opterName", userName, metaObject);
//        }

        Class<?> updateTime = metaObject.getGetterType("updateTime");
        if (updateTime == LocalDateTime.class) {
            this.setFieldValByName("updateTime", LocalDateTime.now(), metaObject);
        } else if(updateTime == Date.class) {
            this.setFieldValByName("updateTime", new Date(), metaObject);
        }

    }


}
