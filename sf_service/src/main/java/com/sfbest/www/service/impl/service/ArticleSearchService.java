package com.sfbest.www.service.impl.service;

import com.alibaba.fastjson.JSON;
import com.sfbest.www.service.util.LogUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.remote.ArticleHessianSearcher;

import java.net.URLDecoder;
import java.util.Map;

@Service
public class ArticleSearchService {
    @Autowired
    private ArticleHessianSearcher articleSearcher;

    /**
     * 文章搜索
     * @param param {
     * keyword  String  关键词
     * columnId int     栏目ID
     * pageSize int     每页条数
     * pageNo   int     页码0开始
     * }
     */
    public Object getSearch(Map param){
        try{
            String retStr = URLDecoder.decode(articleSearcher.search(JSON.toJSONString(param)),"UTF-8");
            Map mapType = JSON.parseObject(retStr,Map.class);
            if(mapType.containsKey("result")){
                return mapType;
            }else{
                throw new Exception("Not exists key:suggestion [search]");
            }
        }catch (Exception e){
            LogUtil.getLogger(LogUtil.LoggerName.HESSIAN).error(e.getMessage());
            return null;
        }
    }
    /**
     * 数量
     * @param param {
     * keyword  string  关键词
     * }
     */
    public Object getSearchCount(Map param){
        try{
            String retStr = URLDecoder.decode(articleSearcher.searchCount(JSON.toJSONString(param)),"UTF-8");
            Map mapType = JSON.parseObject(retStr,Map.class);
            if(mapType.containsKey("result")){
                return mapType;
            }else{
                throw new Exception("Not exists key:suggestion [searchCount]");
            }
        }catch (Exception e){
            LogUtil.getLogger(LogUtil.LoggerName.HESSIAN).error(e.getMessage());
            return null;
        }
    }
}
