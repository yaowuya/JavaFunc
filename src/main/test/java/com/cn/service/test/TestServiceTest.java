package com.cn.service.test;

import com.alibaba.fastjson.JSON;
import com.cn.entity.test.TestUser;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;


/**
 * Created by 钟锐锋 on 2017/9/4.
 */
@RunWith(SpringJUnit4ClassRunner.class)     //表示继承了SpringJUnit4ClassRunner类
@ContextConfiguration(locations = {"classpath:spring-mybatis.xml"})
public class TestServiceTest {
    private static Logger logger= Logger.getLogger(TestServiceTest.class);

    @Resource
    private TestService testService=null;

    @Test
    public void getUserById() throws Exception {
        TestUser user = testService.getUserById(1);
         System.out.println(user.getUserName());
        // logger.info("值："+user.getUserName());
        logger.info(JSON.toJSONString(user));
    }

}