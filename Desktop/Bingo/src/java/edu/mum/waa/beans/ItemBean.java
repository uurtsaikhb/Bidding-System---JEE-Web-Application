/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.waa.beans;

import edu.mum.waa.controllers.ItemFacadeLocal;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.ejb.EJB;

/**
 *
 * @author uurtsaikh
 */
@Named(value = "itemBean")
@SessionScoped
public class ItemBean implements Serializable {

    /**
     * Creates a new instance of ItemBean
     */
    
    @EJB
    ItemFacadeLocal itemController; // you can create, update, find items with this controller class.
    
    public ItemBean() {
    }
    
    
}
