/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.waa.beans;

import edu.mum.waa.controllers.ItemFacadeLocal;
import edu.mum.waa.controllers.UserItemFacadeLocal;
import edu.mum.waa.filter.Util;
import edu.mum.waa.models.Item;
import edu.mum.waa.models.UserItem;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
/**
 *
 * @author uurtsaikh
 */



@Named(value = "profileBean")
@SessionScoped
public class ProfileBean implements Serializable{

    /**
     * Creates a new instance of ProfileBean
     */
    
    @EJB
    ItemFacadeLocal itemController;
    
    @EJB
    UserItemFacadeLocal userItemController;
    
    private Item selectedItem;
    
    
    
    public ProfileBean() {
    }
    
    
    public String seeItemDetails (int itemId) {
        
        selectedItem = itemController.find(itemId);

        return "createAuction?faces-redirect=true";
    }

    public List<Item> getUserItems() {
        
        List<Item> userItems = new ArrayList<>();
        // get items from database using logged user.
        List<UserItem> user_item = userItemController.findAll();
        for (UserItem ui : user_item) {
            if (Objects.equals(Util.getUser().getId(), ui.getUserId().getId())) {
                userItems.add(itemController.find(ui.getItemId()));
            }
        }
        
        return userItems;
    }
    
    
    public Item getSelectedItem() {
        return selectedItem;
    }

    public void setSelectedItem(Item selectedItem) {
        this.selectedItem = selectedItem;
    }
}
