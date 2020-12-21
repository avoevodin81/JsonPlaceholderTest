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

public class TestGETPostsId extends RestBaseTest implements PostsRestServiceContext {

    @Test(dataProvider = "PositiveScenarios")
    public void testGETPostsIdPositive(int postID) {
        Response response = postsRestService.getPosts(postID);
        PostsModel postsRequestModels = response
                .then()
                .log().all()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .as(PostsModel.class);

        PostsModel expectedUser = getModelFromFileWithKey(getTestDataPath(), DEFAULT_TEST_DATA_KEY + postID, PostsModel.class);

        assertEquals(postsRequestModels, expectedUser, "The user data is not the same");
    }

    @Test(dataProvider = "NegativeScenarios")
    public void testGETPostsIdNegative(int postID) {
        Response response = postsRestService.getPosts(postID);
        response
                .then()
                .log().all()
                .statusCode(HttpStatus.SC_NOT_FOUND);
    }

    @DataProvider(name = "PositiveScenarios")
    public Object[][] getDataForPositiveScenario() {
        return new Object[][]{
                {1}, {48}, {100}
        };
    }

    @DataProvider(name = "NegativeScenarios")
    public Object[][] getDataForNegativeScenario() {
        return new Object[][]{
                {0}, {101}, {1000000000}
        };
    }
}
