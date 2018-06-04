package com.sfbest.www.domain.dao;

import com.sfbest.www.domain.entity.User;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;


@Repository
public interface UserDao {

    List<User> getUserPage(Map<String,Integer> page);

}
