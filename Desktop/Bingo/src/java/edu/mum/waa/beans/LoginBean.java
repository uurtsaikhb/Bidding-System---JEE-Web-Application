    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.waa.beans;

import edu.mum.waa.controllers.UserFacadeLocal;
import edu.mum.waa.filter.Util;
import edu.mum.waa.models.User;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

/**
 *
 * @author mandal
 */
@Named
@RequestScoped
public class LoginBean implements Serializable {

    @EJB
    UserFacadeLocal userController;

    private String userName;
    private String userPassword;
    private boolean isLoggedIn;
    private User user;
    
    
    public User getUser()
    {
        return Util.getUser();
    }
    

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
        return checkLogin();
    }

    public String signOut() {
        isLoggedIn = false;

        HttpSession session = Util.getSession();
        session.invalidate();
        return "login";
    }

    public boolean isLoggedIn() {
        return isLoggedIn;
    }

    public String checkLogin() {
        System.out.println("USER");
        User _user = userController.findByUserNameAndPassword(userName, userPassword);
        if(_user != null){
            if (userName.equals(_user.getUsername()) && userPassword.equals(_user.getPassword())) 
            {
                System.out.println("Logged in");
                HttpSession session = Util.getSession();
                session.setAttribute("username", userName);
                session.setAttribute("user", _user);
                isLoggedIn = true;
                return "profile?faces-redirect=true";
            }
        }
        // Set login ERROR
        FacesMessage msg = new FacesMessage("Email Address or Password were not found!!!", "ERROR MSG");
        msg.setSeverity(FacesMessage.SEVERITY_ERROR);
        FacesContext.getCurrentInstance().addMessage(null, msg);

        return "login?faces-redirect=true";
    }
}
