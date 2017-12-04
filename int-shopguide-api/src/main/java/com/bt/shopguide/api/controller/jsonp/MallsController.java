package com.bt.shopguide.api.controller.jsonp;

import com.bt.shopguide.api.system.GlobalVariable;
import com.bt.shopguide.api.vo.JsonResult;
import com.bt.shopguide.api.vo.JsonResultArray;
import com.bt.shopguide.dao.service.IMallsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by caiting on 2017/9/29.
 */

@RequestMapping(value="/malls")
@RestController
public class MallsController {

    @RequestMapping(value = "")
    @ResponseBody
    public JsonResult getAllMalls(@RequestParam(value = "nation",required = false) String nation){
        JsonResult jsonResult = new JsonResult();
        JsonResultArray jra = new JsonResultArray();
        if("us".equalsIgnoreCase(nation)){
            jra.setList(GlobalVariable.us_malls);
        }else {
            jra.setList(GlobalVariable.malls);
        }
        jsonResult.setResult(jra);
        return  jsonResult;
    }
}
