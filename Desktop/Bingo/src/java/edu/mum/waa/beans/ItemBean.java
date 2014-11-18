/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.waa.beans;

import edu.mum.waa.controllers.CategoryFacadeLocal;
import edu.mum.waa.controllers.ItemFacadeLocal;
import edu.mum.waa.models.Category;
import edu.mum.waa.models.Item;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
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
    
    @EJB
    CategoryFacadeLocal categoryController;
    
    
    private String name;
    private String description;
    private int categoryId;
    
    
    /*
        this List stores user's items
    */
    
    private List<Item> userItems;
    
    public ItemBean() {
    }
    
    /* create item and store to database */
    public String createItem (){
        Item item = new Item(Integer.SIZE, name, description);
        item.setCategoryId(categoryController.find(categoryId));
        itemController.create(item);
        return "myItemList";
    }

    /* this method returns user's all items as  a List */
    public List<Item> getUserItems () {
//        userItems = new ArrayList<>();
//        // get items from database using logged user.
//        for (Item i : itemController.findAll()) {
//            if (i.get)
//        }
//        userItems.add(null)
        return itemController.findAll();
    }
    
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }    
    
}
