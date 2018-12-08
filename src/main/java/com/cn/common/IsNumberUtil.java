package com.cn.common;

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * User: zhongrf
 * Date: 2018/5/28 10:11
 * Description:判断是否为数字
 */
public class IsNumberUtil {

    public static void main(String[] args) {
        String str="-1234";
        isNumeric(str);
        isNum(str);
    }

    /**
     * 匹配是否为数字
     * @param str 可能为中文，也可能是-19162431.1254，不使用BigDecimal的话，变成-1.91624311254E7
     * @return
     * @author yutao
     * @date 2016年11月14日下午7:41:22
     */
    public static boolean isNumeric(String str) {
        // 该正则表达式可以匹配所有的数字 包括负数
        Pattern pattern = Pattern.compile("-?[0-9]+(\\.[0-9]+)?");
        String bigStr;
        try {
            bigStr = new BigDecimal(str).toString();
        } catch (Exception e) {
            return false;//异常 说明包含非数字。
        }

        Matcher isNum = pattern.matcher(bigStr); // matcher是全匹配
        if (!isNum.matches()) {
            return false;
        }
        return true;
    }

    /**
     * 新思路
     * 如果是数字，创建new BigDecimal()时肯定不会报错，那就可以直接返回true啦！
     * @param str
     * @return
     */
    public static boolean isNum(String str) {
        String bigStr;
        try {
            bigStr = new BigDecimal(str).toString();
        } catch (Exception e) {
            return false;//异常 说明包含非数字。
        }
        return true;
    }
}