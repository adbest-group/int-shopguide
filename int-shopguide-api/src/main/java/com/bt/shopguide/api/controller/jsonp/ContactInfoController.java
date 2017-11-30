package com.bt.shopguide.api.controller.jsonp;

import com.bt.shopguide.api.system.GlobalVariable;
import com.bt.shopguide.api.vo.JsonResult;
import com.bt.shopguide.api.vo.JsonResultArray;
import com.bt.shopguide.dao.entity.ContactInfo;
import com.bt.shopguide.dao.service.IContactInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.util.Date;

/**
 * Created by caiting on 2017/10/26.
 */
@RequestMapping(value="/contact")
@RestController
public class ContactInfoController {
    @Autowired
    private IContactInfoService contactInfoService;
    @RequestMapping(value = "/add")
    public JsonResult save(ContactInfo contactInfo){
        JsonResult jsonResult = new JsonResult();
        int nameLength = 0;
        int emailLength = 0;
        int phoneLength = 0;
        int contentLength = 0 ;
        try {
            nameLength = contactInfo.getName().getBytes("utf-8").length;
        } catch (Exception e) {
            nameLength = 0;
        }
        try {
            emailLength = contactInfo.getEmail().getBytes("utf-8").length;
        } catch (Exception e) {
            emailLength = 0;
        }
        try {
            phoneLength = contactInfo.getPhone().getBytes("utf-8").length;
        } catch (Exception e) {
            phoneLength = 0;
        }
        try {
            contentLength = contactInfo.getContent().getBytes("utf-8").length;
        } catch (Exception e) {
            contentLength = 0;
        }
        if(contactInfo.getName()!=null && nameLength > 0 && nameLength>50){
            jsonResult.setCode(-1);
            jsonResult.setMsg(jsonResult.getMsg()==null?"error name!":jsonResult.getMsg()+"error name!");
        }
        if(contactInfo.getEmail()==null||emailLength ==0 ||emailLength>50){
            jsonResult.setCode(-1);
            jsonResult.setMsg(jsonResult.getMsg()==null?"error email!":jsonResult.getMsg()+"error email!");
        }
        if(contactInfo.getPhone()!=null && phoneLength > 0 && phoneLength>20){
            jsonResult.setCode(-1);
            jsonResult.setMsg(jsonResult.getMsg()==null?"error phone!":jsonResult.getMsg()+"error phone!");
        }
        if(contactInfo.getContent()==null||contentLength ==0 ||contentLength>2000){
            jsonResult.setCode(-1);
            jsonResult.setMsg(jsonResult.getMsg()==null?"error content!":jsonResult.getMsg()+"error content!");
        }
        if(jsonResult.getCode() == 0){
            contactInfo.setCreateTime(new Date());
            try{
                contactInfoService.save(contactInfo);
            }catch(Exception e){
                jsonResult.setCode(-1);
                jsonResult.setMsg("save contact faild!");
            }

        }

        return  jsonResult;
    }

//    public static void main(String[] args) throws Exception{
//        System.out.println("你好".getBytes("utf-8").length);
//    }
}
