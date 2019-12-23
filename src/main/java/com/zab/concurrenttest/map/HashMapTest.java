package com.zab.concurrenttest.map;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zab
 * @date 2019-12-19 21:09
 */
public class HashMapTest {

    private String s;
    private String s1;

    public HashMapTest(String s) {
        this.s = s;
    }

    public HashMapTest(String s,String s1) {
        super();
        this.s = s;
    }

    public static void main(String[] args) {
        Map<Object, Object> map = new HashMap<>();
        Map<Object, Object> map1 = new HashMap<>();
        map.put("1","111");
        map1.put(map,"11111");
        map.put("2","222");
        map1.put(map1,map);
        System.out.println(map1.get(map1));
        int i = 1;
    }
}
