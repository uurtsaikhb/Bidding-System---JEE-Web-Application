/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.waa.controllers;

import edu.mum.waa.models.Category;
import edu.mum.waa.models.User;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import edu.mum.waa.models.Item;

/**
 *
 * @author uurtsaikh
 */
@Named("itemBean")
@SessionScoped
public class ItemBean implements Serializable {
    
    private String itemName;
    private String itemDesc;
    private User owner;
    private String [] itemPictures;
    
    
    public String createItem (){
        
        Item item = new Item(itemName, itemName, itemPictures, new Category("electronics"), owner);
        
        return "index";
    }
    
}
