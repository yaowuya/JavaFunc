package com.cn.service.test;

import com.alibaba.fastjson.JSON;
import com.cn.BaseTest;
import com.cn.entity.test.TestUser;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.junit.Test;

import javax.annotation.Resource;


/**
 * Created by 钟锐锋 on 2017/9/4.
 */
public class TestServiceTest extends BaseTest {

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