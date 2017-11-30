package com.bt.shopguide.api.controller.jsonp;

import com.bt.shopguide.api.system.GlobalVariable;
import com.bt.shopguide.api.vo.JsonResult;
import com.bt.shopguide.api.vo.JsonResultArray;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by caiting on 2017/10/10.
 */
@RequestMapping(value="/hotwords")
@RestController
public class HotwordsController {

    @RequestMapping(value = "")
    @ResponseBody
    public JsonResult getHotwords(){
        JsonResult jsonResult = new JsonResult();
        JsonResultArray jra = new JsonResultArray();
        jra.setList(GlobalVariable.hotwords);
        jsonResult.setResult(jra);
        return  jsonResult;
    }
}
