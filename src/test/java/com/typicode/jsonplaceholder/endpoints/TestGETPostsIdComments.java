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

public class TestGETPostsIdComments extends RestBaseTest implements PostsCommentsRestServiceContext {

    @Test(dataProvider = "data")
    public void testGETPostsIdComments(int postId, int size) {
        Response response = postsCommentsRestService.getPostsIdComments(postId);
        PostsCommentsModel[] postsRequestModels = response
                .then()
                .log().all()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .as(PostsCommentsModel[].class);

        assertEquals(postsRequestModels.length, size, "json body size:");
    }

    @Test
    public void testGETPostsIdCommentsBodyValue() {
        Response response = postsCommentsRestService.getPostsIdComments(1);
        PostsCommentsModel[] postsRequestModels = response
                .then()
                .log().all()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .as(PostsCommentsModel[].class);

        PostsCommentsModel expectedComment = getModelFromFileWithKey(getTestDataPath(), DEFAULT_TEST_DATA_KEY, PostsCommentsModel.class);
        assertEquals(postsRequestModels[0], expectedComment, "Expected comment is not the same");
    }


    @DataProvider(name = "data")
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
