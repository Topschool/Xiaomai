package com.topschool.xm.util;

import org.junit.Test;

import static org.junit.Assert.*;

public class HttpUtilTest {

    @Test
    public void getJson() {
        String url = "http://api.map.baidu.com/telematics/v3/weather?location=%E6%88%90%E9%83%BD&output=json&ak=rnm8udmHdWaHFWZTO2tuTiG8";
        Object o = HttpUtil.getJson(url);
        System.out.println(o);
    }
}