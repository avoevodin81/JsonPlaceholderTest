package com.typicode.jsonplaceholder.rest.postcomments;

import com.typicode.jsonplaceholder.rest.RestService;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import static com.typicode.jsonplaceholder.constants.RestEntityConstants.COMMENTS;
import static com.typicode.jsonplaceholder.constants.RestEntityConstants.POSTS_PATH_ID_COMMENTS;

/**
 * Service-client for using /posts/comments... endpoint
 */
public class PostsCommentsRestService extends RestService {

    public Response getPostsIdComments(int postId) {
        return RestAssured.given()
                .spec(requestSpecification)
                .basePath(POSTS_PATH_ID_COMMENTS)
                .pathParam("id", postId)
                .log().all()
                .get();
    }

    public Response getComments(int postId) {
        return RestAssured.given()
                .param("postId", postId)
                .spec(requestSpecification)
                .basePath(COMMENTS)
                .log().all()
                .get();
    }
}
