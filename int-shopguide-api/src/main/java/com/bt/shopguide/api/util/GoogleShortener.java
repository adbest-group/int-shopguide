package com.bt.shopguide.api.util;

import com.google.gson.Gson;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;

/**
 * Created by caiting on 2017/11/3.
 */
@Service
public class GoogleShortener {
    @Value("${google.shorten.apiurl}")
    private String apiUrl;
    @Value("${google.shorten.key}")
    private String apiKey;

    public String shorten(String url){
        if(url==null) return null;
        String shortUrl = null;
        String json = "{\"longUrl\": \""+url+"\"}";

        HttpClientHelper http = HttpClientHelper.getInstance();
        CloseableHttpClient httpClient = http.getConnection();

        try {
            HttpPost postRequest = new HttpPost(apiUrl+"?key="+apiKey);
            postRequest.setHeader("Content-Type", "application/json");
            postRequest.setEntity(new StringEntity(json, "UTF-8"));

            HttpResponse response = httpClient.execute(postRequest);
            String responseText = EntityUtils.toString(response.getEntity());

            Gson gson = new Gson();
            @SuppressWarnings("unchecked")
            HashMap<String, String> res = gson.fromJson(responseText, HashMap.class);
            shortUrl = res.get("id");
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return shortUrl;
    }
}
