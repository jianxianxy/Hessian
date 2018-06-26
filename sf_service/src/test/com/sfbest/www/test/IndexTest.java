package com.sfbest.www.test;

import com.alibaba.fastjson.JSON;
import com.caucho.hessian.client.HessianProxyFactory;
import com.sfbest.www.domain.service.HessianService;

import java.util.HashMap;
import java.util.Map;

public class IndexTest {
    public static String url = "http://localhost:8080/index";

    public static void main(String[] args) {
        try {

            HessianProxyFactory factory = new HessianProxyFactory();
            final HessianService commonService = (HessianService) factory.create(HessianService.class, url);
            System.out.printf(commonService.sayHello("Kylin"));
            /*
            Map<String,Integer> map = new HashMap<>();
            map.put("page",0);
            map.put("limit",3);
            System.out.printf(commonService.getUserByPage(JSON.toJSONString(map)));
            */
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
