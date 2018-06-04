package com.sfbest.www.domain.service;

import com.sfbest.www.domain.entity.User;

import java.util.List;
import java.util.Map;

public interface HessianService {
    String sayHello(String name);
    String getUserList(List<User> users);
    String getUserByPage(String pageInfo);
}
