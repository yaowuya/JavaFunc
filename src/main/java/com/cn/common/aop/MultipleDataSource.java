package com.cn.common.aop;

import com.cn.common.utils.EncryptUtil;
import com.cn.entity.data.MyDataSource;
import org.apache.commons.dbcp.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;
import javax.xml.crypto.Data;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 钟锐锋 on 2017/11/9.
 */
public class MultipleDataSource extends AbstractRoutingDataSource {
    private static Logger logger= LoggerFactory.getLogger(MultipleDataSource.class);
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private BasicDataSource defaultDataSource;

    private static final ThreadLocal<String> dataSourceKey=new ThreadLocal<>();
    /*全部数据源*/
    private static Map<String,DataSource> allMyDataSourceMap;

    static {
        allMyDataSourceMap=new HashMap<>();
    }

    /**
     * 返回哪个数据源的ID，就用哪个数据源
     * @return
     */
    @Override
    protected Object determineCurrentLookupKey() {
        return dataSourceKey.get();
    }
    //选择具体的库
    public static void setDataSourceKey(String dataSourceId){
        dataSourceKey.set(dataSourceId);
    }
    //删除线程变量，自动切换为默认的库
    public static void restore(){
        dataSourceKey.remove();
    }

    @Override
    public void afterPropertiesSet(){
        logger.info("初始化多数据源》》》");
        super.setTargetDataSources(getAllDataSource());
        super.afterPropertiesSet();
    }

    /**
     * 同步数据源
     */
    public void syncDataSource(){
        this.afterPropertiesSet();
    }

    /**
     * 私有该方法，避免加载多个数据源从而造成过多的数据库连接
     * @return
     */
    private Map<Object,Object> getAllDataSource(){
        logger.info("加载数据源》》》》》》》》》》》》》》》》》");
        String sql="select id,name,type,rac as isRAC,url,port,`database`," +
                "username,`password` from datasource";
        List<MyDataSource> dataSourceList=jdbcTemplate.query(sql,new BeanPropertyRowMapper<MyDataSource>(MyDataSource.class));
        Map<Object,Object> myDataSourceMap=new HashMap<>();
        for(MyDataSource ds:dataSourceList){
            ds.setPassword(ds.getPassword());
            ds.initialMyDataSource();
            myDataSourceMap.put(ds.getName(),ds);
            logger.info("获取数据源id{},name{}",ds.getId(),ds.getName());
        }
        this.setAllMyDataSourceMap(myDataSourceMap);
        return myDataSourceMap;
    }

    public static Map<String, DataSource> getAllMyDataSourceMap() {
        return allMyDataSourceMap;
    }

    private void setAllMyDataSourceMap(Map<Object, Object> myDataSourceMap) {
        for(Map.Entry<Object,Object> entry:myDataSourceMap.entrySet()){
             allMyDataSourceMap.put((String)entry.getKey(),(DataSource)entry.getValue());
        }
        allMyDataSourceMap.put("默认数据源",defaultDataSource);
    }

    public static ThreadLocal<String> getDataSourceKey() {
        return dataSourceKey;
    }
}
