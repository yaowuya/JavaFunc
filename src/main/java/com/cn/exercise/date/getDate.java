package com.cn.exercise.date;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * User: zhongrf
 * Date: 2018/7/16 10:55
 * Description:
 */
public class getDate {
    public static void main(String[] args){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        SimpleDateFormat sdf=new SimpleDateFormat("dd");
        String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        for(int i=0;i<20;i++){
            Date date=calendar.getTime();
//            System.out.println(sdf.format(date));
            System.out.println(String.valueOf(calendar.get(Calendar.DATE))+" "+weekDays[calendar.get(Calendar.DAY_OF_WEEK)-1]);
            calendar.add(Calendar.DAY_OF_MONTH,1);
        }
    }

    public static Date getNextDay(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        date = calendar.getTime();
        return date;
    }
}