/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.waa.beans;

import edu.mum.waa.controllers.ItemFacadeLocal;
import edu.mum.waa.models.Item;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
/**
 *
 * @author uurtsaikh
 */



@Named(value = "profileBean")
@RequestScoped
public class ProfileBean {

    /**
     * Creates a new instance of ProfileBean
     */
    
    @EJB
    ItemFacadeLocal itemController;
    
    
    Item chosenItem;
    
    
    
    public ProfileBean() {
    }
    
    
    public String seeItemDetails (int itemId) {
        
        chosenItem = itemController.find(itemId);
        return "createAuction?faces-redirect=true";
    }
}
