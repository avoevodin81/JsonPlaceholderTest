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
import static org.testng.Assert.assertEquals;

public class TestPOSTPosts extends RestBaseTest implements PostsRestServiceContext {

    @Test(dataProvider = "PositiveScenarios")
    public void testPOSTPosts(String testDataKey) {
        PostsModel modelForPost = getModelFromFileWithKey(getTestDataPath(), testDataKey, PostsModel.class);
        Response response = postsRestService.postPosts(modelForPost);

        PostsModel createdPostsModel = response
                .then()
                .log().all()
                .statusCode(HttpStatus.SC_CREATED)
                .extract()
                .as(PostsModel.class);

        assertEquals(createdPostsModel, modelForPost, "Created user is not the same");
    }

    @DataProvider(name = "PositiveScenarios")
    public Object[][] getDataForPositiveScenario() {
        return new Object[][]{
                {"TestDataSimpleUserCreation"},
                {"TestDataCheckValidationOfId"},
                {"TestDataCheckValidationWOTitle"},
                {"TestDataCheckValidationWOBody"}
        };
    }

    @AfterTest
    public void removeCreatedData() {
        Arrays.asList("TestDataSimpleUserCreation", "TestDataCheckValidationOfId", "TestDataCheckValidationWOTitle",
                "TestDataCheckValidationWOBody").forEach(testDataKey -> {
            PostsModel model = getModelFromFileWithKey(getTestDataPath(), testDataKey, PostsModel.class);
            postsRestService.deletePosts(model.getId());
        });
    }
}
