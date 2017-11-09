package com.cn.controller;

import com.cn.common.SysConstant;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.util.WebUtils;


import javax.servlet.http.HttpServletRequest;
import javax.xml.registry.infomodel.User;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * Created by 钟锐锋 on 2017/9/5.
 */
public class BaseController {
    /**
     * 使用格式不要忘了 {}
     * logger.info("123123{}","123123231");
     * 如果没有 {}，逗号后面的值不显示
     */
    protected final Logger logger=Logger.getLogger(BaseController.class);
    @Autowired
    private HttpServletRequest request;

//    public User getUserCurrent() {
//        Object obj = WebUtils.getSessionAttribute(this.request, SysConstant.CURRENR_USER);
//        return (User) obj;
//    }

    //    只能获取1条数据
    public Map<String, Object> getParamMap() {
        Map<String, Object> result = new LinkedHashMap<>();
        LinkedHashMap<String, String[]> map = new LinkedHashMap<>(request.getParameterMap());
        for (Map.Entry<String, String[]> entry : map.entrySet()) {
            String key = entry.getKey();
            String[] values = entry.getValue();
            result.put(key, values[0]);
        }
        return result;
    }

    /**
     * 获取前端通过 multipart/form-data 提交文件时附带的参数
     * Enumeration<?>代表可以传递任意类型，?是通配符即Object及其下的子类，也就是java的所有对象了。
     * request.getParameterNames()方法是将发送请求页面中form表单里所有具有name属性的表单对象获取(包括button).返回一个Enumeration类型的枚举.
     * 通过Enumeration的hasMoreElements()方法遍历.再由nextElement()方法获得枚举的值.此时的值是form表单中所有控件的name属性的值.
     * 最后通过request.getParameter()方法获取表单控件的value值.
     * 先获得变量mane再获得其值，对于getParameterName（）其值是变量/对象的名称，getParameterValue（）获得的是变量/对象的值。
     * request.getParameterValues("name")方法将获取所有form表单中name属性为"name"的值.该方法返回一个数组.遍历数组就可得到value值.
     * request.getParameterNames()的值是无序排列request.getParameterValues()是按照form表单的控件顺序排列.
     * @return
     */
    public Map<String, Object> requestParams(HttpServletRequest req) {
        Map<String, Object> paramsMap = new HashMap<String, Object>();
        if (req == null) {
            return paramsMap;
        }
        Enumeration<?> paramNames = req.getParameterNames();
        if(paramNames!=null&&paramNames.hasMoreElements()){
            while (paramNames.hasMoreElements()){
                String paramName=(String)paramNames.nextElement();
                String[] paramValues=req.getParameterValues(paramName);
                if(paramValues.length==1){
                    paramsMap.put(paramName,paramValues[0]);
                }else{
                    paramsMap.put(paramName, ArrayUtils.toString(paramValues));
                }
            }
        }
        return paramsMap;
    }

    /**
     * 当有日期传进来时，如果要用到entity，则需要进行日期转换，否则报错
     *
     * @param binder 另一种简单的方法是：
     *               在entity中
     * @DateTimeFormat(pattern = "yyyy-MM-dd")
     * private Date createTime;
     */
    @InitBinder
    public void InitBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));   //true:允许输入空值，false:不能为空值
    }

}
