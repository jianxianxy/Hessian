package com.sfbest.www.service.impl;

import com.alibaba.fastjson.JSON;
import com.caucho.hessian.server.HessianServlet;
import com.sfbest.www.domain.entity.User;
import com.sfbest.www.domain.service.HessianService;
import com.sfbest.www.service.impl.service.UserService;
import com.sfbest.www.service.util.RedisUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class IndexImpl extends HessianServlet implements HessianService {

    @Resource
    public UserService userService;

    @Resource
    public RedisUtil redisUtil;

    @Override
    public String sayHello(String name) {
        return "Hello," + name;
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
