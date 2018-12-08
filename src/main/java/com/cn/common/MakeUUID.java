package com.cn.common;

import java.util.Random;
import java.util.UUID;

/**
 * User: zhongrf
 * Date: 2018/5/8 16:25
 * Description:
 */
public class MakeUUID {

    public static void main(String[] args) {
        getUUID();
        System.out.println(getUUIDByRules("zxcvbnmlkjhgfdsaqwertyuiopQWERTYUIOPASDFGHJKLZXCVBNM1234567890"));
    }

    /**
     * 生成32位编码
     * @return string
     */
    public static String getUUID(){
        String uuid = UUID.randomUUID().toString().trim().replaceAll("-", "");
        System.out.println(UUID.randomUUID().toString().trim());
        return uuid;
    }

    /**
     * 自定义规则生成32位编码
     * @return string
     */
    public static String getUUIDByRules(String rules)
    {
        int rpoint = 0;
        StringBuffer generateRandStr = new StringBuffer();
        Random rand = new Random();
        int length = 32;
        for(int i=0;i<length;i++)
        {
            if(rules!=null){
                rpoint = rules.length();
                int randNum = rand.nextInt(rpoint);
                generateRandStr.append(rules.substring(randNum,randNum+1));
            }
        }
        return generateRandStr+"";
    }
}