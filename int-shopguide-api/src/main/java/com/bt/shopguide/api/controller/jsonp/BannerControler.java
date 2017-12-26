package com.bt.shopguide.api.controller.jsonp;

import com.bt.shopguide.api.vo.JsonResult;
import com.bt.shopguide.api.vo.JsonResultArray;
import com.bt.shopguide.dao.service.IBannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by caiting on 2017/12/19.
 */
@RequestMapping(value="/banner")
@RestController
public class BannerControler {
    @Autowired
    private IBannerService bannerService;

    @RequestMapping(value = "")
    @ResponseBody
    public JsonResult getBanner(@RequestParam(value = "type",required = false,defaultValue = "1")String type,
                                @RequestParam(value = "count",required = false,defaultValue = "5")Integer count){
        JsonResult jsonResult = new JsonResult();
        JsonResultArray jra = new JsonResultArray();
        jra.setList(bannerService.getBanner(type,count));
        jsonResult.setResult(jra);
        return  jsonResult;
    }
}
