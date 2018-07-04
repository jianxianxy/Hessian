package com.sfbest.www.service.impl.service;

import com.alibaba.fastjson.JSON;
import com.sfbest.account.api.sms.SmsService;
import com.sfbest.www.service.util.LogUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class SmsApiService {

    @Autowired
    SmsService userSms;

    /**
     * 获取验证码
     * @param param{
     * mobile String 手机号
     * type   String 模版
     * userId Int    用户ID
     * ip     String 长整型格式放入IP地址
     * }
     */
    public Object getCode(Map param){
        try {
            String jsonStr = userSms.getSmsCode(JSON.toJSONString(param));
            System.out.println(jsonStr);
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
     * 检查验证码
     * @param param{
     * mobile  String 手机号
     * smsCode String 验证码
     * }
     */
    public Boolean checkCode(Map param){
        try {
            String jsonStr = userSms.isOk(JSON.toJSONString(param));
            System.out.println(jsonStr);
            Map mapData = JSON.parseObject(jsonStr,Map.class);
            if((Integer)mapData.get("code") == 0){
                return true;
            }else{
                throw new Exception("Return Error");
            }
        } catch (Exception e) {
            LogUtil.getLogger(LogUtil.LoggerName.HESSIAN).error(e.getMessage());
            return false;
        }
    }
}
