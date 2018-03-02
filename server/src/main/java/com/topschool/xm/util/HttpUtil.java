package com.topschool.xm.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpUtil {
    public static JSONObject getJson(String url) {
        StringBuilder sb = new StringBuilder();
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return JSON.parseObject(sb.toString());
    }
}
