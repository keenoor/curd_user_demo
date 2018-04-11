package com.example.crud_demo.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @Author: keenor
 * @Date: 2018/4/11
 * @Description:
 */

@Data
public class UserInfo {

    private Long id;
    private String loginname;
    private String password;
    private String name;

}
