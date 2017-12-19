package com.bt.shopguide.dao.service.imp;

import com.bt.shopguide.dao.entity.Banner;
import com.bt.shopguide.dao.mapper.BannerMapper;
import com.bt.shopguide.dao.service.IBannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by caiting on 2017/12/19.
 */
@Service
public class BannerService implements IBannerService {
    @Autowired
    private BannerMapper bannerMapper;

    @Override
    public List<Banner> getBanner(String type, Integer n) {
        return bannerMapper.getBanner(type,n);
    }
}
