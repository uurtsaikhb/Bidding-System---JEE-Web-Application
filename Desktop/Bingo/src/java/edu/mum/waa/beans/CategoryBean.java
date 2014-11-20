/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.waa.beans;

import edu.mum.waa.controllers.AuctionFacadeLocal;
import edu.mum.waa.controllers.CategoryFacadeLocal;
import edu.mum.waa.controllers.ItemFacadeLocal;
import edu.mum.waa.filter.Util;
import edu.mum.waa.models.Auction;
import edu.mum.waa.models.Category;
import edu.mum.waa.models.Item;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;

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
    @EJB
    ItemFacadeLocal itemController;
    @EJB
    AuctionFacadeLocal auctionController;
    
    
    private List<Category> categories;
    private List<Item> items;
    private int categoryId = 0;
    
    public CategoryBean() {
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }
    
    public String selectedCategory()
    {
        String id = FacesContext.getCurrentInstance().
		getExternalContext().getRequestParameterMap().get("hidden1");
	setCategoryId(Integer.parseInt(id));
        return "categoryItems";
    }
    
    public List<Category>  getCategories (){
        return categoryController.findAll();
    }
    
    public List<Item> getItems()
    {
        items = new ArrayList<>();
        for(Item ite: itemController.findByCategory((Category)categoryController.find(categoryId)))
        {
            Auction auction = auctionController.findByItemId(ite);
            if(auction.getStatus() == Util.AUCTION_STATUS_STARTED)
            {
                items.add(ite);
            }
        }
        return items;
    }
    
    public Auction getAuction(int auctionId)
    {
        return auctionController.find(auctionId);
    }
    
  
    
}
