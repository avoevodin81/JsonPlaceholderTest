package com.typicode.jsonplaceholder.contract;

import com.typicode.jsonplaceholder.RestBaseTest;
import com.typicode.jsonplaceholder.rest.postcomments.PostsCommentsRestServiceContext;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static com.jayway.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class TestGETPostsIdCommentsContract extends RestBaseTest implements PostsCommentsRestServiceContext {

    @Test(dataProvider = "data")
    public void testGETPostsContract(int postId) {
        postsCommentsRestService.getPostsIdComments(postId)
                .then()
                .log().body()
                .body(matchesJsonSchemaInClasspath(getResponseSchemaJson()));
    }

    @DataProvider(name = "data")
    public Object[][] getDataForPositiveScenario() {
        return new Object[][]{
                {1}, {58}, {100}
        };
    }
}
