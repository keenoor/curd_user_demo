package com.example.crud_demo;

import com.example.crud_demo.dao.UserDao;
import com.example.crud_demo.pojo.UserInfo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author: keenor
 * @Date: 2018/4/11
 * @Description:
 */

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserTest {

    @Autowired
    private UserDao userDao;

    @Test
    public void testUser(){
        UserInfo user = userDao.getUserById(2L);
        log.info("id: {}, loginName: {}", user.getId(), user.getLoginname());
    }
}
