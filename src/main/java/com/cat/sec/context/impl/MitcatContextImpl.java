package com.cat.sec.context.impl;

import com.cat.sec.context.MitcatContext;
import com.cat.sec.util.CurrentUser;

import java.util.HashMap;
import java.util.Map;

/*
 * @Description TODO
 * @author huanghm
 * @Date 2025-10-16 10:37
 **/
public class MitcatContextImpl implements MitcatContext {

    private static final long serialVersionUID = 8383356012441014698L;
    private CurrentUser currentUser = new CurrentUser();
    private Map properties = new HashMap();

    @Override
    public CurrentUser getCurrentUser() {
        return this.currentUser;
    }

    @Override
    public void setCurrentUser(CurrentUser currentUser) {
        this.currentUser = currentUser;
    }

    @Override
    public void removeCurrentUser() {
        this.currentUser = null;
    }

    @Override
    public Object getProperty(Object obj) {
        return this.properties.get(obj);
    }

    @Override
    public void addProperty(Object obj, Object obj1) {
        this.properties.put(obj, obj1);
    }

    @Override
    public void removeProperty(Object obj) {
        this.properties.remove(obj);
    }

    @Override
    public Map getProperties() {
        return this.properties;
    }

    @Override
    public void setProperties(Map map) {
        this.properties = map;
    }

    @Override
    public void removeAllProperties() {
        this.properties.clear();
    }
}
