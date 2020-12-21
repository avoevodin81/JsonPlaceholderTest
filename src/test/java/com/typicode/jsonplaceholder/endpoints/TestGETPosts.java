package com.typicode.jsonplaceholder.endpoints;

import com.typicode.jsonplaceholder.RestBaseTest;
import com.typicode.jsonplaceholder.rest.posts.PostsRestServiceContext;
import com.typicode.jsonplaceholder.rest.posts.model.PostsModel;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

import static com.typicode.jsonplaceholder.constants.TestDataKeyConstants.TEST_DATA_FIRST_USER;
import static com.typicode.jsonplaceholder.constants.TestDataKeyConstants.TEST_DATA_LAST_USER;
import static com.typicode.jsonplaceholder.utils.TestDataUtils.getModelFromFileWithKey;
import static org.testng.Assert.assertEquals;

public class TestGETPosts extends RestBaseTest implements PostsRestServiceContext {

    @Test
    public void testGETPosts() {
        Response response = postsRestService.getPosts();
        PostsModel[] postsRequestModels = response
                .then()
                .log().all()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .as(PostsModel[].class);

        assertEquals(postsRequestModels.length, 100, "json body size:");

        PostsModel expectedFirstUser = getModelFromFileWithKey(getTestDataPath(), TEST_DATA_FIRST_USER, PostsModel.class);
        PostsModel expectedLastUser = getModelFromFileWithKey(getTestDataPath(), TEST_DATA_LAST_USER, PostsModel.class);

        assertEquals(postsRequestModels[0], expectedFirstUser, "The first user is not the same");
        assertEquals(postsRequestModels[99], expectedLastUser, "The last user is not the same");
    }
}
