package com.sfbest.www.service.impl;

import com.alibaba.fastjson.JSON;
import com.caucho.hessian.server.HessianServlet;
import com.sf.search.remote.ArticleHessianSearcher;
import com.sfbest.www.domain.entity.User;
import com.sfbest.www.domain.service.HessianService;
import com.sfbest.www.service.impl.service.ArticleSearchService;
import com.sfbest.www.service.impl.service.CommentService;
import com.sfbest.www.service.impl.service.SearchService;
import com.sfbest.www.service.impl.service.UserService;
import com.sfbest.www.service.util.RedisUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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
    CommentService commentService;

    @Override
    public String sayHello(String name) {

        Map map0 = new HashMap();
        map0.put("name","productId");
        map0.put("value",202008);
        Map map1 = new HashMap();
        map1.put("name","selected");
        map1.put("value",0);

        ArrayList search = new ArrayList();
        search.add(map0);
        search.add(map1);

        Map cond = new HashMap();
        cond.put("page",0);
        cond.put("pageSize",3);
        cond.put("searchConditionList",search);
        String jsonStr = JSON.toJSONString(commentService.getCommentList(cond));
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
