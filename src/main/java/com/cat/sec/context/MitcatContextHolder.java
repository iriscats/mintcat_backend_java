package com.cat.sec.context;

import com.cat.sec.context.impl.MitcatContextImpl;

/*
 * @Description TODO
 * @author huanghm
 * @Date 2025-10-16 09:52
 **/
public class MitcatContextHolder {
    private static ThreadLocal contextHolder = new ThreadLocal();

    public static void setContext(MitcatContext context) {
        contextHolder.set(context);
    }

    public static MitcatContext getContext() {
        MitcatContext context = (MitcatContext)contextHolder.get();
        if (context == null) {
            context = new MitcatContextImpl();
            setContext(context);
        }
        return context;
    }
}
