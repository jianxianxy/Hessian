package com.sfbest.www.service.controller;

import com.sfbest.www.domain.dao.UserDao;
import com.sfbest.www.domain.entity.User;
import com.sfbest.www.service.impl.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

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
    public UserService userService;

    @RequestMapping(value = {"","/index"})
    public String index(HttpServletRequest request, Model model){
        Map<String,Integer> map = new HashMap<>();
        map.put("page",0);
        map.put("limit",3);
        List<User> userList = userDao.getUserPage(map);
        model.addAttribute("userList",userList);
        return "index";
    }

}
