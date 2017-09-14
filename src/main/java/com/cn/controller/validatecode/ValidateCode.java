package com.cn.controller.validatecode;

import com.cn.common.JsonResult;
import com.cn.common.VerifyCode;
import com.cn.controller.BaseController;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by 钟锐锋 on 2017/9/13.
 */
@Controller
@RequestMapping("/ValidateCode")
public class ValidateCode extends BaseController{
    private static Logger logger=Logger.getLogger(ValidateCode.class);
    @Autowired
    HttpServletRequest request;

    /**
     * VerifyCode 生成验证码
     * @param response
     * @param session
     * @return
     */
    @ResponseBody
    @RequestMapping("/verifycode")
    public String VerifyCode(HttpServletResponse response, HttpSession session) {
//        产生验证码
        VerifyCode vc = new VerifyCode();
        BufferedImage image = vc.getImage();
        session.setAttribute("session_vCode", vc.getText());
        try {
            VerifyCode.output(image, response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 登录认证认证码
     * @param session
     * @return
     */
    @ResponseBody
    @RequestMapping("/checkVerify")
    public JsonResult login(HttpSession session) {
//        验证验证码是否正确
        JsonResult jsonResult = null;
        String VerifyCode = "";
        Map<String, Object> param = super.getParamMap();
        Iterator iter = param.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            if (entry.getKey().equals("checkCode")) {
                VerifyCode = (String) entry.getValue();
            }
//            System.out.println(entry.getKey()+"  "+(String)entry.getValue());
        }
        String vc_code = (String) session.getAttribute("session_vCode");
        if (!vc_code.equalsIgnoreCase(VerifyCode)) {
            jsonResult = new JsonResult(false, "验证码错误");
        } else {
            jsonResult = new JsonResult(true, "验证码正确");
        }
        return jsonResult;
    }
}
