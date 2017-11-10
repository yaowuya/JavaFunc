package com.cn.dao.data;

import com.cn.common.annotation.DataSourceType;
import org.springframework.stereotype.Repository;

/**
 * Created by 钟锐锋 on 2017/11/10.
 */
@Repository
public interface DataSourceDao {
    @DataSourceType("javafunc")
    int count(String name);
}
