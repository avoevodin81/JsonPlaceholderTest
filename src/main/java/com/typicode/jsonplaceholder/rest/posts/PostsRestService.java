package com.typicode.jsonplaceholder.rest.posts;

import com.typicode.jsonplaceholder.rest.RestService;
import com.typicode.jsonplaceholder.rest.posts.model.PostsModel;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import static com.typicode.jsonplaceholder.constants.RestEntityConstants.POSTS_PATH;
import static com.typicode.jsonplaceholder.constants.RestEntityConstants.POSTS_PATH_ID;

/**
 * Service-client for using /posts... endpoint
 */
public class PostsRestService extends RestService {

    public Response getPosts() {
        return RestAssured.given()
                .spec(requestSpecification)
                .basePath(POSTS_PATH)
                .log().all()
                .get();
    }

    public Response getPosts(int postId) {
        return RestAssured.given()
                .spec(requestSpecification)
                .basePath(POSTS_PATH_ID)
                .pathParam("id", postId)
                .log().all()
                .get();
    }

    public Response postPosts(PostsModel model) {
        return RestAssured.given()
                .spec(requestSpecification)
                .basePath(POSTS_PATH)
                .body(model)
                .log().all()
                .post();
    }

    public Response putPosts(int postId, PostsModel model) {
        return RestAssured.given()
                .spec(requestSpecification)
                .basePath(POSTS_PATH_ID)
                .pathParam("id", postId)
                .body(model)
                .log().all()
                .put();
    }

    public Response deletePosts(int postId) {
        return RestAssured.given()
                .spec(requestSpecification)
                .basePath(POSTS_PATH_ID)
                .pathParam("id", postId)
                .log().all()
                .delete();
    }
}
