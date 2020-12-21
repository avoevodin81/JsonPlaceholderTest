package com.typicode.jsonplaceholder.contract;

import com.typicode.jsonplaceholder.RestBaseTest;
import com.typicode.jsonplaceholder.rest.postcomments.PostsCommentsRestServiceContext;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static com.jayway.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class TestGETCommentsIdContract extends RestBaseTest implements PostsCommentsRestServiceContext {

    @Test(dataProvider = "data")
    public void testGETCommentsIdContract(int postID) {
        postsCommentsRestService.getComments(postID)
                .then()
                .log().body()
                .body(matchesJsonSchemaInClasspath(getResponseSchemaJson(TestGETPostsIdCommentsContract.class.getSimpleName())));
    }

    @DataProvider(name = "data")
    public Object[][] getPostId() {
        return new Object[][]{
                {1}, {35}, {100}
        };
    }
}
