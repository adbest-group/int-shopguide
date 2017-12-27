package com.bt.shopguide.api.task;

import com.bt.shopguide.api.twitter.TwitterUtil;
import com.bt.shopguide.api.util.GoogleShortener;
import com.bt.shopguide.dao.entity.GoodsList;
import com.bt.shopguide.dao.service.IGoodsListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by caiting on 2017/11/21.
 */
@Component
public class TwitterPublishTask {
    @Autowired
    private GoogleShortener shortener;
    @Autowired
    private TwitterUtil twitterUtil;
    @Autowired
    private IGoodsListService goodsListService;

    @Value("${project.host}")
    private String host;
    @Value("${share.twitter}")
    private boolean if_share_twitter;

    //每小时发布一条page
    @Scheduled(cron = "0 0 0/2 * * ?")
    public void tweet(){
        if(if_share_twitter) {
            List<GoodsList> list = goodsListService.getRandGoods(1, 10);
            if (list.size() > 0) {
                GoodsList gl = list.get(0);
                String shortUrl = shortener.shorten(host + "/detail/" + gl.getId());
                twitterUtil.tweet(gl.getSmallImageUrl(), (gl.getDiscounts() == null ? (gl.getPrice() == null ? "" : gl.getPrice()) : gl.getDiscounts()) + " " + gl.getTitle() + ". " +
                        "Link Here: " + shortUrl);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        String[] cfgs = new String[]{"classpath:applicationContext.xml"};
        ApplicationContext ctx = new ClassPathXmlApplicationContext(cfgs);
        ((TwitterPublishTask)ctx.getBean("twitterPublishTask")).tweet();
    }
}
