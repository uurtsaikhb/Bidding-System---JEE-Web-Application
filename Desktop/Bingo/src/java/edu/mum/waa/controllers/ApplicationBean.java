/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.waa.controllers;

import edu.mum.waa.models.Category;
import edu.mum.waa.models.User;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author uurtsaikh
 */

@Named("application")
@SessionScoped
public class ApplicationBean implements Serializable {
    
    List<Category> categories;
    List<User> users;
    
    public ApplicationBean () {
    
        categories = new ArrayList<>();
            categories.add(new Category("electronics"));
            categories.add(new Category("fashion"));
            categories.add(new Category("electronics"));
            
    }
    
    public void createCatergory () {
        Category category = new Category("Electronics");
    }
    
}
