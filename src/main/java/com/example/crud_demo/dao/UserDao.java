package com.example.crud_demo.dao;

import com.example.crud_demo.pojo.UserInfo;
import com.sun.org.apache.bcel.internal.generic.NEW;

import org.apache.catalina.User;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author: keenor
 * @Date: 2018/4/11
 * @Description:
 */

@Slf4j
@Component
public class UserDao {

    private static TreeMap<Long, UserInfo> userMap = new TreeMap<>((o1, o2) -> o1.compareTo(o2));
    /**
     * loginname index
     */
    private static TreeMap<String, Long> loginNameMap = new TreeMap<>((o1, o2) -> o1.compareTo(o2));

    static {
        try {
            File file = ResourceUtils.getFile("classpath:data/user_info.txt");
            BufferedReader reader;
            String temp;
            boolean isDataLine = false;
            reader = new BufferedReader(new FileReader(file));
            while ((temp = reader.readLine()) != null) {
                if (isDataLine) {
                    String[] split = temp.split("\\|");
                    UserInfo user = new UserInfo();
                    user.setId(Long.parseLong(split[0].trim()));
                    user.setLoginname(split[1].trim());
                    user.setPassword(getRandomPassword());
                    user.setName(split[3].trim());
                    userMap.put(Long.parseLong(split[0].trim()), user);

                    loginNameMap.put(split[1].trim(), Long.parseLong(split[0].trim()));
                }
                isDataLine = true;
            }
        } catch (FileNotFoundException e) {
            log.error("read data file fail", e);
        } catch (IOException e) {
            log.error("read line error", e);
        }
    }

    public List<UserInfo> getUserOrderId(String order) {
        List<UserInfo> userInfos = new ArrayList<>();
        for (Map.Entry<Long, UserInfo> longUserInfoEntry : userMap.entrySet()) {
            userInfos.add(longUserInfoEntry.getValue());
        }
        if ("desc".equals(order)){
            Collections.reverse(userInfos);
        }
        return userInfos;
    }

    public UserInfo getUserById(Long id) {
        return userMap.get(id);
    }

    public List<UserInfo> getUserOrderLoginName(String order) {
        List<UserInfo> userInfos = new ArrayList<>();

        for (Map.Entry<String, Long> entry : loginNameMap.entrySet()) {
            userInfos.add(userMap.get(entry.getValue()));
        }
        if ("desc".equals(order)){
            Collections.reverse(userInfos);
        }
        return userInfos;
    }

    public UserInfo getUserByLoginName(String loginName) {
        return userMap.get(loginNameMap.get(loginName));
    }



    private static String getRandomPassword() {
        return " "+ System.currentTimeMillis() + (long) (Math.random() * 10000L);
    }

}
