package com.cn.controller.test;

import com.cn.entity.test.TestUser;
import com.cn.service.test.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

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
        int userId = Integer.parseInt(request.getParameter("id"));
        TestUser user = this.testService.getUserById(userId);
        model.addAttribute("user", user);
        return "showUser";
    }
}
