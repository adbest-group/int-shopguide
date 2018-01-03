package com.bt.shopguide.dao.service.imp;

import com.bt.shopguide.dao.entity.GoodCheap;
import com.bt.shopguide.dao.mapper.GoodCheapMapper;
import com.bt.shopguide.dao.service.IGoodCheapService;
import com.bt.shopguide.dao.vo.PageDataVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

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

    @Override
    public void selectGoodCheapPage(PageDataVo<GoodCheap> vo) {
        int totalCount = goodCheapMapper.getTotalCount(vo.getConditionMap());
        if(totalCount > 0){
            Map<String,Object> params = new HashMap<String,Object>();
            for(Map.Entry<String, Object> entry:vo.getConditionMap().entrySet()){
                params.put(entry.getKey(), entry.getValue());
            }
            params.put("startIndex", (vo.getPageIndex()-1)*vo.getPageSize());
            params.put("pageSize", vo.getPageSize());
            vo.setData(goodCheapMapper.selectPage(params));
            vo.setTotalCount(totalCount);
        }
    }

    @Override
    public void selectGoodCheapPageOrderById(PageDataVo<GoodCheap> vo) {
        int totalCount = goodCheapMapper.getTotalCount(vo.getConditionMap());
        if(totalCount > 0){
            Map<String,Object> params = new HashMap<String,Object>();
            for(Map.Entry<String, Object> entry:vo.getConditionMap().entrySet()){
                params.put(entry.getKey(), entry.getValue());
            }
            params.put("startIndex", (vo.getPageIndex()-1)*vo.getPageSize());
            params.put("pageSize", vo.getPageSize());
            vo.setData(goodCheapMapper.selectPageOrderById(params));
            vo.setTotalCount(totalCount);
        }
    }
}
