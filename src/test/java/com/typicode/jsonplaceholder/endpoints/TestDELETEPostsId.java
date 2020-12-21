package com.typicode.jsonplaceholder.endpoints;

import com.typicode.jsonplaceholder.RestBaseTest;
import com.typicode.jsonplaceholder.rest.posts.PostsRestServiceContext;
import com.typicode.jsonplaceholder.rest.posts.model.PostsModel;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.annotations.AfterTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Arrays;

import static com.typicode.jsonplaceholder.utils.TestDataUtils.getModelFromFileWithKey;

public class TestDELETEPostsId extends RestBaseTest implements PostsRestServiceContext {

    @Test(dataProvider = "PositiveScenarios")
    public void testDELETEPostsId(int postsId) {
        Response response = postsRestService.deletePosts(postsId);

        response
                .then()
                .log().all()
                .statusCode(HttpStatus.SC_OK);
    }

    @Test(dataProvider = "NegativeScenarios")
    public void testDELETEPostsIdNegative(int postsId) {
        Response response = postsRestService.deletePosts(postsId);

        response
                .then()
                .log().all()
                .statusCode(HttpStatus.SC_NOT_FOUND);
    }

    @DataProvider(name = "PositiveScenarios")
    public Object[][] getDataForPositiveScenario() {
        return new Object[][]{
                {1},
                {56},
                {100}
        };
    }

    @DataProvider(name = "NegativeScenarios")
    public Object[][] getDataForNegativeScenario() {
        return new Object[][]{
                {0},
                {101},
                {100000000}
        };
    }

    @AfterTest
    public void restoreData() {
        Arrays.asList("TestData1", "TestData56", "TestData100").forEach(testDataKey -> {
            PostsModel modelForPost = getModelFromFileWithKey(getTestDataPath(), testDataKey, PostsModel.class);
            postsRestService.postPosts(modelForPost);
        });
    }
}
