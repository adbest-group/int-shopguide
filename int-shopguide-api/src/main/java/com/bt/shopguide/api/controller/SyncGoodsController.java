package com.bt.shopguide.api.controller;

import com.bt.shopguide.api.vo.JsonResult;
import com.bt.shopguide.dao.entity.*;
import com.bt.shopguide.dao.service.IGoodCheapService;
import com.bt.shopguide.dao.service.IGoodsDetailService;
import com.bt.shopguide.dao.service.IGoodsListService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.lang.reflect.Type;
import java.util.List;

/**
 * Created by caiting on 2017/10/11.
 */
@Controller
@RequestMapping(value = "/sync")
public class SyncGoodsController {
    private Logger logger = Logger.getLogger(SyncGoodsController.class);

    @Autowired
    IGoodsDetailService goodsDetailService;
    @Autowired
    IGoodsListService goodsListService;
    @Autowired
    IGoodCheapService goodCheapService;

    @ResponseBody
    @RequestMapping(value = "goods")
    public JsonResult syncGoods(@RequestParam(value = "json",required = false) String goodsJson){
        JsonResult jsonResult = new JsonResult();
        if(goodsJson==null){
            jsonResult.setCode(-1);
            jsonResult.setMsg("not enougth param!");
            return jsonResult;
        }
        Gson gson = new Gson();
        List<GoodsListWithHtml> goods;
        Type type = new TypeToken<List<GoodsListWithHtml>>() {}.getType();
        try {
            goods = gson.fromJson(goodsJson,type);
        }catch(Exception e){
            jsonResult.setCode(-1);
            jsonResult.setMsg("parse error");
            return jsonResult;
        }
        for(GoodsListWithHtml goodHtml : goods){
            GoodsList glist = new GoodsList();
            GoodsDetail gdetail = new GoodsDetail();

            glist.setTitle(goodHtml.getTitle());
            glist.setSmallImageUrl(goodHtml.getSmallImageUrl());
            glist.setPrice(goodHtml.getPrice());
            glist.setOriginalPrice(goodHtml.getOriginalPrice());
            glist.setDiscounts(goodHtml.getDiscounts());
            glist.setCoupons(goodHtml.getCoupons());
            glist.setPromoCode(goodHtml.getPromoCode());
            glist.setCategory(goodHtml.getCategory());
            glist.setMallName(goodHtml.getMallName());
            glist.setGoodSourceName(goodHtml.getGoodSourceName());
            glist.setShortContent(goodHtml.getShortContent());
            glist.setUrl(goodHtml.getUrl());
            glist.setThumbs(Math.abs(goodHtml.getThumbs()));
            glist.setPublish(goodHtml.getPublish());
            glist.setNation(goodHtml.getNation());
            glist.setSyncTime(goodHtml.getSyncTime());
            glist.setCreateTime(goodHtml.getCreateTime());
            try{
                goodsListService.save(glist);
                try{
                    gdetail.setGoodsId(glist.getId());
                    gdetail.setContentHtml(goodHtml.getContentHtml());
                    gdetail.setCreateTime(goodHtml.getCreateTime());
                    goodsDetailService.save(gdetail);
                }catch(Exception e){
                    logger.error("save goods_detail faild!original goods_id is["+goodHtml.getId()+"]");
                    continue;
                }
            }catch(Exception e){
                logger.error("save goods_list faild!original id is["+goodHtml.getId()+"]");
                continue;
            }
        }
        return  jsonResult;
    }

    @ResponseBody
    @RequestMapping(value = "cheaps")
    public JsonResult syncCoupons(@RequestParam(value = "json",required = false) String cheapJson){
        JsonResult jsonResult = new JsonResult();
        if(cheapJson==null){
            jsonResult.setCode(-1);
            jsonResult.setMsg("not enougth param!");
            return jsonResult;
        }
        Gson gson = new Gson();
        List<GoodCheap> cheaps;
        Type type = new TypeToken<List<GoodCheap>>() {}.getType();
        try {
            cheaps = gson.fromJson(cheapJson,type);
        }catch(Exception e){
            jsonResult.setCode(-1);
            jsonResult.setMsg("parse error");
            return jsonResult;
        }
        for(GoodCheap cheap : cheaps){
            try{
                goodCheapService.save(cheap);

            }catch(Exception e){
                logger.error("save coupon faild!original id is["+cheap.getId()+"]");
                continue;
            }
        }
        return  jsonResult;
    }
}
