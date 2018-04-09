package com.cn.common;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * User: zhongrf
 * Date: 2018/3/26 15:56
 * Description:
 */
public class MatchDateRex {
    /**
     * (1)能匹配的年月日类型有：
     *    2014年4月19日
     *    2014年4月19号
     *    2014-4-19
     *    2014/4/19
     *    2014.4.19
     *    20140419
     * (2)能匹配的时分秒类型有：
     *    15:28:21
     *    15:28
     *    5:28 pm
     *    15点28分21秒
     *    15点28分
     *    15点
     * (3)能匹配的年月日时分秒类型有：
     *    (1)和(2)的任意组合，二者中间可有任意多个空格
     * 如果dateStr中有多个时间串存在，只会匹配第一个串，其他的串忽略
     * @param dateStr
     * @return
     */
    private static String matchDateString(String dateStr) {
        try {
            List matches = new ArrayList();
//            Pattern p = Pattern.compile("(\\d{1,4}[-|\\/|年|\\.]\\d{1,2}[-|\\/|月|\\.]\\d{1,2}([日|号])?(\\s)*(\\d{1,2}([点|时])?((:)?\\d{1,2}(分)?((:)?\\d{1,2}(秒)?)?)?)?(\\s)*(PM|AM)?)", Pattern.CASE_INSENSITIVE|Pattern.MULTILINE);
            Pattern p = Pattern.compile("(\\d{1,4}[-]\\d{1,2}[-]\\d{1,2})", Pattern.CASE_INSENSITIVE|Pattern.MULTILINE);
            Matcher matcher = p.matcher(dateStr);
//            if (matcher.find() && matcher.groupCount() >= 1) {
//                matches = new ArrayList();
//                for (int i = 1; i <= matcher.groupCount(); i++) {
//                    String temp = matcher.group(i);
//                    matches.add(temp);
//                }
//            } else {
//                matches = Collections.EMPTY_LIST;
//            }
            String[] array=dateStr.split("\\d{1,4}[-]\\d{1,2}[-]\\d{1,2}");
            while (matcher.find()) {
                String temp = matcher.group();
                matches.add(temp);
            }
            if (matches.size() > 0) {
                return ((String) matches.get(0)).trim();
            } else {
            }
        } catch (Exception e) {
            return "";
        }

        return dateStr;
    }

    /**
     *
     * @param args
     */
    public static void main(String[] args) {

        String iSaid = "亲爱的，20140101 15时36分21秒， 我会在世贸天阶向你求婚！等到2015-06-25，我们就结婚。";

        // 匹配时间串
        String answer = matchDateString(iSaid);

        // 输出：
        // 问：请问我们什么时候结婚？
        // 答：2014年4月25 15时36分21秒
        System.out.println("问：请问我们什么时候结婚？");
        System.out.println("答：" + answer);
        System.out.println(answer.length());

    }
}
