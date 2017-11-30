package com.bt.shopguide.dao.service;

import com.bt.shopguide.dao.entity.Coupon;
import com.bt.shopguide.dao.vo.PageDataVo;

/**
 * Created by caiting on 2017/11/23.
 */
public interface ICouponService {
    public int save(Coupon c);
    public void selectCouponPage(PageDataVo<Coupon> vo);

}
