package com.bt.shopguide.collector.executor;

import com.bt.shopguide.dao.entity.Coupon;
import com.bt.shopguide.dao.entity.GoodCheap;
import com.bt.shopguide.dao.service.ICouponService;
import com.bt.shopguide.dao.service.IGoodCheapService;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created by caiting on 2017/11/23.
 */

/** {
 "taskId": 1019300718,
 "seedId": 1398025,
 "createTime": "2018-01-03T09:36:09Z",
 "crawlTime": "2018-01-03T09:39:17Z",
 "category": "makeup",
 "title": "尚之源 玻尿酸身体乳液补水保湿浴后持久淡香全身护理润肤乳香体",
 "price": "  9.9",
 "listImage": "https://ofjn5tuqf.qnssl.com/ee0385e42eb71cf89f44296b56f709e6.jpg?imageView2/2/w/400/h/400",
 "pubtime": "8小时前",
 "buyButton": "去购买",
 "buyButtonLink": "https://uland.taobao.com/coupon/edetail?e=du0%2F8%2FmbXf9t3vqbdXnGlu%2B6wc%2F43l4tMnw6cTBQvWwTJdgBbMuyNrcZelJt%2BzjyxvZrgEGCAf%2F1k%2BTC7xR6p2S4ihT3PrBb19fIGjTfiD5E%2BZBrlIcbSTN21B8Js3M3wTq9C65hdIhi0UgrFBehNod3Pxw5H%2BO5&pid=mm_26322749_4094016_66006518&af=1",
 "pid": "6d2bfd9c-8485-4f76-81df-c9ab0f430473",
 "coupon": "领20元券",
 "cate": "彩妆护肤",
 "docId": "1398025.6d2bfd9c-8485-4f76-81df-c9ab0f430473"
 }
 */
@Service
public class GuangdiuCheapJsonExecutor extends AbstractJsonExecutor {
    private static org.slf4j.Logger logger = LoggerFactory.getLogger(GuangdiuCheapJsonExecutor.class);

    @Autowired
    private IGoodCheapService goodCheapService;

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    public void execute(String json) {
        JsonObject obj = new JsonParser().parse(json).getAsJsonObject();

        String pid = "int_shopguide_goods_guangdiu_cheap_"+obj.get("title").getAsString();
        logger.info("coupon cache key:{}",new Object[]{pid});
        if(redisTemplate.opsForValue().get(pid)!=null){
            logger.info("guangdiu cheap-{} has collected!",new Object[]{obj.get("couponId")});
            return;
        }

        GoodCheap gc = new GoodCheap();
        try {
            gc.setTitle((obj.get("title")==null||obj.get("title").equals(JsonNull.INSTANCE))?"":obj.get("title").getAsString());
            gc.setListImage((obj.get("listImage")==null||obj.get("listImage").equals(JsonNull.INSTANCE))?"":obj.get("listImage").getAsString());
            gc.setPrice((obj.get("price")==null||obj.get("price").equals(JsonNull.INSTANCE))?"":obj.get("price").getAsString());
            gc.setUrl((obj.get("buyButtonLink")==null||obj.get("buyButtonLink").equals(JsonNull.INSTANCE))?"":obj.get("buyButtonLink").getAsString());
            gc.setCoupon((obj.get("coupon")==null||obj.get("coupon").equals(JsonNull.INSTANCE))?"":obj.get("coupon").getAsString());
            gc.setCategory((obj.get("cate")==null||obj.get("cate").equals(JsonNull.INSTANCE))?"":obj.get("cate").getAsString());
            gc.setSyncTime(sdf.parse(obj.get("crawlTime").getAsString().replaceAll("T"," ").replaceAll("Z","")));
            gc.setCreateTime(new Date());
            if(goodCheapService.save(gc)>0){
                redisTemplate.opsForValue().set(pid,"1",timeout, TimeUnit.DAYS);
            }else{
                logger.error("save coupon faild!");
            }
        } catch (ParseException e) {
            logger.error("parse expire date faild!");
        } catch (Exception e){
            logger.error("save coupon faild with exception :" + e);
        }
    }
}
