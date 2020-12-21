package com.typicode.jsonplaceholder.rest.posts;

/**
 * Context for creating and instance of PostsRestService class
 */
public interface PostsRestServiceContext {
    PostsRestService postsRestService = new PostsRestService();
}
