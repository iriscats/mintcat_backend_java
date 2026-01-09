package com.cat.common.exception;

import org.apache.commons.codec.CharEncoding;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.PropertiesLoaderSupport;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Properties;


/**
 * <p>
 * 异常错误配置读取类
 * </p>
 * @version 1.0
 */
@Component
public class ExceptionConfigReader extends PropertiesLoaderSupport implements ResourceLoaderAware, InitializingBean {
    private static ResourceLoader resourceLoader;

    private static Properties mergeProperties;

    // 错误码的约定配置路径 约定大于配置
    private static final String DEFAULT_ERROR_CODE_CONFIG_PATH = "classpath:config/ErrorCode.properties";

    public static void setResource(ResourceLoader resourceLoader) {
        ExceptionConfigReader.resourceLoader = resourceLoader;
    }

    public static void setMergeProperties(Properties mergeProperties) {
        ExceptionConfigReader.mergeProperties = mergeProperties;
    }

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        setResource(resourceLoader);
    }

    @Override
    public void afterPropertiesSet() throws IOException {
        ResourcePatternResolver resourcePatternResolver = (ResourcePatternResolver) resourceLoader;
        Resource[] defaultResources = resourcePatternResolver.getResources(DEFAULT_ERROR_CODE_CONFIG_PATH);
        this.setFileEncoding(CharEncoding.UTF_8);
        this.setLocations(defaultResources);
        ExceptionConfigReader.setMergeProperties(this.mergeProperties());
    }

    /***
     * 获取固定的错误信息
     * @param code 错误码
     * @return 错误信息
     */
    public static final String getExceptionMessage(Integer code) {
        return mergeProperties.getProperty(String.valueOf(code));
    }

    /**
     * 获取动态的错误信息
     *
     * @param code       错误码
     * @param dynaValues 动态数组
     * @return 错误信息
     */
    public static final String getExceptionMessage(Integer code, String[] dynaValues) {
        String oriMessage = mergeProperties.getProperty(String.valueOf(code));
        String value = oriMessage;
        for (int i = 0; i < dynaValues.length; i++) {
            String dynaValue = dynaValues[i];
            value = StringUtils.replace(value, "{" + i + "}", dynaValue);
        }
        return value;
    }

}
