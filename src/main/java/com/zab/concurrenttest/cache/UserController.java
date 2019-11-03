package com.zab.concurrenttest.cache;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class UserController {

    User user = new User();
    User user1 = new User();

    Map<String,User> map = new HashMap<>();

    {
        user.setName("zhangsan");
        user.setId("1");
        user.setAge("12");

        user1.setName("lisi");
        user1.setId("2");
        user1.setAge("11");

        map.put("zhangsan",user);
        map.put("lisi",user1);
    }

    @RequestMapping("/user")
    @Cacheable(value = "user")
    public User getUser(String name){

        return map.get(name);
    }

    @RequestMapping("update_user")
    @CacheEvict(value = "user")
    public User updateUser(String name){
        map.get(name).setId("0");
        map.get(name).setAge("0");
        return map.get(name);
    }


}
