package com.事件;

import lombok.Data;

//我们简单创建一个用户实体，并添加两个字段：用户名、密码。实体代码如下所示：
@Data
public class UserBean {

    //用户名
    private String name;
    //密码
    private String password;

}