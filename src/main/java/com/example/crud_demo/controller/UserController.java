package com.example.crud_demo.controller;

import com.example.crud_demo.dao.UserDao;
import com.example.crud_demo.pojo.UserInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @Author: keenor
 * @Date: 2018/4/11
 * @Description:
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserDao userDao;

    @RequestMapping("/list")
    public ModelAndView userList() {
        return new ModelAndView("list");
    }

    @RequestMapping("/api/select")
    @ResponseBody
    public UserInfo select(Long id, String loginname) {
        if (id != null) {
            return userDao.getUserById(id);
        } else if (!StringUtils.isEmpty(loginname)) {
            return userDao.getUserByLoginName(loginname);
        } else {
            return null;
        }
    }

    @RequestMapping("/api/list")
    @ResponseBody
    public List<UserInfo> list(String sort, String order) {
        if ("loginname".equals(sort)) {
            return userDao.getUserOrderLoginName(order);
        } else {
            return userDao.getUserOrderId(order);
        }
    }

}
