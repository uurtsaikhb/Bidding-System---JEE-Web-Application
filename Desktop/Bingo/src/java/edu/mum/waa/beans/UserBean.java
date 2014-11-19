/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.waa.beans;

import edu.mum.waa.controllers.UserFacadeLocal;
import edu.mum.waa.models.User;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;

/**
 *
 * @author uurtsaikh
 */
@Named(value = "userBean")
@SessionScoped
public class UserBean implements Serializable {

    /**
     * Creates a new instance of UserBean
     */
    
    @EJB
    UserFacadeLocal userController;
    
    private String firstname;
    private String lastname;
    private String username;
    private String email;
    private String phone;
    private String password;
    
    
    public UserBean() {
    }
    
    
    public String createUser (){
        User user = new User(Integer.SIZE, firstname, lastname, username, email, phone, password);
        userController.create(user);
        return "login?faces-redirect=true";
    }

    public List<User> getUser () {
        return userController.findAll();
    }
    
    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
