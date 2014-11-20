/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.waa.beans;

import edu.mum.waa.controllers.AuctionFacadeLocal;
import edu.mum.waa.controllers.ItemFacadeLocal;
import edu.mum.waa.models.Auction;
import edu.mum.waa.models.Item;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.Dependent;

/**
 *
 * @author mandal
 */
@Named(value = "homeBean")
@Dependent
public class homeBean {

    
    @EJB
    AuctionFacadeLocal auctionController;
    
    @EJB
    ItemFacadeLocal itemController;
    
    private Auction auction;
    private List<Item> items;
    
    /**
     * Creates a new instance of homeBean
     */
    public homeBean() {
    }
    
    public Auction getAuction(int auctionId)
    {
        return auctionController.find(auctionId);
    }
    
    public List<Item> getItems()
    {   
        items = new ArrayList<>();
        for(Item ite: itemController.findForIndex())
        {
            Auction auction = auctionController.findByItemId(ite);
            if(auction.getStatus() == 0)
            {
                items.add(ite);
            }
        }
        return items;
    }
    
    
}
