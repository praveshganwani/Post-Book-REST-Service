/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.spit.repository;

import com.spit.configs.DBConfig;
import com.spit.model.Post;
import com.spit.model.Status;
import com.spit.model.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Pravesh Ganwani
 */
public class PostRepository {
    
    public List<Post> getPosts() {
        List<Post> allPosts = new ArrayList<>();
        try {
            Connection con = DBConfig.getConnection();
            PreparedStatement ps = con.prepareStatement("select * from posts");
            ResultSet rs = ps.executeQuery();
            while(rs.next())
            {
                Post p = new Post();
                p.setUserId(rs.getString("userid"));
                p.setPostId(rs.getString("postid"));
                p.setPost(rs.getString("post"));
                allPosts.add(p);
            }
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return allPosts;
    }
    
    public Post getPost(String id) {
        Post p = null;
        try {
            Connection con = DBConfig.getConnection();
            PreparedStatement ps = con.prepareStatement("select * from posts where postid=?");
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            while(rs.next())
            {
                p = new Post();
                p.setUserId(rs.getString("userid"));
                p.setPostId(rs.getString("postid"));
                p.setPost(rs.getString("post"));
            }
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return p;
    }
    
    public Status createPost(Post p) {
        Status st = null;
        try {
            Connection con = DBConfig.getConnection();
            String numbers = "123456789";
            Random generator = new Random();
            int randomIndex = generator.nextInt(numbers.length());
            int randomNumber = numbers.charAt(randomIndex);
            StringBuilder randomCharacters = new StringBuilder();
            String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
            for(int i=0; i<4; i++)
            {
                Random characterGenerator = new Random();
                randomIndex = characterGenerator.nextInt(characters.length());
                randomCharacters.append(characters.charAt(randomIndex));
            }
            randomNumber = randomNumber*23;
            p.setPostId("POST" + randomNumber + randomCharacters);
            PreparedStatement ps = con.prepareStatement("insert into posts (userid, postid, post) values(?,?,?)");
            ps.setString(1, p.getUserId());
            ps.setString(2, p.getPostId());
            ps.setString(3, p.getPost());
            ps.executeUpdate();
            st = new Status();
            st.setStatus(1);
            con.close();
        } catch (Exception e) {
            st = new Status();
            st.setStatus(-1);
            System.out.println(e);
        }
        return st;
    }
    
    public Status updatePost(Post p) {
        Status st = null;
        try {
            Connection con = DBConfig.getConnection();
            PreparedStatement ps = con.prepareStatement("update posts set post=? where postid=?");
            ps.setString(1, p.getPost());
            ps.setString(2, p.getPostId());
            ps.executeUpdate();
            st = new Status();
            st.setStatus(1);
            con.close();
        } catch (Exception e) {
            System.out.println(e);
            st = new Status();
            st.setStatus(-1);
        }
        return st;
    }
    
    public Status deletePost(String id) {
        Status st = null;
        try {
            Connection con = DBConfig.getConnection();
            PreparedStatement ps = con.prepareStatement("delete from posts where postid=?");
            ps.setString(1, id);
            ps.executeUpdate();
            st = new Status();
            st.setStatus(1);
            con.close();
        } catch (Exception e) {
            System.out.println(e);
            st = new Status();
            st.setStatus(-1);
        }
        return st;
    }
    
    public User postedBy(String id) {
        UserRepository ur = new UserRepository();
        User u = null;
        Post p = getPost(id);
        try {
            u = ur.getUser(p.getUserId());
        } catch (Exception e) {
            System.out.println(e);
        }
        return u;
    }
    
}
