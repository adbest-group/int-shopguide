package com.bt.shopguide.api.controller.jsonp;

import com.bt.shopguide.api.system.GlobalVariable;
import com.bt.shopguide.api.vo.JsonResult;
import com.bt.shopguide.api.vo.JsonResultArray;
import com.bt.shopguide.dao.entity.User;
import com.bt.shopguide.dao.entity.UserAction;
import com.bt.shopguide.dao.service.IUserActionService;
import com.bt.shopguide.dao.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.UUID;

/**
 * Created by caiting on 2017/10/17.
 */
@RequestMapping(value = "/behavior")
@Controller
public class BehaviorController {

    @Autowired
    private IUserService userService;
    @Autowired
    private IUserActionService userActionService;

    @ResponseBody
    @RequestMapping(value = "add")
    public JsonResult addBehavior(HttpServletRequest request,
                                  @RequestParam(value = "type",required = false) Integer type,
                                  @RequestParam(value = "url",required = false) String url,
                                  @RequestParam(value = "goodId",required = false) Long goodId,
                                  @RequestParam(value = "key",required = false) String keyword){
        JsonResult jsonResult = new JsonResult();

        HttpSession session = request.getSession();
        User u = (User)session.getAttribute("USER_ID");
        if(u!=null) {
            UserAction userAction = new UserAction();
            userAction.setUserId(u.getId());
            userAction.setOperateType(type);
            userAction.setPageUrl(url);
            userAction.setGoodId(goodId);
            userAction.setKeyword(keyword);
            userAction.setOperateTime(new Date());
            userAction.setCreateTime(new Date());
            try {
                userActionService.save(userAction);
            } catch (Exception e) {
                jsonResult.setCode(-1);
                jsonResult.setMsg("save action faild!");
            }
        }

        return  jsonResult;
    }

    @RequestMapping(value = "cookie")
    public String cookie(HttpServletRequest request,HttpServletResponse response){
        Cookie userCookie = null;
        //标识是否新访问者
        boolean  flag = true;

        Cookie[] cookies = request.getCookies();
        if(cookies!=null&&cookies.length>0) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("cookie_id")) {
                    userCookie = cookie;
                    flag = false;
                    break;
                }
            }
        }

        if(userCookie == null){
            userCookie = new Cookie("cookie_id", UUID.randomUUID().toString().replaceAll("-",""));
            userCookie.setPath("/api");
            userCookie.setMaxAge(999999999);
//            userCookie.setMaxAge(-1);

            response.addCookie(userCookie);
        }

        //新访问者
        User u = null;
        if(flag){
            String ua = request.getHeader("User-Agent");
            u = new User();
            u.setCookie(userCookie.getValue());
            u.setDeviceId(ua);
            u.setIp(request.getRemoteAddr());
            u.setCreateTime(new Date());
            userService.save(u);
        }else{
            u = userService.selectByCookie(userCookie.getValue());
        }

        User su = (User)request.getSession().getAttribute("USER_ID");
        if(su == null){
            request.getSession().setAttribute("USER_ID",u);
        }

        return "forward:/blank.gif";
    }
}
