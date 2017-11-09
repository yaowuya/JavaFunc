package com.cn.common;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 统一处理controller层抛出的异常
 * Created by 钟锐锋 on 2017/11/9.
 */
public class CustomHandlerExceptionResolver implements HandlerExceptionResolver {
    private static Logger logger= LoggerFactory.getLogger(CustomHandlerExceptionResolver.class);

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object o, Exception e) {
        String requetType=request.getHeader("X-Requested-With");
        ModelAndView modelAndView=new ModelAndView("error");
        try {
            if(StringUtils.equalsAnyIgnoreCase("XMLHttpRequest",requetType)){
                response.setContentType("text/html;charset=UTF-8");
                PrintWriter printWriter=response.getWriter();
                printWriter.println(JsonUtils.toJson(new JsonResult(0,false,"操作失败,失败原因:"+e.getMessage(),null)));
                printWriter.flush();
                printWriter.close();
                return null;
            }
        }catch (IOException e1){
            e.printStackTrace();
        }
        modelAndView.addObject("error", ExceptionUtils.getFullStackTrace(e));
        return modelAndView;
    }
}
