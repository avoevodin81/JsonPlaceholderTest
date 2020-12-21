package com.typicode.jsonplaceholder.rest.postcomments;

/**
 * Context for creating and instance of PostsCommentsRestService class
 */
public interface PostsCommentsRestServiceContext {
    PostsCommentsRestService postsCommentsRestService = new PostsCommentsRestService();
}
