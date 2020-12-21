package com.typicode.jsonplaceholder.contract;

import com.typicode.jsonplaceholder.RestBaseTest;
import com.typicode.jsonplaceholder.rest.posts.PostsRestServiceContext;
import org.testng.annotations.Test;

import static com.jayway.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class TestGETPostsContract extends RestBaseTest implements PostsRestServiceContext {

    @Test
    public void testGETPostsContract() {
        postsRestService.getPosts()
                .then()
                .log().body()
                .body(matchesJsonSchemaInClasspath(getResponseSchemaJson()));
    }
}
