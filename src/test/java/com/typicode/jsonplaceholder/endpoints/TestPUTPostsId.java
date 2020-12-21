package com.typicode.jsonplaceholder.endpoints;

import com.typicode.jsonplaceholder.RestBaseTest;
import com.typicode.jsonplaceholder.rest.posts.PostsRestServiceContext;
import com.typicode.jsonplaceholder.rest.posts.model.PostsModel;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static com.typicode.jsonplaceholder.constants.TestDataKeyConstants.DEFAULT_TEST_DATA_KEY;
import static com.typicode.jsonplaceholder.utils.TestDataUtils.getModelFromFileWithKey;
import static org.testng.Assert.assertEquals;

public class TestPUTPostsId extends RestBaseTest implements PostsRestServiceContext {

    @Test(dataProvider = "PositiveScenarios")
    public void testPOSTPosts(int postsId) {
        PostsModel updatedModel = getModelFromFileWithKey(getTestDataPath(), DEFAULT_TEST_DATA_KEY + postsId, PostsModel.class);
        Response response = postsRestService.putPosts(postsId, updatedModel);

        PostsModel updatedPostsModel = response
                .then()
                .log().all()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .as(PostsModel.class);

        assertEquals(updatedPostsModel, updatedModel, "Updated user is not the same");
    }

    @Test(dataProvider = "NegativeScenarios")
    public void testPOSTPostsNegative(int postsId) {
        PostsModel updatedModel = getModelFromFileWithKey(getTestDataPath(), DEFAULT_TEST_DATA_KEY, PostsModel.class);
        Response response = postsRestService.putPosts(postsId, updatedModel);

        response
                .then()
                .log().all()
                .statusCode(HttpStatus.SC_NOT_FOUND);
    }

    @DataProvider(name = "PositiveScenarios")
    public Object[][] getDataForPositiveScenario() {
        return new Object[][]{
                {1},
                {38},
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
}
