package com.polibius.businessmanagementappdesk.util;

import java.io.InputStream;
import java.util.Properties;

public class AppConfig {

    private static final Properties props = new Properties();

    static {
        try(InputStream input = AppConfig.class.getResourceAsStream("/config.properties")) {
            if(input != null) {
                props.load(input);
                System.out.println("Config loaded!");
                System.out.println("url: " + getBackendURL());
            } else {
                System.out.println("Unable to load config!!");
            }

        } catch(Exception e) {
            System.out.println("Unable to load config");
            e.printStackTrace();
        }
    }

    public static String getBackendURL() {
        return props.getProperty("backend.url", "http://localhost:8080");
    }
}
