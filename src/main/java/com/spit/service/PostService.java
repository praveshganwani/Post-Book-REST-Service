/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spit.service;

import com.spit.model.Post;
import com.spit.model.Status;
import com.spit.model.User;
import com.spit.repository.PostRepository;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Pravesh Ganwani
 */

@Path("posts")
public class PostService {
    PostRepository pr = new PostRepository();
    
    @GET
    @Produces(MediaType.APPLICATION_JSON) 
    public List<Post> getPosts() {
        return pr.getPosts();
    }
    
    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON) 
    public Post getPost(@PathParam("id") String id) {
        return pr.getPost(id);
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON) 
    public Status createPost(Post p) {
        Status st = pr.createPost(p);
        return st;
    }
    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Status updatePost(Post p) {
        Status st = pr.updatePost(p);
        return st;
    }
    
    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON) 
    public Status deletePost(@PathParam("id") String id) {
        Post p = pr.getPost(id);
        Status st = null;
        if(p != null) {
            st = pr.deletePost(id);
        }
        return st;
    }
    
    @GET
    @Path("posted-by/{id}")
    @Produces(MediaType.APPLICATION_JSON) 
    public User postedBy(@PathParam("id") String id) {
        return pr.postedBy(id);
    }
}
