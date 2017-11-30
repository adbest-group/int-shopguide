package com.bt.shopguide.api.system;

import com.bt.shopguide.dao.entity.Category;
import com.bt.shopguide.dao.entity.GoodsList;
import com.bt.shopguide.dao.entity.Hotword;
import com.bt.shopguide.dao.entity.Malls;
import com.bt.shopguide.dao.service.ICategoryService;
import com.bt.shopguide.dao.service.IGoodsListService;
import com.bt.shopguide.dao.service.IHotwordService;
import com.bt.shopguide.dao.service.IMallsService;
import com.bt.shopguide.dao.vo.PageDataVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.util.resources.cldr.te.CalendarData_te_IN;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by caiting on 2017/9/28.
 */
@Service
public class GlobalVariable {
    Logger logger = LoggerFactory.getLogger(GlobalVariable.class);

    @Autowired
    private IMallsService mallsService;
    @Autowired
    private IHotwordService hotwordService;
    @Autowired
    private IGoodsListService goodsListService;
    @Autowired
    private ICategoryService categoryService;
    //所有商城列表
    public static List<Malls> malls = new ArrayList<>();
    //top10商品
    public static List<GoodsList> goods_list_top10 = new ArrayList<>();
    //热词列表
    public static List<Hotword> hotwords = new ArrayList<>();
    //分类列表
    public static List<Category> categorys = new ArrayList<>();

    public void init(){
        logger.info("初始化全局变量开始！~~~~~~~~~~~~~~~");

        loadMalls();
        loadHotwords();
        loadCategorys();

        loadGoodTop10();


        logger.info("初始化全局变量完成！~~~~~~~~~~~~~~~");
    }

    //刷新商城
    public void loadMalls(){
        List<Malls> tmpMalls = mallsService.getTopN(8);
        malls = tmpMalls;
        logger.info("刷新商城完成！~~~~~~~~~~~~~~~");
    }

    //刷新热词
    public void loadHotwords(){
        List<Hotword> tmpHotwords = hotwordService.getTop5();
        hotwords = tmpHotwords;
        logger.info("刷新热词完成！~~~~~~~~~~~~~~~");
    }

    //刷新分类
    public void loadCategorys(){
        List<Category> tmp = categoryService.getTopN(10);
        categorys = tmp;
        logger.info("刷新分类完成！~~~~~~~~~~~~~~~");
    }

    //刷新top10商品
    public void loadGoodTop10(){
        PageDataVo<GoodsList> vo = new PageDataVo<>();
        vo.setPageSize(10);
        goodsListService.selectGoodsListPage(vo);
        if(vo.getData()!=null&&vo.getData().size()>0){
            goods_list_top10 = vo.getData();
        }
        logger.info("刷新top10商品完成！~~~~~~~~~~~~~~~");
    }
}
