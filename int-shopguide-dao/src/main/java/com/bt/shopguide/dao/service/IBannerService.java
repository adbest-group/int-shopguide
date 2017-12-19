package com.bt.shopguide.dao.service;

import com.bt.shopguide.dao.entity.Banner;

import java.util.List;

/**
 * Created by caiting on 2017/12/19.
 */
public interface IBannerService {
    public List<Banner> getBanner(String type,Integer n);
}
