package com.sfbest.www.service.impl.service;

import com.alibaba.fastjson.JSON;
import com.sf.search.remote.HessianSearcher;
import com.sfbest.www.service.util.LogUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

@Service
public class SearchService {
    @Autowired
    private HessianSearcher searcher;

    /**
     * 根据分类ID获取相关品牌
     * @param cid 商品分类ID
     */
    public Object getBrandByCid(int cid){
        try{
            Map<String,Integer> mapCid = new HashMap<>();
            mapCid.put("cid",cid);
            String str = URLDecoder.decode(searcher.brandByCid(JSON.toJSONString(mapCid)), "UTF-8");
            Map mapType = JSON.parseObject(str,Map.class);
            if(mapType.containsKey("result")){
                Map brandList = (Map) mapType.get("result");
                return brandList.get("brandList");
            }else{
                throw new Exception("Not exists key:result");
            }
        }catch (Exception e){
            LogUtil.getLogger(LogUtil.LoggerName.HESSIAN).error(e.getMessage());
            return null;
        }
    }

    /**
     * 发现优品
     * @param areaid 三级地址ID
     */
    public Object getGoodProduct(int areaid){
        try{
            Map<String,Integer> mapArea = new HashMap<>();
            mapArea.put("threeRegion",areaid);
            String str = URLDecoder.decode(searcher.searchBottomList(JSON.toJSONString(mapArea)), "UTF-8");
            Map mapType = JSON.parseObject(str,Map.class);
            if(mapType.containsKey("result")){
                Map productList = (Map) mapType.get("result");
                return productList.get("ProductList");
            }else{
                throw new Exception("Not exists key:result");
            }
        }catch (Exception e){
            LogUtil.getLogger(LogUtil.LoggerName.HESSIAN).error(e.getMessage());
            return null;
        }
    }
    /**
     * 过滤商品库存
     * @param mapParam [
     * productIds 商品ID集合,
     * threeRegion 三级地址ID,
     * fourRegion -1 四级地址ID,
     * isStock Boolean 是否判断库存,
     * reachable Boolean 是否判断可送达,
     * sortable Boolean 按照队列顺序排队,
     * size=100
     * ]
     */
    public Object getFilterProduct(Map<String,Object> mapParam){
        try{
            String str = URLDecoder.decode(searcher.searchProductList(JSON.toJSONString(mapParam)), "UTF-8");
            Map mapType = JSON.parseObject(str,Map.class);
            if(mapType.containsKey("result")){
                Map brandList = (Map) mapType.get("result");
                return brandList.get("ProductList");
            }else{
                throw new Exception("Not exists key:result");
            }
        }catch (Exception e){
            LogUtil.getLogger(LogUtil.LoggerName.HESSIAN).error(e.getMessage());
            return null;
        }
    }
    /**
     * 获取关联关键词
     * @param word 关键词
     */
    public Object getSuggest(String word){
        try{
            Map<String,String> mapArea = new HashMap<>();
            mapArea.put("q",word);
            String str = URLDecoder.decode(searcher.suggest(JSON.toJSONString(mapArea)), "UTF-8");
            Map mapType = JSON.parseObject(str,Map.class);
            if(mapType.containsKey("suggestion")){
                return mapType.get("suggestion");
            }else{
                throw new Exception("Not exists key:result");
            }
        }catch (Exception e){
            LogUtil.getLogger(LogUtil.LoggerName.HESSIAN).error(e.getMessage());
            return null;
        }
    }
}
