package com.sfbest.www.service.impl.service;

import com.alibaba.fastjson.JSON;
import com.sfbest.account.api.addr.AddrService;
import com.sfbest.account.api.user.UserService;
import com.sfbest.www.service.util.LogUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class UserApiService {

    @Autowired
    UserService userService;
    @Autowired
    AddrService userAddr;

    /**
     * 获取头像
     * @param uid String 用户ID
     */
    public Object getHeadPic(String uid){
        try {
            String jsonStr = userService.getHeadPicBatch(uid);
            System.out.println(jsonStr);
            Map mapData = JSON.parseObject(jsonStr,Map.class);
            if((Integer)mapData.get("code") == 0){
                return mapData.get("data");
            }else{
                throw new Exception("Return error.");
            }
        } catch (Exception e) {
            LogUtil.getLogger(LogUtil.LoggerName.HESSIAN).error(e.getMessage());
            return null;
        }
    }

    /**
     * 获取用户积分
     * @param param{
     * userId int 用户ID
     * }
     */
    public Object getAccount(Map param) {
        try {
            String jsonStr = userService.getSfAccountInfo(JSON.toJSONString(param));
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
    /**
     * 投诉与建议
     * @param param{
     * userEmail    String  邮箱
     * userMobile   String  手机
     * workType     int     投诉类型
     * wordsType    int     投诉类型
     * desc         String  描述
     * [orderSn]    String  订单号
     * [userName]   String  姓名
     * }
     */
    public int complaint(Map param){
        try {
            String jsonStr = userService.saveWordsLeave(JSON.toJSONString(param));
            Map mapData = JSON.parseObject(jsonStr,Map.class);
            if((Integer) mapData.get("code") == 0){
                return 1;
            }else{
                return 0;
            }
        } catch (Exception e) {
            LogUtil.getLogger(LogUtil.LoggerName.HESSIAN).error(e.getMessage());
            return -1;
        }
    }
    /**
     * 智能排序
     * @param param{
     * sessionId String session标识
     * userId    int    用户ID
     * deviceId  int    设备ID
     * }
     */
    public Object intelligentSortFlag(Map param){
        try {
            String jsonStr = userService.getSmartSortFlag(JSON.toJSONString(param));
            Map mapData = JSON.parseObject(jsonStr,Map.class);
            if((Integer)mapData.get("code") == 0){
                return mapData.get("data");
            }else{
                throw new Exception("Return error");
            }
        } catch (Exception e) {
            LogUtil.getLogger(LogUtil.LoggerName.HESSIAN).error(e.getMessage());
            return null;
        }
    }
    /**
     * 获取用户默认收货地址
     * @param param{
     * userId   int 用户ID
     * }
     */
    public Object getUserDefaultAddr(Map param){
        try {
            String jsonStr = userAddr.getUserDefaultAddrByUid(JSON.toJSONString(param));
            Map mapData = JSON.parseObject(jsonStr,Map.class);
            if((Integer)mapData.get("code") == 0){
                return mapData.get("data");
            }else{
                throw new Exception("Return Error");
            }
        } catch (Exception e) {
            LogUtil.getLogger(LogUtil.LoggerName.HESSIAN).error(e.getMessage());
            return null;
        }
    }
    /**
     * 获取用户收货地址
     * @param param{
     * page     int 页码
     * pageSize int 数量
     * searchConditionList array[]{"name":"userId","value":?}
     * }
     */
    public Object getUserAddr(Map param){
        try {
            String jsonStr = userAddr.getUserAddrByUid(JSON.toJSONString(param));
            Map mapData = JSON.parseObject(jsonStr,Map.class);
            if((Integer)mapData.get("code") == 0){
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
