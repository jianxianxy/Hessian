package com.sfbest.www.service.impl.service;

import com.alibaba.fastjson.JSON;
import com.sfbest.www.service.util.LogUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sf.search.remote.ArticleHessianSearcher;

import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

@Service
public class ArticleSearchService {
    @Autowired
    private ArticleHessianSearcher articleSearcher;

    /**
     * 文章搜索
     * @param param [
     * keyword  String  关键词
     * columnId int     栏目ID
     * pageSize int     每页条数
     * pageNo   int     页码0开始
     * ]
     */
    public Object getSearch(Map param){
        try{
            String retStr = URLDecoder.decode(articleSearcher.search(JSON.toJSONString(param)),"UTF-8");
            Map mapType = JSON.parseObject(retStr,Map.class);
            return mapType;
        }catch (Exception e){
            LogUtil.getLogger(LogUtil.LoggerName.HESSIAN).error(e.getMessage());
            return null;
        }
    }
    /**
     * 数量
     * @param param [
     * keyword  string  关键词
     * ]
     */
    public Object getSearchCount(Map param){
        try{
            String retStr = URLDecoder.decode(articleSearcher.searchCount(JSON.toJSONString(param)),"UTF-8");
            Map mapType = JSON.parseObject(retStr,Map.class);
            return mapType;
        }catch (Exception e){
            LogUtil.getLogger(LogUtil.LoggerName.HESSIAN).error(e.getMessage());
            return null;
        }
    }
    /**
     * 获取关联关键词
     * @param param [
     * q    string  关键词
     * ]
     */
    public Object getSuggest(Map param){
        try{
            String retStr = URLDecoder.decode(articleSearcher.suggest(JSON.toJSONString(param)),"UTF-8");
            Map mapType = JSON.parseObject(retStr,Map.class);
            return mapType;
        }catch (Exception e){
            LogUtil.getLogger(LogUtil.LoggerName.HESSIAN).error(e.getMessage());
            return null;
        }
    }
}
