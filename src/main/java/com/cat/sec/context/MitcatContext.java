package com.cat.sec.context;

import com.cat.sec.util.CurrentUser;

import java.util.Map;

/*
 * @Description TODO
 * @author huanghm
 * @Date 2025-10-16 09:52
 **/
public interface MitcatContext {

    String HSAF_CONTEXT_KEY = "HsafContext";

    CurrentUser getCurrentUser();

    void setCurrentUser(CurrentUser var1);

    void removeCurrentUser();

    Object getProperty(Object var1);

    void addProperty(Object var1, Object var2);

    void removeProperty(Object var1);

    Map getProperties();

    void setProperties(Map var1);

    void removeAllProperties();
}
