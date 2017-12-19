package com.bt.shopguide.api.controller.jsonp;

import com.bt.shopguide.api.vo.JsonResult;
import com.bt.shopguide.api.vo.JsonResultArray;
import com.bt.shopguide.dao.service.ICouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by caiting on 2017/12/11.
 */
@RequestMapping(value="/coupon")
@RestController
public class CouponController {
    @Autowired
    private ICouponService couponService;

    @RequestMapping(value = "")
    @ResponseBody
    public JsonResult getCoupons(){
        JsonResult jsonResult = new JsonResult();
        JsonResultArray jra = new JsonResultArray();
//        if("us".equalsIgnoreCase(nation)){
//            jra.setList(GlobalVariable.us_categorys);
//        }else {
//            jra.setList(GlobalVariable.categorys);
//        }
        jra.setList(couponService.selectTodayCoupon());
        jsonResult.setResult(jra);
        return  jsonResult;
    }
}
