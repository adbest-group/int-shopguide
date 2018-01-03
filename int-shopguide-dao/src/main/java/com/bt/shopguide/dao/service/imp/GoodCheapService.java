package com.bt.shopguide.dao.service.imp;

import com.bt.shopguide.dao.entity.GoodCheap;
import com.bt.shopguide.dao.mapper.GoodCheapMapper;
import com.bt.shopguide.dao.service.IGoodCheapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by caiting on 2017/12/29.
 */
@Service
public class GoodCheapService implements IGoodCheapService{

    @Autowired
    private GoodCheapMapper goodCheapMapper;

    @Override
    public int save(GoodCheap goodCheap) {
        return goodCheapMapper.insert(goodCheap);
    }
}
