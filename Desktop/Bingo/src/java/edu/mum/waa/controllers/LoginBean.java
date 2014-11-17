    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.waa.controllers;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author mandal
 */
@Named
@SessionScoped
public class LoginBean implements Serializable
{
    private String userName;
    private String userPassword;
    private boolean isLoggedIn;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }
    
    public String signIn()
    {
        isLoggedIn = true;
        return "index.xhtml";
    }
    
    public String signOut()
    {
        isLoggedIn = false;
        return "login";
    }

    public boolean isLoggedIn() 
    {
        return isLoggedIn;
    }
}
