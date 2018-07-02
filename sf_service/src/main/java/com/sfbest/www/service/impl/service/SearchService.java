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
     * @param cid int 商品分类ID
     */
    public Object getBrandByCid(int cid){
        try{
            Map<String,Integer> mapCid = new HashMap<>();
            mapCid.put("cid",cid);
            String str = URLDecoder.decode(searcher.brandByCid(JSON.toJSONString(mapCid)), "UTF-8");
            Map mapType = JSON.parseObject(str,Map.class);
            if(mapType.containsKey("result")){
                return mapType;
            }else{
                throw new Exception("Not exists key:result [brandByCid]");
            }
        }catch (Exception e){
            LogUtil.getLogger(LogUtil.LoggerName.HESSIAN).error(e.getMessage());
            return null;
        }
    }

    /**
     * 发现优品
     * @param areaid int 三级地址ID
     */
    public Object getGoodProduct(int areaid){
        try{
            Map<String,Integer> mapArea = new HashMap<>();
            mapArea.put("threeRegion",areaid);
            String str = URLDecoder.decode(searcher.searchBottomList(JSON.toJSONString(mapArea)), "UTF-8");
            Map mapType = JSON.parseObject(str,Map.class);
            if(mapType.containsKey("result")){
                return mapType;
            }else{
                throw new Exception("Not exists key:result [searchBottomList]");
            }
        }catch (Exception e){
            LogUtil.getLogger(LogUtil.LoggerName.HESSIAN).error(e.getMessage());
            return null;
        }
    }
    /**
     * 过滤商品库存
     * @param mapParam {
     * productIds   array       商品ID集合,
     * threeRegion  int         三级地址ID,
     * fourRegion   -1          四级地址ID,
     * isStock      Boolean     是否判断库存,
     * reachable    Boolean     是否判断可送达,
     * sortable     Boolean     按照队列顺序排队,
     * size=100
     * }
     */
    public Object getFilterProduct(Map<String,Object> mapParam){
        try{
            String str = URLDecoder.decode(searcher.searchProductList(JSON.toJSONString(mapParam)), "UTF-8");
            Map mapType = JSON.parseObject(str,Map.class);
            if(mapType.containsKey("result")){
                return mapType;
            }else{
                throw new Exception("Not exists key:result [searchProductList]");
            }
        }catch (Exception e){
            LogUtil.getLogger(LogUtil.LoggerName.HESSIAN).error(e.getMessage());
            return null;
        }
    }
    /**
     * 获取关联关键词
     * @param word string 关键词
     */
    public Object getSuggest(String word){
        try{
            Map<String,String> mapArea = new HashMap<>();
            mapArea.put("q",word);
            String str = URLDecoder.decode(searcher.suggest(JSON.toJSONString(mapArea)), "UTF-8");
            Map mapType = JSON.parseObject(str,Map.class);
            if(mapType.containsKey("suggestion")){
                return mapType;
            }else{
                throw new Exception("Not exists key:result [suggest]");
            }
        }catch (Exception e){
            LogUtil.getLogger(LogUtil.LoggerName.HESSIAN).error(e.getMessage());
            return null;
        }
    }
    /**
     * 商品搜索
     * @param conditions{
     * activityId   int     活动ID
     * actType      int     活动类型
     * attr         String  属性字符串
     * brandId      int     品牌ID
     * categoryId   int     分类ID
     * channelCodes array   设备对应渠道数组
     * countryId    int     国家ID
     * couponTypeId int     优惠券批次ID
     * deviceId     int     优惠券设备ID[PC:50]
     * endPrice     int     结束价格
     * fourRegion   int     四级地址ID
     * inputBox     int     是否从搜索来
     * isActivity   int     1、促销
     * isBook       Boolean 是否需要支持预定
     * isCOD        Boolean 否需要支持货到付款
     * isOversea    int     0、非海淘,1、海淘,不传、全部
     * isPromote    Boolean 是否需要正在促销中
     * isreach      Boolean 是否可配送
     * isReturn     Boolean 是否需要支持退货
     * isstock      Boolean 是否有货
     * keyword      String  搜索关键字
     * ltrType      int     0、非智能排序,1、智能排序
     * o            String  排序字符串
     * oneRegion    int     一级地址ID
     * pageNo       int     页码,0开始
     * pageSize     int     条数
     * priceRangeId int     价格区间ID
     * productAreaId        产地
     * scopeId      int     设备ID[PC:50]
     * searchId     String  页面埋点
     * startPrice   int     开始价格
     * threeRegion  int     三级地址ID
     * twoRegion    int     二级地址ID
     * userId       int     用户ID
     * }
     */
    public Object getSearch(Map<String,Object> conditions){
        try{
            String str = URLDecoder.decode(searcher.search(JSON.toJSONString(conditions)), "UTF-8");
            Map mapType = JSON.parseObject(str,Map.class);
            if(mapType.containsKey("result")){
                return mapType;
            }else{
                throw new Exception("Not exists key:result [search]");
            }
        }catch (Exception e){
            LogUtil.getLogger(LogUtil.LoggerName.HESSIAN).error(e.getMessage());
            return null;
        }
    }
}
