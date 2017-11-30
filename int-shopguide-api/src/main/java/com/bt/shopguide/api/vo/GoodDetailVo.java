package com.bt.shopguide.api.vo;

import com.bt.shopguide.dao.entity.GoodsListWithHtml;
import org.springframework.beans.factory.annotation.Value;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.Date;

/**
 * Created by caiting on 2017/9/29.
 */
public class GoodDetailVo implements Serializable {
    private Long goodId;
    private String title;
    private String smallImageUrl;
    private String price;
    private String originalPrice;
    private String discounts;
    private String coupons;
    private String promoCode;
    private String mallName;
    private String goodSourceName;
    private String shortContent;
    private String url;
    private Integer thumbs;
    private String contentHtml;
    private Date syncTime;

    private GoodDetailVo(){}
    public GoodDetailVo(GoodsListWithHtml entity,String charset){
        this.goodId = entity.getId();
        this.title = entity.getTitle();
        this.smallImageUrl = entity.getSmallImageUrl();
        this.price = entity.getPrice();
        this.originalPrice = entity.getOriginalPrice();
        this.discounts = entity.getDiscounts();
        this.coupons = entity.getCoupons();
        this.promoCode = entity.getPromoCode();
        this.mallName = entity.getMallName();
        this.goodSourceName = entity.getGoodSourceName();
        this.shortContent = entity.getShortContent();
        this.url = entity.getUrl();
        this.thumbs = entity.getThumbs();
        this.syncTime = entity.getSyncTime();
        try {
            this.contentHtml = new String(entity.getContentHtml(),charset);
        } catch (UnsupportedEncodingException e) {
            this.contentHtml="";
        }

    }

    public Long getGoodId() {
        return goodId;
    }

    public void setGoodId(Long goodId) {
        this.goodId = goodId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSmallImageUrl() {
        return smallImageUrl;
    }

    public void setSmallImageUrl(String smallImageUrl) {
        this.smallImageUrl = smallImageUrl;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(String originalPrice) {
        this.originalPrice = originalPrice;
    }

    public String getDiscounts() {
        return discounts;
    }

    public void setDiscounts(String discounts) {
        this.discounts = discounts;
    }

    public String getCoupons() {
        return coupons;
    }

    public void setCoupons(String coupons) {
        this.coupons = coupons;
    }

    public String getPromoCode() {
        return promoCode;
    }

    public void setPromoCode(String promoCode) {
        this.promoCode = promoCode;
    }

    public String getMallName() {
        return mallName;
    }

    public void setMallName(String mallName) {
        this.mallName = mallName;
    }

    public String getGoodSourceName() {
        return goodSourceName;
    }

    public void setGoodSourceName(String goodSourceName) {
        this.goodSourceName = goodSourceName;
    }

    public String getShortContent() {
        return shortContent;
    }

    public void setShortContent(String shortContent) {
        this.shortContent = shortContent;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getContentHtml() {
        return contentHtml;
    }

    public void setContentHtml(String contentHtml) {
        this.contentHtml = contentHtml;
    }

    public Date getSyncTime() {
        return syncTime;
    }

    public void setSyncTime(Date syncTime) {
        this.syncTime = syncTime;
    }

    public Integer getThumbs() {
        return thumbs;
    }

    public void setThumbs(Integer thumbs) {
        this.thumbs = thumbs;
    }
}
