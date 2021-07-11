/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spit.service;

import com.spit.model.Post;
import com.spit.model.Status;
import com.spit.model.User;
import com.spit.repository.UserRepository;
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

@Path("users")
public class UserService {
    UserRepository ur = new UserRepository();
    
    @GET
    @Produces(MediaType.APPLICATION_JSON) 
    public List<User> getUsers() {
        return ur.getUsers();
    }
    
    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON) 
    public User getUser(@PathParam("id") String id) {
        return ur.getUser(id);
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON) 
    public Status createUser(User u) {
        Status st = ur.createUser(u);
        return st;
    }
    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Status updateUser(User u) {
        Status st = ur.updateUser(u);
        return st;
    }
    
    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON) 
    public Status deletePost(@PathParam("id") String id) {
        User u = ur.getUser(id);
        Status st = null;
        if(u != null) {
            st = ur.deleteUser(id);
        }
        return st;
    }
    
    @GET
    @Path("user-posts/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Post> postsByUser(@PathParam("id") String id) {
        return ur.postsByUser(id);
    }
}
