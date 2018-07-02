package com.sfbest.www.service.impl.service;

import com.alibaba.fastjson.JSON;
import com.sfbest.account.comment.api.CommentsForPcService;
import com.sfbest.account.comment.api.CommentsForProductService;
import com.sfbest.www.service.util.LogUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.concurrent.Callable;

@Service
public class CommentService {
    @Autowired
    CommentsForPcService commentPc;
    @Autowired
    CommentsForProductService commentProduct;

    /**
     * 获取热门晒单
     */
    public Object getHotComment(){
        try {
            String retStr = commentPc.getFourteenHotPost();
            Map mapData = JSON.parseObject(retStr, Map.class);
            if((Integer)mapData.get("code") == 0){
                return mapData.get("data");
            }else{
                throw new Exception("Return Error [getFourteenHotPost]");
            }
        }catch (Exception e){
            LogUtil.getLogger(LogUtil.LoggerName.HESSIAN).error(e.getMessage());
            return null;
        }
    }
    /**
     * 评论点赞
     * @param {
     * commentId int 评论ID
     * userId    int 用户ID
     * }
     * @return 0、成功，1、已点赞，-1、失败
     */
    public int likeComment(Map param){
        try{
            String retStr = commentPc.saveCommentLike(JSON.toJSONString(param));
            Map mapData = JSON.parseObject(retStr,Map.class);
            if((Integer)mapData.get("code") == 0){
                return 0;
            }else if((Integer)mapData.get("code") == 100013156){
                return 1;
            }else{
                return -1;
            }
        }catch(Exception e){
            LogUtil.getLogger(LogUtil.LoggerName.HESSIAN).error(e.getMessage());
            return -1;
        }
    }
    /**
     * 获取评论数量统计
     * @param param{
     * productId int 商品ID
     * }
     */
    public Object getCommentCount(Map param){
        try{
            String jsonStr = commentProduct.getCommentsDifNum(JSON.toJSONString(param));
            Map mapData = JSON.parseObject(jsonStr,Map.class);
            if((Integer)mapData.get("code") == 0){
                return mapData.get("data");
            }else{
                throw new Exception("Return error!");
            }
        }catch(Exception e){
            LogUtil.getLogger(LogUtil.LoggerName.HESSIAN).error(e.getMessage());
            return null;
        }
    }
    /**
     * 获取一条最新好评
     * @param param{
     * productId int 商品ID
     * }
     */
    public Object getOneGoodComment(Map param){
        try{
            String jsonStr = commentProduct.getOneOfLatestComments(JSON.toJSONString(param));
            Map mapData = JSON.parseObject(jsonStr,Map.class);
            if((Integer) mapData.get("code") == 0){
                return mapData.get("data");
            }else{
                throw new Exception("Return error!");
            }
        }catch (Exception e){
            LogUtil.getLogger(LogUtil.LoggerName.HESSIAN).error(e.getMessage());
            return null;
        }
    }
    /**
     * 获取评论列表
     * @param param{
     * page int 页码
     * pageSize int 条数
     * searchConditionList {
     *         {name:productId,value:_},{name:selected,value:_}[,{name:userId,value:_}]
     *     }
     * }
     */
    public Object getCommentList(Map param){
        try {
            String jsonStr = commentProduct.getCommentsList(JSON.toJSONString(param));
            Map mapData = JSON.parseObject(jsonStr,Map.class);
            if((Integer) mapData.get("code") == 0){
                return mapData.get("data");
            }else{
                throw new Exception("Return Error");
            }
        } catch (Exception e) {
            LogUtil.getLogger(LogUtil.LoggerName.HESSIAN).error(e.getMessage());
            return null;
        }
    }
}
