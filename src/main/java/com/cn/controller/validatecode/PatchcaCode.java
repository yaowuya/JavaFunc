package com.cn.controller.validatecode;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.PrintWriter;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.patchca.color.ColorFactory;
import org.patchca.filter.predefined.CurvesRippleFilterFactory;
import org.patchca.filter.predefined.DiffuseRippleFilterFactory;
import org.patchca.filter.predefined.DoubleRippleFilterFactory;
import org.patchca.filter.predefined.MarbleRippleFilterFactory;
import org.patchca.filter.predefined.WobbleRippleFilterFactory;
import org.patchca.service.ConfigurableCaptchaService;
import org.patchca.utils.encoder.EncoderHelper;
import org.patchca.word.RandomWordFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;

/**
 * 使用谷歌提供的验证码组件  kaptcha和从网上找的验证码组件  patchca来生成验证码
 * 需要手动下载patchca-0.5.0.jar
 * Created by 钟锐锋 on 2017/9/13.
 */
@Controller
@RequestMapping("/PatchcaCode")
public class PatchcaCode {
    @Autowired
    private Producer captchaProducer = null;  //kaptcha

    private static ConfigurableCaptchaService cs = new ConfigurableCaptchaService();

    private static Random random = new Random();


    static{

        cs.setColorFactory(new ColorFactory() {

            public Color getColor(int x) {

                int [] c={25, 60, 170};
                int i=random.nextInt(c.length);
                for(int fi = 0;fi < c.length; fi++)
                {

                    if(fi==i)
                    {
                        c[fi]=random.nextInt(71);
                    }else
                    {
                        c[fi]=random.nextInt(256);
                    }
                }
                return new Color(c[0],c[1],c[2]);
            }
        });

        RandomWordFactory wf=new RandomWordFactory();
        wf.setCharacters("23456789abcdefghijkmnpqrstuvwxyzABCDEFGHIJKMNPQRSTUVWXYZ");
        wf.setMaxLength(4);
        wf.setMinLength(4);
        cs.setWordFactory(wf);
    }

    protected void setResponseHeaders( HttpServletResponse response) {
        response.setContentType("image/jpeg");
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        long time=System.currentTimeMillis();
        response.setDateHeader("Last-Modified", time);
        response.setDateHeader("Date", time);
        response.setDateHeader("Expires", time);
    }
    @RequestMapping("getpatchcaImage")
    public void getpatchcaImage(HttpServletRequest request, HttpServletResponse response) throws Exception {

        switch (random.nextInt(5)) {
            case 0:
                cs.setFilterFactory(new CurvesRippleFilterFactory(cs.getColorFactory()));
                break;
            case 1:
                cs.setFilterFactory(new MarbleRippleFilterFactory());
                break;
            case 2:
                cs.setFilterFactory(new DoubleRippleFilterFactory());
                break;

            case 3:
                cs.setFilterFactory(new WobbleRippleFilterFactory());
                break;

            case 4:
                cs.setFilterFactory(new DiffuseRippleFilterFactory());
                break;

        }

        HttpSession session=request.getSession(false);
        if(session==null)
        {
            session=request.getSession();
        }
        setResponseHeaders(response);
        String token=EncoderHelper.getChallangeAndWriteImage(cs, "png", response.getOutputStream());
        session.setAttribute("captchaToken", token);
        System.out.println("当前的 sessionId:"+session.getId()+",验证码："+token );

    }

    /**
     * @author jinggujia
     * @Description: 谷歌验证码
     * @param @param request
     * @param @param response
     * @param @return
     * @param @throws Exception  参数说明
     * @return ModelAndView    返回类型
     * @throws
     */
    @RequestMapping("getKaptchaImage")
    public ModelAndView getKaptchaImage(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        String code = (String)session.getAttribute(Constants.KAPTCHA_SESSION_KEY);
        System.out.println("验证码: " + code );

        response.setDateHeader("Expires", 0);
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
        response.setHeader("Pragma", "no-cache");
        response.setContentType("image/jpeg");

        String capText = captchaProducer.createText();
        session.setAttribute(Constants.KAPTCHA_SESSION_KEY, capText);

        BufferedImage bi = captchaProducer.createImage(capText);
        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(bi, "jpg", out);
        try {
            out.flush();
        } finally {
            out.close();
        }
        return null;
    }


    /**
     * @author jinggujia
     * @Description: 用来校验通过过谷歌验证码组件生成的验证码与前台用输入的验证码是否正确
     * @param @param res
     * @param @param resp
     * @param @param out  参数说明
     * @return void    返回类型
     * @throws
     */
    public void checkCode(HttpServletRequest res, HttpServletResponse resp,PrintWriter out)
    {

        boolean flag=false;
        String authcode=res.getParameter("code");
        String code = (String) res.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);        //获取生成的验证码
        System.out.println(authcode + "," + code);
        if((code.toUpperCase()).equals(authcode.toUpperCase()))
        {
            flag= true;
        }
        else
        {
            flag=false;
        }
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("text/plain;charset=utf-8");
        //向叶面输出数据
        try {
            out.write(String.valueOf(flag));
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * @author jinggujia
     * @Description:
     * @param @param res
     * @param @param resp
     * @param @param out  参数说明
     * @return void    返回类型
     * @throws
     */
    public void validateCode(HttpServletRequest res, HttpServletResponse resp,PrintWriter out)
    {
        boolean flag=false;
        String authcode=res.getParameter("code");
        String code = (String) res.getSession().getAttribute("captchaToken");        //获取生成的验证码
        System.out.println(authcode + "," + code);
        if((code.toUpperCase()).equals(authcode.toUpperCase()))
        {
            flag= true;
        }
        else
        {
            flag=false;
        }
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("text/plain;charset=utf-8");
        //向叶面输出数据
        try {
            out.write(String.valueOf(flag));
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
