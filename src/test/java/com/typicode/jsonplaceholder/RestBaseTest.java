package com.typicode.jsonplaceholder;

import com.typicode.jsonplaceholder.utils.PropertyUtils;

/**
 * Base test for the tests
 */
public class RestBaseTest {

    private static final String SCHEMA_PATH = PropertyUtils.getPropertyData(PropertyUtils.SCHEMA_PATH);
    private static final String TEST_DATA_PATH = PropertyUtils.getPropertyData(PropertyUtils.TEST_DATA_PATH);

    protected String getResponseSchemaJson() {
        return getResponseSchemaJson(this.getClass().getSimpleName());
    }

    protected String getResponseSchemaJson(String fileName) {
        return String.format(SCHEMA_PATH, fileName);
    }

    protected String getTestDataPath() {
        return String.format(TEST_DATA_PATH, this.getClass().getSimpleName());
    }
}
