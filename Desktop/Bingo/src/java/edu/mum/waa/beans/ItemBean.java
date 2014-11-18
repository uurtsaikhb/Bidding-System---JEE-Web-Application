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
    
    public ItemBean() {
    }
    
    
    public String createItem (){
        Item item = new Item(Integer.SIZE, name, description);
        item.setCategoryId(categoryController.find(categoryId));
        itemController.create(item);
        return "index";
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
