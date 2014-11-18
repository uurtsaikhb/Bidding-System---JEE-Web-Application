/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.waa.beans;

import edu.mum.waa.controllers.CategoryFacadeLocal;
import edu.mum.waa.models.Category;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author uurtsaikh
 */
@Named(value = "categoryBean")
@RequestScoped
public class CategoryBean implements Serializable {

    /**
     * Creates a new instance of CategoryBean
     */
    @EJB
    CategoryFacadeLocal categoryController;
    
    private List<Category> categories;
    
    public CategoryBean() {
    }
    
    public List<Category>  getCategories (){
        return categoryController.findAll();
    }
  
    
}
