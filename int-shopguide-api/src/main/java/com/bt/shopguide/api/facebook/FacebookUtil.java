package com.bt.shopguide.api.facebook;

import com.bt.shopguide.api.util.HttpClientHelper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by caiting on 2017/10/30.
 */
@Service
public class FacebookUtil {
    @Value("${share.facebook.tooken}")
    private String token;
    @Value("${share.facebook.apiversion}")
    private String version;
    @Value("${share.facebook.host}")
    private String host;
    @Value("${share.facebook.user.id}")
    private String userId;



    public String sharePhoto(String photoUrl,String caption){
        Map param = new HashMap();
        param.put("access_token",token);
        param.put("url",photoUrl);
        param.put("caption",caption);
        String result = HttpClientHelper.getInstance().doPost(host+"/"+this.version+"/"+userId+"/photos",param);
        return result;
    }

    public static void main(String[] args) {
        FacebookUtil fu = new FacebookUtil();
        fu.token = "EAAS3KvljZAhkBAAZAd4umKx54dZAPBUkJgfRciX2UCbu4ccHbJa7Crzjyx2irL4B37v1aFmjzZATLp6bvjBKNcAy6UtomdoqTpQZCaOW6pOLWpgvDZAfjAvxaMbR4FksZCirqQorU1fI52EQF9e426b04HBTZCGGjZCXHR2DnyPremAZDZD";
        fu.version= "v2.10";

        fu.host= "https://graph.facebook.com";
        fu.userId= "280251415819072";

        System.out.println(fu.sharePhoto("http://b4.photo.store.qq.com/psu?/15b945c2-e6f4-41ff-b5c1-2bf3229a248c/ugjsQL3Jd1pLjh1PEGVsHZ4MFVD5FzTYAnAM.9C8zh8!/b/YUoQ2QE*0AAAYqqUbgJkyQAA&a=3&b=4&bo=WAIgAwAAAAABAF4!&rf=viewer_4","回顾2008奥运会"));
    }
}
