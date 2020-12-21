package com.typicode.jsonplaceholder.endpoints;

import com.typicode.jsonplaceholder.RestBaseTest;
import com.typicode.jsonplaceholder.rest.postcomments.PostsCommentsRestServiceContext;
import com.typicode.jsonplaceholder.rest.postcomments.model.PostsCommentsModel;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static com.typicode.jsonplaceholder.constants.TestDataKeyConstants.DEFAULT_TEST_DATA_KEY;
import static com.typicode.jsonplaceholder.utils.TestDataUtils.getModelFromFileWithKey;
import static org.testng.Assert.assertEquals;

public class TestGETCommentsPostId extends RestBaseTest implements PostsCommentsRestServiceContext {

    @Test(dataProvider = "dataForVerifyValues")
    public void testGETCommentsPostIdVerifyValues(int postId) {
        Response response = postsCommentsRestService.getComments(postId);
        PostsCommentsModel[] postsRequestModels = response
                .then()
                .log().all()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .as(PostsCommentsModel[].class);

        PostsCommentsModel expectedComment = getModelFromFileWithKey(getTestDataPath(), DEFAULT_TEST_DATA_KEY+ postId, PostsCommentsModel.class);

        assertEquals(postsRequestModels[0], expectedComment, "Expected comment is not the same");
    }

    @Test(dataProvider = "dataForVerifySize")
    public void testGETPostsIdCommentsVerifySize(int postId, int size) {
        Response response = postsCommentsRestService.getComments(postId);
        PostsCommentsModel[] postsRequestModels = response
                .then()
                .log().all()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .as(PostsCommentsModel[].class);

        assertEquals(postsRequestModels.length, size, "json body size:");
    }

    @DataProvider(name = "dataForVerifyValues")
    public Object[][] getPostId() {
        return new Object[][]{
                {1}, {68}, {100}
        };
    }

    @DataProvider(name = "dataForVerifySize")
    public Object[][] getDataForPositiveScenario() {
        return new Object[][]{
                {1, 5},
                {53, 5},
                {100, 5},
                {0, 0},
                {101, 0},
                {1000000000, 0}
        };
    }
}
