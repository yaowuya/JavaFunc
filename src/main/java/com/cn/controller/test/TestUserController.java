package com.cn.controller.test;

import com.cn.entity.test.TestUser;
import com.cn.service.test.TestService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by 钟锐锋 on 2017/9/4.
 */
@Controller
@RequestMapping("/testUser")
public class TestUserController {
    @Autowired
    private TestService testService;

    @RequestMapping("/showUser")
    public String toIndex(HttpServletRequest request, Model model){
        int userId = 1;
        TestUser user = this.testService.getUserById(userId);
        model.addAttribute("user", user);
        return "test/showUser";
    }

    public static void main(String[] args) {
        try {

            Date dateObj=null;
            System.out.println("date:"+changeToString(dateObj));

            Date date1=new Date();
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
            Date date2=sdf.parse(sdf.format(date1));
            System.out.println(date2);
            Integer a=5;
            Integer b=13;
            double result = new BigDecimal((float)a / b).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
            System.out.println(result);
            String date = "2017-07-17 11:03:52";
            System.out.println("compareToBefore1 : "+date.compareTo("2017-06-16 11:03:52"));
            System.out.println("compareToBefore2 : "+date.compareTo("2017-05-16 11:03:52"));
            System.out.println("compareToNow1 : "+date.compareTo("2017-07-17 11:03:52"));
            System.out.println("compareToNow2 : "+date.compareTo("2017-07-17"));
            System.out.println("compareToAfter1 : "+date.compareTo("2017-07-18 11:03:52"));
            System.out.println("compareToAfter2 : "+date.compareTo("2017-09-16 11:03:52"));

            System.out.println(a.compareTo(b));

            Map<String,String> map=new HashMap<>();
            test1(map);
            test2(map);

            System.out.println(map);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void test1(Map<String,String> map1){
        map1.put("ab","cd");
        test3(map1);
    }

    public static String changeToString(Object obj){
        return (obj==null)?"":obj.toString();
    }

    public static void test2(Map<String,String> map2){
        map2.put("12","34");
    }

    public static void test3(Map<String,String> map2){
        map2.put("3a","3b");
    }
}
