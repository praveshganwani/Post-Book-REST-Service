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
public class UserRepository {
    
    public List<User> getUsers() {
        List<User> allUsers = new ArrayList<>();
        try {
            Connection con = DBConfig.getConnection();
            PreparedStatement ps = con.prepareStatement("select * from users");
            ResultSet rs = ps.executeQuery();
            while(rs.next())
            {
                User u = new User();
                u.setUserId(rs.getString("userid"));
                u.setUserName(rs.getString("username"));
                u.setUserEmail(rs.getString("useremail"));
                u.setUserPassword(rs.getString("userpassword"));
                allUsers.add(u);
            }
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return allUsers;
    }
    
    public User getUser(String id) {
        User u = null;
        try {
            Connection con = DBConfig.getConnection();
            PreparedStatement ps = con.prepareStatement("select * from users where userid=?");
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            while(rs.next())
            {
                u = new User();
                u.setUserId(rs.getString("userid"));
                u.setUserName(rs.getString("username"));
                u.setUserEmail(rs.getString("useremail"));
                u.setUserPassword(rs.getString("userpassword"));
            }
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return u;
    }
    
    public Status createUser(User u) {
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
            u.setUserId("USER" + randomNumber + randomCharacters);
            PreparedStatement ps = con.prepareStatement("insert into users (userid, username, useremail, userpassword) values(?,?,?,?)");
            ps.setString(1, u.getUserId());
            ps.setString(2, u.getUserName());
            ps.setString(3, u.getUserEmail());
            ps.setString(4, u.getUserPassword());
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
    
    public Status updateUser(User u) {
        Status st = null;
        try {
            Connection con = DBConfig.getConnection();
            PreparedStatement ps = con.prepareStatement("update users set username=?, useremail=?, password=? where userid=?");
            ps.setString(1, u.getUserName());
            ps.setString(2, u.getUserEmail());
            ps.setString(3, u.getUserPassword());
            ps.setString(4, u.getUserId());
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
    
    public Status deleteUser(String id) {
        Status st = null;
        try {
            Connection con = DBConfig.getConnection();
            PreparedStatement ps = con.prepareStatement("delete from users where userid=?");
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
    
    public List<Post> postsByUser(String id) {
        PostRepository pr = new PostRepository();
        List<Post> allPosts = pr.getPosts();
        List<Post> userPosts = new ArrayList<>();
        try {
            for(Post p : allPosts) {
                if(p.getUserId().equals(id)) {
                    userPosts.add(p);
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return userPosts;
    }
}
