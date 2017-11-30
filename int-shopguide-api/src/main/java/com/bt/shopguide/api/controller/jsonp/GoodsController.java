package com.bt.shopguide.api.controller.jsonp;

import com.bt.shopguide.api.system.GlobalVariable;
import com.bt.shopguide.api.vo.GoodDetailVo;
import com.bt.shopguide.api.vo.JsonResult;
import com.bt.shopguide.api.vo.JsonResultArray;
import com.bt.shopguide.api.vo.JsonResultArrayWithPage;
import com.bt.shopguide.dao.entity.GoodsList;
import com.bt.shopguide.dao.entity.GoodsListWithHtml;
import com.bt.shopguide.dao.service.IGoodsListService;
import com.bt.shopguide.dao.vo.PageDataVo;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by caiting on 2017/9/29.
 */
@RestController
public class GoodsController {

    @Value("${page.pageSize}")
    private int pageSize = 20;
    @Value("${project.charset}")
    private String charset;

    @Autowired
    IGoodsListService goodsListService;

    /**
     *商品列表
     **/
    @ResponseBody
    @RequestMapping(value = "/goods")
    public JsonResult getGoodsList(@RequestParam(value = "page",defaultValue = "1") Integer pageIndex,
                                   @RequestParam(value = "mall",required = false) String mallName,
                                   @RequestParam(value = "category",required = false) String category,
                                   @RequestParam(value = "id",required = false) Long id,
                                   @RequestParam(value = "sync",required = false) Long syncTime){
        JsonResult result = new JsonResult();
        JsonResultArrayWithPage jrap = new JsonResultArrayWithPage();

        //组装分页组件
        PageDataVo<GoodsList> vo = new PageDataVo<>();
        vo.setPageIndex(pageIndex);
        vo.setPageSize(pageSize);
        Map<String,Object> condition = Maps.newHashMap();
        if(mallName!=null) condition.put("mallName",mallName);
        if(category!=null) condition.put("category",category);
        if(id!=null) condition.put("id",id);
        if(syncTime!=null) condition.put("syncTime",new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(syncTime)));
        vo.setConditionMap(condition);
        goodsListService.selectGoodsListPage(vo);

        //获取分页数据
        jrap.setPageIndex(pageIndex);
        jrap.setPageSize(pageSize);
        jrap.setTotalCount(vo.getTotalCount());
        jrap.setList(vo.getData());

        //设置当前第一条数据的id和syncTime
        if(vo.getTotalCount()>0) {
            if (pageIndex == 1) {
                GoodsList gl = (GoodsList) vo.getData().get(0);
                jrap.setMaxId(gl.getId());
                jrap.setMaxSyncTime(gl.getSyncTime());
            } else {
                //组装分页组件
                PageDataVo<GoodsList> v1 = new PageDataVo<>();
                v1.setPageIndex(1);
                v1.setPageSize(1);
                Map<String, Object> condition1 = Maps.newHashMap();
                if (mallName != null) condition1.put("mallName", mallName);
                if (category != null) condition1.put("category", category);
                if (id != null) condition1.put("id", id);
                if (syncTime != null)
                    condition1.put("syncTime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(syncTime)));
                v1.setConditionMap(condition1);
                goodsListService.selectGoodsListPage(v1);
                GoodsList gl = (GoodsList) v1.getData().get(0);
                jrap.setMaxId(gl.getId());
                jrap.setMaxSyncTime(gl.getSyncTime());
            }
        }

        result.setResult(jrap);
        return result;
    }

    /**
     *搜索商品列表
     **/
    @ResponseBody
    @RequestMapping(value = "/search")
    public JsonResult getGoodsList(@RequestParam(value = "page",defaultValue = "1") Integer pageIndex,
                                   @RequestParam(value = "key",required = false) String keyword){
        JsonResult result = new JsonResult();
        JsonResultArrayWithPage jrap = new JsonResultArrayWithPage();

        //组装分页组件
        PageDataVo<GoodsList> vo = new PageDataVo<>();
        vo.setPageIndex(pageIndex);
        vo.setPageSize(pageSize);
        Map<String,Object> condition = Maps.newHashMap();
        if(keyword!=null) condition.put("keyword",keyword);
        vo.setConditionMap(condition);
        goodsListService.selectGoodsListPage(vo);

        //获取分页数据
        jrap.setPageIndex(pageIndex);
        jrap.setPageSize(pageSize);
        jrap.setTotalCount(vo.getTotalCount());
        jrap.setList(vo.getData());

        result.setResult(jrap);
        return result;
    }

    /**
     *top10商品列表
     **/
    @ResponseBody
    @RequestMapping(value = "/hotgoods")
    public JsonResult getGoodsTop10(){
        JsonResult result = new JsonResult();
        JsonResultArray jrap = new JsonResultArray();
        jrap.setList(GlobalVariable.goods_list_top10);
        result.setResult(jrap);
        return result;
    }

    @ResponseBody
    @RequestMapping("/goodsdetail")
    public JsonResult getGoodDetail(@RequestParam(value = "id",required = false) Long id){
        JsonResult result = new JsonResult();
        if(id==null){
            result.setCode(-1);
            result.setMsg("paramter id is necessary!");
        }else{
            try {
                GoodsListWithHtml good = goodsListService.selectGoodDetailByGoodsId(id);
                result.setResult(new GoodDetailVo(good,charset));
            }catch(Exception e){
                result.setCode(-1);
                result.setMsg("query faild!");
            }
        }
        return result;
    }

    @ResponseBody
    @RequestMapping("/goods/thumbs")
    public JsonResult thumbs(@RequestParam(value = "id",required = false) Long id){
        JsonResult result = new JsonResult();
        if(id==null){
            result.setCode(-1);
            result.setMsg("paramter id is necessary!");
        }else{
            try {
                goodsListService.increaseThumbs(id,1);
                result.setResult(goodsListService.getGoodsListById(id));
            }catch(Exception e){
                e.printStackTrace();
                result.setCode(-1);
                result.setMsg("thumbs faild!");
            }
        }
        return result;
    }

    /**
     *根据商品id获取推荐可能喜欢的商品
     * 定义为商品相同category下的随机四种商品
     **/
    @ResponseBody
    @RequestMapping(value = "/goods/maybelike")
    public JsonResult getMayBeLike(@RequestParam(value = "id",required = false)Long id){
        JsonResult result = new JsonResult();
        if(id==null){
            result.setCode(-1);
            result.setMsg("paramter id is necessary!");
        }else{
            GoodsList gl = goodsListService.getGoodsListById(id);
            if(gl!=null){
                JsonResultArray jrap = new JsonResultArray();
                if(gl.getCategory()==null){
                    jrap.setList(new ArrayList<GoodsList>());
                }else{
                    jrap.setList(goodsListService.getRandGoodsByCategory(gl.getCategory(),4));
                }
                result.setResult(jrap);
            }else {
                result.setCode(-1);
                result.setMsg("invilid id!");
            }
        }
        return result;
    }

    /**
     *根据商品id获取同商城热门的商品
     * 定义为商品相同商城下的随机四种商品
     **/
    @ResponseBody
    @RequestMapping(value = "/goods/mallhot")
    public JsonResult getMallHot(@RequestParam(value = "id",required = false)Long id){
        JsonResult result = new JsonResult();
        if(id==null){
            result.setCode(-1);
            result.setMsg("paramter id is necessary!");
        }else{
            GoodsList gl = goodsListService.getGoodsListById(id);
            if(gl!=null){
                JsonResultArray jrap = new JsonResultArray();
                if(gl.getMallName()==null){
                    jrap.setList(new ArrayList<GoodsList>());
                }else{
                    List<GoodsList> list = goodsListService.getRandGoodsByMall(gl.getMallName(),gl.getId(),4);
                    jrap.setList(list);
                }
                result.setResult(jrap);
            }else {
                result.setCode(-1);
                result.setMsg("invilid id!");
            }
        }
        return result;
    }
}
