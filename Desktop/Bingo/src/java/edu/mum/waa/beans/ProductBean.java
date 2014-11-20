/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.waa.beans;

import edu.mum.waa.controllers.AuctionFacadeLocal;
import edu.mum.waa.controllers.BidFacadeLocal;
import edu.mum.waa.controllers.ItemFacadeLocal;
import edu.mum.waa.filter.Util;
import edu.mum.waa.models.Auction;
import edu.mum.waa.models.Bid;
import edu.mum.waa.models.Item;
import java.io.IOException;
import java.io.Serializable;
import java.util.Date;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author mandal
 */
@Named(value = "productBean")
@RequestScoped
public class ProductBean implements Serializable{

    @EJB
    ItemFacadeLocal itemController;
    
    @EJB
    AuctionFacadeLocal auctionController;
    
    @EJB
    BidFacadeLocal bidController;
    
    private int itemId;
    private Item item;
    private Auction auction;
    private int myBid;
    private int auctionId;
    /**
     * Creates a new instance of ProductBean
     */
    public ProductBean() {
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }
    
    public String selectedItem()
    {
        String id = FacesContext.getCurrentInstance().
		getExternalContext().getRequestParameterMap().get("itemId");
	setItemId(Integer.parseInt(id));
        return "productShow";
    }
    
    public Item getItem()
    {
        System.out.println(itemId);
        item = itemController.find(itemId);
        return item;
    }
    
    public Auction getAuction()
    {
        auctionId = auctionController.findByItemId(item).getId();
        return auctionController.find(auctionId);
    }
    
    public int getMyBid() {
        return myBid;
    }

    public void setMyBid(int myBid) {
        this.myBid = myBid;
    }

    public int getAuctionId() {
        return auctionId;
    }

    public void setAuctionId(int auctionId) {
        this.auctionId = auctionId;
    }
    
    
    public void createBid() throws IOException
    {
        if(Util.getUser() == null)
        {
            FacesContext.getCurrentInstance().getExternalContext().redirect("login.xhtml");
        }
        Auction auct = auctionController.find(auctionId);
        Bid bid = new Bid(Integer.SIZE, auct.getId(), (int) (myBid - auct.getHammerPrice()), myBid, new Date());
        bid.setBuyerId(Util.getUser());
        bidController.create(bid);
        
        auct.setHammerPrice(myBid);
        auctionController.edit(auct);
        
    }
    
    public String takePossoblePrice()
    {
        Auction auct = auctionController.find(auctionId);
        return ((int)auct.getHammerPrice() + auct.getStepPrice()) + "";
    }
    
    public String takeNewPrice()
    {
        Auction auct = auctionController.find(auctionId);
        return (int)auct.getHammerPrice() + "";
    }
    
    public String takeMessage()
    {
        if(auctionId != 0){
		return "";
	   }else{
		return "Ajax message : Welcome ";
	   }
    }
    
}
