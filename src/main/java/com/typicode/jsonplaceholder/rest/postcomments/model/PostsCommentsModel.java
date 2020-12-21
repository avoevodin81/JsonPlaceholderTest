package com.typicode.jsonplaceholder.rest.postcomments.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.typicode.jsonplaceholder.rest.ModelService;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PostsCommentsModel implements ModelService {
    private int postId;
    private int id;
    private String name;
    private String email;
    private String body;
}
