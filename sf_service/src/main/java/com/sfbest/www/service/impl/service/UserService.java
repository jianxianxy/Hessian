package com.sfbest.www.service.impl.service;

import com.sfbest.www.domain.dao.UserDao;
import com.sfbest.www.domain.entity.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service("userService")
public class UserService {

    @Resource
    public UserDao userDao;

    public List<User> getUser(Map<String,Integer> pageInfo){
        List<User> list = userDao.getUserPage(pageInfo);
        return list;
    }

}
