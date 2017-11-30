package com.bt.shopguide.api.twitter;

import com.bt.shopguide.api.util.HttpClientHelper;
import org.springframework.stereotype.Service;
import twitter4j.*;

import java.io.ByteArrayInputStream;

/**
 * Created by caiting on 2017/11/20.
 */
@Service
public class TwitterUtil {

    public void tweet(String imgUrl,String content){
        Status status = null;
        try {
            Twitter twitter = TwitterFactory.getSingleton();
            StatusUpdate st = new StatusUpdate(content);
            byte[] img = HttpClientHelper.getInstance().getStaticResourceInputStream(imgUrl);
            if(img != null)
                st.setMedia("", new ByteArrayInputStream(img));
            status = twitter.updateStatus(st);
        } catch (TwitterException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
//        System.out.println("test app");
//        Twitter twitter = TwitterFactory.getSingleton();
//        Status status = null;
////        UploadedMedia media = null;
//        try {
//            StatusUpdate st = new StatusUpdate("$579.99 ($779.99, 26% off) Apple 10.5-Inch iPad Pro with Cellular 64GB (Verizon).Link Here: http://www.dealswill.com/detail/29028.");
//            byte[] img = HttpClientHelper.getInstance().getStaticResourceInputStream("http://imgcache.dealmoon.com/thumbimg.dealmoon.com/ugc/d95/7f6/bac/4a71689737090ff91d930e7.jpg_300_0_13_504b.jpg");
//            st.setMedia("", new ByteArrayInputStream(img));
//            status = twitter.updateStatus(st);
////            media = twitter.uploadMedia;
//
//        } catch (TwitterException e) {
//            e.printStackTrace();
//        }
//        System.out.println("Successfully updated the status to [" + status.getText() + "].");
    }
}
