package com.cat.common.exception.resolver;

import com.cat.common.component.Result;
import com.cat.common.exception.CatException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.Nullable;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.AbstractHandlerExceptionResolver;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/* loaded from: hsa-ims-common-ZJ-330000-V1.1.0-RELEASE.jar:cn/hsa/ims/common/resolver/ImsHandleExceptionResolver.class */
public class HandleExceptionResolver extends AbstractHandlerExceptionResolver {
    private String defaultErrorView;
    private String defaultErrorMessage;
    private static final String EXCEPT_HEADER = ",异常流水号exseq=";
    private static final String EXCEPT_INFO = ",错误信息:";
    private static final String M_S_G = "message";
    private static final String TYPE = "type";
    protected static final Integer ERROR_CODE = 210012;
    
    private Logger log = LoggerFactory.getLogger((Class<?>) HandleExceptionResolver.class);
    private String viewType = "json";
    private Map<Integer, String> exceptionMappings = new HashMap();
    private final String LOG_CONTEXT_KEY = "_logContext";

    @Override // org.springframework.web.servlet.handler.AbstractHandlerExceptionResolver
    @Nullable
    protected ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        ModelAndView mv;
        Integer errorCode = Result.FAIL;
        getClass();
        if (ex instanceof CatException) {
            errorCode = ((CatException) ex).getCode();
            this.log.warn(EXCEPT_INFO + ex.getMessage());
        } else if (ex.getCause() == null) {
            this.log.error(EXCEPT_INFO + ex.getMessage(), ex);
        } else {
            this.log.error(EXCEPT_INFO + ex.getMessage(), ex.getCause());
        }
        if ("page".equalsIgnoreCase(this.viewType)) {
            String viewName = this.defaultErrorView;
            if (this.exceptionMappings != null && this.exceptionMappings.containsKey(errorCode)) {
                viewName = this.exceptionMappings.get(errorCode);
            }
            mv = new ModelAndView();
            mv.setViewName(viewName);
        } else {
            mv = new ModelAndView(new MappingJackson2JsonView());
        }
        mv.addObject("code", errorCode);
        if ((ex instanceof CatException) && ex.getMessage() != null && ex.getMessage().length() > 0) {
            if (ERROR_CODE == ((CatException) ex).getCode()) {
                mv.addObject("message", ex.getMessage());
            } else {
                mv.addObject("message", ex.getMessage());
                mv.addObject("type", Result.ResponseType.TYPE_INFO.getType());
            }
        } else if (StringUtils.isNotBlank(this.defaultErrorMessage)) {
            mv.addObject("message", this.defaultErrorMessage);
        } else {
            mv.addObject("message", ex.getMessage());
        }
        return mv;
    }

    public void setViewType(String viewType) {
        this.viewType = viewType;
    }

    public void setDefaultErrorView(String defaultErrorView) {
        this.defaultErrorView = defaultErrorView;
    }

    public void setExceptionMappings(Map<Integer, String> exceptionMappings) {
        this.exceptionMappings.putAll(exceptionMappings);
    }

    public void addExceptionMappings(Integer errorCode, String errorView) {
        this.exceptionMappings.put(errorCode, errorView);
    }

    public void setDefaultErrorMessage(String defaultErrorMessage) {
        this.defaultErrorMessage = defaultErrorMessage;
    }
}