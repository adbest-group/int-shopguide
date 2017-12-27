package com.bt.shopguide.api.weibo;

import com.bt.shopguide.api.util.HttpClientHelper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import weibo4j.Timeline;
import weibo4j.http.ImageItem;
import weibo4j.model.*;
import java.io.UnsupportedEncodingException;

/**
 * Created by caiting on 2017/11/20.
 */
@Service
public class WeiboUtil {

    @Value("${share.weibo.access_token}")
    private String access_token;

    public void share(String imgUrl,String content){
        Status status = null;
        try {

            byte[] img = HttpClientHelper.getInstance().getStaticResourceInputStream(imgUrl);
            ImageItem pic = new ImageItem("pic", img);
            String s = java.net.URLEncoder.encode(content, "utf-8");
            Timeline tm = new Timeline(access_token);
            status = tm.shareStatus(s, pic);

            System.out.println("Successfully upload the status to ["
                    + status.getText() + "].");
        } catch (WeiboException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
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

        new WeiboUtil().share("https://ofjn5tuqf.qnssl.com/64a636149679a6b7e105cfd81631964b.jpg?imageView2/2/w/224/h/224","24.9元 KIDORABLE 创意 双层反向伞 点击：http://www.abigpush.com/detail/42391");
    }
}
