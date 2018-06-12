package com.sfbest.www.test;

import com.alibaba.fastjson.JSON;
import com.caucho.hessian.client.HessianProxyFactory;
import com.sf.search.remote.HessianSearcher;

import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

public class SearchTest {
    public static String url = "http://10.102.4.32:8081/search/remoteSearcher.hessian";

    public static void main(String[] args) {
        try {

            HessianProxyFactory factory = new HessianProxyFactory();
            final HessianSearcher commonService = (HessianSearcher) factory.create(HessianSearcher.class, url);
            Map<String,Integer> map = new HashMap<>();
            map.put("cid",8299);
            String str = URLDecoder.decode(commonService.brandByCid(JSON.toJSONString(map)), "UTF-8");
            System.out.println(str);
            Map mapType = JSON.parseObject(str,Map.class);
            Map brandList = (Map) mapType.get("result");
            System.out.println(brandList.get("brandList"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
