/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.waa.controllers;

import edu.mum.waa.models.User;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author uurtsaikh
 */

@Named("registerBean")
@SessionScoped
public class RegisterBean implements Serializable{
    
    private String firstname;
    
    public String createUser () {
        System.out.println("f: " + firstname);
        return "index";
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }
    
}
