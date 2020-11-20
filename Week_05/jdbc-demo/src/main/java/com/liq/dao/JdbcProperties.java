package com.liq.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @description TODO
 * author: liquan
 * date: 2020/11/18 11:17
 * version: 1.0
 */
public class JdbcProperties {

    public static Map<String, String> profiles = new HashMap<String, String>();

    static {
        try {
            Properties pps = new Properties();
            pps.load(new FileInputStream("./src/main/resources/db.properties"));

            for (Object key : pps.keySet()) {
                profiles.put(key.toString(), pps.getProperty(key.toString()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        System.out.println(JdbcProperties.profiles.get("jdbc.url"));
    }
}
