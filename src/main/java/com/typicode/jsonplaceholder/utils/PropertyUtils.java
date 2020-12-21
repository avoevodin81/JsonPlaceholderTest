package com.typicode.jsonplaceholder.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Util class for reading main configuration properties
 */
public class PropertyUtils {

    public static final String URL_HOST = "url.host";
    public static final String SCHEMA_PATH = "schema.path";
    public static final String TEST_DATA_PATH = "testdata.path";

    private static Properties prop = initPropertyData("src/main/resources/config.properties");

    public static String getPropertyData(String key) {
        return prop.getProperty(key);
    }

    private static Properties initPropertyData(String fileName) {
        Properties prop = null;
        try (FileInputStream fis = new FileInputStream(fileName)) {
            prop = new Properties();
            prop.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return prop;
    }
}
