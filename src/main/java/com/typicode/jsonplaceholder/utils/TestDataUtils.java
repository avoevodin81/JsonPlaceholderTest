package com.typicode.jsonplaceholder.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.typicode.jsonplaceholder.rest.ModelService;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

/**
 * Util class for reading data from Yaml files and getting POJO model
 */
public class TestDataUtils {
    public static <T extends ModelService> T getModelFromFile(String filePath, Class<T> model) {
        File file = new File(filePath);
        ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
        ModelService modelService = null;
        try {
            modelService = objectMapper.readValue(file, model);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return model.cast(modelService);
    }

    public static <T extends ModelService> T getModelFromFileWithKey(String filePath, String key, Class<T> model) {
        File file = new File(filePath);
        Yaml yaml = new Yaml();
        Map<String, Object> map = null;
        try (InputStream inputStream = new FileInputStream(file)) {
            map = yaml.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        ObjectMapper objectMapper = new ObjectMapper();
        ModelService modelService = objectMapper.convertValue(map.get(key), model);
        return model.cast(modelService);
    }
}
