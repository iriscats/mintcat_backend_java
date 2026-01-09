package com.cat.common.exception.resolver;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cat.common.exception.BusinessException;
import com.cat.common.exception.CatException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestControllerAdvice
/* loaded from: hsa-ims-common-ZJ-330000-V1.1.0-RELEASE.jar:cn/hsa/ims/common/resolver/ImsExceptionResolver.class */
public class ExceptionResolver extends HandleExceptionResolver {
    private static final Logger LOGGER = LoggerFactory.getLogger((Class<?>) ExceptionResolver.class);

    @Override // cn.hsa.ims.common.resolver.ImsHandleExceptionResolver, org.springframework.web.servlet.handler.AbstractHandlerExceptionResolver
    @ExceptionHandler({Exception.class})
    protected ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        LOGGER.info("ims system happen error", (Throwable) ex);
        if (ex instanceof CatException) {
            CatException imsException = (CatException) ex;
            return super.doResolveException(request, response, handler, imsException);
        }
        if ((ex instanceof HttpMessageNotReadableException) || (ex instanceof InvalidFormatException) || (ex instanceof HttpMediaTypeNotSupportedException)) {
            CatException imsException2 = new CatException(210009);
            return super.doResolveException(request, response, handler, imsException2);
        }
        if (ex instanceof HttpRequestMethodNotSupportedException) {
            CatException imsException3 = new CatException(210010);
            return super.doResolveException(request, response, handler, imsException3);
        }
        if (ex instanceof MaxUploadSizeExceededException) {
            CatException imsException4 = new CatException(210011);
            return super.doResolveException(request, response, handler, imsException4);
        }
        if (ex instanceof MethodArgumentNotValidException) {
            BindingResult bindingResult = ((MethodArgumentNotValidException) ex).getBindingResult();
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            FieldError fieldError = fieldErrors.get(0);
            JSONObject jsonObject = JSON.parseObject(fieldError.getDefaultMessage());
            CatException imsException5 = new CatException(jsonObject.getInteger("code"), jsonObject.getString("message"));
            return super.doResolveException(request, response, handler, imsException5);
        }
        BusinessException businessException = new BusinessException(ex);
        return super.doResolveException(request, response, handler, businessException);
    }
}