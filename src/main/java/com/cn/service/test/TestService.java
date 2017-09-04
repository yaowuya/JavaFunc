package com.cn.service.test;

import com.cn.dao.test.TestUserMapper;
import com.cn.entity.test.TestUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by 钟锐锋 on 2017/9/4.
 */
@Service
public class TestService {
    @Resource
    TestUserMapper testUserMapper;

    public TestUser getUserById(int userId){
        return this.testUserMapper.selectByPrimaryKey(userId);
    }
}
