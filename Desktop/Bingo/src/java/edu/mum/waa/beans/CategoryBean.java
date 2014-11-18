/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.waa.beans;

import edu.mum.waa.controllers.CategoryFacadeLocal;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.ejb.EJB;

/**
 *
 * @author uurtsaikh
 */
@Named(value = "categoryBean")
@SessionScoped
public class CategoryBean implements Serializable {

    /**
     * Creates a new instance of CategoryBean
     */
    
    @EJB
    CategoryFacadeLocal categoryController;
    
    public CategoryBean() {
    }
    
}
