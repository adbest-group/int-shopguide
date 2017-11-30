package com.bt.shopguide.api.task;

import com.bt.shopguide.api.system.GlobalVariable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Created by caiting on 2017/10/12.
 */
@Component
public class RefreshCache {

    @Autowired
    private GlobalVariable globalVariable;

    //刷新商城，分类，热词等涣散村数据 5分钟一次
    @Scheduled(cron = "0 0/10 * * * ?")
    public void refresh(){
        globalVariable.loadMalls();
        globalVariable.loadCategorys();
        globalVariable.loadHotwords();
    }

    //刷新top10商品，半小时一次
    @Scheduled(cron = "0 0/30 * * * ?")
    public void refreshTop10Goods(){
        globalVariable.loadGoodTop10();
    }
}
