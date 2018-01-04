package com.bt.shopguide.api.controller.jsonp;

import com.bt.shopguide.api.system.GlobalVariable;
import com.bt.shopguide.api.vo.GoodDetailVo;
import com.bt.shopguide.api.vo.JsonResult;
import com.bt.shopguide.api.vo.JsonResultArray;
import com.bt.shopguide.api.vo.JsonResultArrayWithPage;
import com.bt.shopguide.dao.entity.GoodCheap;
import com.bt.shopguide.dao.entity.GoodsList;
import com.bt.shopguide.dao.entity.GoodsListWithHtml;
import com.bt.shopguide.dao.service.IGoodCheapService;
import com.bt.shopguide.dao.service.IGoodsListService;
import com.bt.shopguide.dao.vo.PageDataVo;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by caiting on 2017/9/29.
 */
@RestController
public class GoodCheapController {

    @Value("${page.pageSize}")
    private int pageSize = 20;
    @Value("${project.charset}")
    private String charset;

    @Autowired
    IGoodCheapService goodCheapService;

    /**
     *商品列表
     **/
    @ResponseBody
    @RequestMapping(value = "/cheaps")
    public JsonResult getCheapsList(@RequestParam(value = "page",defaultValue = "1") Integer pageIndex,
                                   @RequestParam(value = "category",required = false) String category){
        JsonResult result = new JsonResult();
        JsonResultArrayWithPage jrap = new JsonResultArrayWithPage();

        //组装分页组件
        PageDataVo<GoodCheap> vo = new PageDataVo<>();
        vo.setPageIndex(pageIndex);
        vo.setPageSize(pageSize);
        Map<String,Object> condition = Maps.newHashMap();
        if(category!=null) condition.put("category",category);
        vo.setConditionMap(condition);
        goodCheapService.selectGoodCheapPage(vo);

        //获取分页数据
        jrap.setPageIndex(pageIndex);
        jrap.setPageSize(pageSize);
        jrap.setTotalCount(vo.getTotalCount());
        jrap.setList(vo.getData());

        result.setResult(jrap);
        return result;
    }

}
