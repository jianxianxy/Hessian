package com.sfbest.www.service.impl;

import com.alibaba.fastjson.JSON;
import com.caucho.hessian.server.HessianServlet;
import com.sfbest.www.domain.entity.User;
import com.sfbest.www.domain.service.HessianService;
import com.sfbest.www.service.impl.service.SearchService;
import com.sfbest.www.service.impl.service.UserService;
import com.sfbest.www.service.util.RedisUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class IndexImpl extends HessianServlet implements HessianService {

    @Resource
    public UserService userService;
    @Resource
    public RedisUtil redisUtil;
    @Resource
    SearchService searchService;

    @Override
    public String sayHello(String name) {
        ArrayList pidArr = new ArrayList();
        pidArr.add(257101);
        pidArr.add(256657);
        pidArr.add(257210);
        pidArr.add(201366);
        pidArr.add(256552);
        Map param = new HashMap();
        param.put("productIds",pidArr);
        param.put("threeRegion",500);
        param.put("fourRegion",-1);
        param.put("isStock",false);
        param.put("reachable",false);
        param.put("sortable",true);
        param.put("size",100);
        String jsonStr = JSON.toJSONString(searchService.getSuggest("酒"));
        return "RET:" + jsonStr;
    }
    @Override
    public String getUserList(List<User> users) {
        StringBuffer stringBuffer = new StringBuffer();
        for (User user : users) {
            stringBuffer.append("[");
            stringBuffer.append(user.getUserName());
            stringBuffer.append("--");
            stringBuffer.append(user.getPassword());
            stringBuffer.append("]");
        }
        return stringBuffer.toString();
    }
    @Override
    public String getUserByPage(String pageInfo) {

        Map pageMap = (Map)JSON.parse(pageInfo);
        List<User> userList = userService.getUser(pageMap);
        String jsonStr = JSON.toJSONString(userList);
        String ret = redisUtil.get("Jtest");

        return ret+":"+jsonStr;
    }

}
