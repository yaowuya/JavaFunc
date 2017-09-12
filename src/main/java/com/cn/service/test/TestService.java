package com.cn.service.test;

import com.cn.dao.test.TestUserMapper;
import com.cn.entity.test.TestUser;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by 钟锐锋 on 2017/9/4.
 */
@Service
public class TestService {

    @Autowired
    TestUserMapper testUserMapper;

    private static Logger logger= Logger.getLogger(TestService.class);

    public TestUser getUserById(int userId){
        return this.testUserMapper.selectByPrimaryKey(userId);
    }

    public String testLog(){
        logger.error("Did it again!");   //error级别的信息，参数就是你输出的信息
        logger.info("我是info信息");    //info级别的信息
        logger.debug("我是debug信息");
        logger.warn("我是warn信息");
        logger.fatal("我是fatal信息");
        logger.log(Level.DEBUG, "我是debug信息");   //这个就是制定Level类型的调用：谁闲着没事调用这个，也不一定哦！
        return "hello";
    }

    public static void main(String[] args){
        TestService testService=new TestService();
        String str=testService.testLog();
        logger.info(str);
    }
}
