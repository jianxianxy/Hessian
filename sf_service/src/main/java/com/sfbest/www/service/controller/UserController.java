package com.sfbest.www.service.controller;

import com.caucho.hessian.client.HessianProxyFactory;
import com.sf.search.remote.HessianSearcher;
import com.sfbest.www.domain.dao.UserDao;
import com.sfbest.www.domain.entity.User;
import com.sfbest.www.domain.service.HessianService;
import com.sfbest.www.service.util.RedisUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.net.URLDecoder;
import java.util.*;
import com.alibaba.fastjson.JSON;

/**
 * Created on 2016/7/15.
 */

@Controller
@RequestMapping("/user")
public class UserController {

    private Logger log = Logger.getLogger(UserController.class);
    @Resource
    public UserDao userDao;
    @Resource
    private RedisUtil redisUtil;
    @Autowired
    private HessianSearcher searcher;

    @RequestMapping(value = {"","/index"})
    public String index(HttpServletRequest request, Model model){
        Map<String,Integer> map = new HashMap<>();
        map.put("page",0);
        map.put("limit",3);
        List<User> userList = userDao.getUserPage(map);
        model.addAttribute("userList",userList);

        redisUtil.set("Jtest","java");
        String ret = redisUtil.get("Jtest");
        model.addAttribute("Jtest",ret);

        try{
            Map<String,Integer> mapCid = new HashMap<>();
            mapCid.put("cid",8299);
            String str = URLDecoder.decode(searcher.brandByCid(JSON.toJSONString(mapCid)), "UTF-8");
            System.out.println(str);
            Map mapType = JSON.parseObject(str,Map.class);
            Map brandList = (Map) mapType.get("result");
            model.addAttribute("brandList",brandList.get("brandList"));
        }catch (Exception e){

        }
        return "index";
    }

}
