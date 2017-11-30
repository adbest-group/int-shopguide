package com.bt.shopguide.api;

import com.bt.shopguide.api.util.HttpClientHelper;
import org.apache.http.client.methods.HttpGet;
import twitter4j.*;

import java.io.InputStream;

/**
 * Created by caiting on 2017/9/28.
 */
public class Test {

    public static void main(String[] args) {
//        System.out.println("test app");
//        Twitter twitter = TwitterFactory.getSingleton();
//        Status status = null;
////        UploadedMedia media = null;
//        try {
//            StatusUpdate st = new StatusUpdate("$579.99 ($779.99, 26% off) Apple 10.5-Inch iPad Pro with Cellular 64GB (Verizon).Link Here: http://www.dealswill.com/detail/29028.");
//            st.setMedia("",HttpClientHelper.getInstance().getStaticResourceInputStream("http://imgcache.dealmoon.com/thumbimg.dealmoon.com/ugc/d95/7f6/bac/4a71689737090ff91d930e7.jpg_300_0_13_504b.jpg"));
//            status = twitter.updateStatus(st);
////            media = twitter.uploadMedia;
//
//        } catch (TwitterException e) {
//            e.printStackTrace();
//        }
//        System.out.println("Successfully updated the status to [" + status.getText() + "].");

        String str = "<br/>                                                         仅限今日(11月26日)，Amazon.com 现有 GNC 保健品一日特卖,6折";
        System.out.println(str.replaceAll("<br/>","").trim());
    }
}
