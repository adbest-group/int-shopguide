package com.bt.shopguide.dao.service;

import com.bt.shopguide.dao.entity.GoodCheap;
import com.bt.shopguide.dao.vo.PageDataVo;

/**
 * Created by caiting on 2017/12/29.
 */
public interface IGoodCheapService {
    public int save(GoodCheap goodCheap);
    public void selectGoodCheapPage(PageDataVo<GoodCheap> vo);
    public void selectGoodCheapPageOrderById(PageDataVo<GoodCheap> vo);
}
