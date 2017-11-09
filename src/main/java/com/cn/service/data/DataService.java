package com.cn.service.data;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by 钟锐锋 on 2017/11/9.
 */
public class DataService implements ApplicationContextAware{
    private static Logger logger= LoggerFactory.getLogger(DataService.class);
    private ApplicationContext applicationContext;

    private static Map<String,Object> parameterMap=new HashMap();

    /**
     * 初始化全局参数，在系统启动时加载
     */
    @PostConstruct
    public void initSysData(){
        new Thread(()->{
            try {
                //初始化全局参数parameterMap
                Map<String,Object> paramsMap=new HashMap<String, Object>();
                paramsMap.put("user","小明");
                paramsMap.put("password","123456");
                setParameterMap(paramsMap);

            }catch (Exception e){
                e.printStackTrace();
                logger.error("初始化全局参数出错：{}",e.getMessage());
            }
        }).start();
    }

    public static Map<String, Object> getParameterMap() {
        return parameterMap;
    }

    public static void setParameterMap(Map<String, Object> parameterMap) {
        DataService.parameterMap = parameterMap;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext=applicationContext;
    }


}
