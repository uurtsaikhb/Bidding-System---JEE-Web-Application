/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.waa.beans;

import edu.mum.waa.controllers.AuctionFacadeLocal;
import edu.mum.waa.filter.Util;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import edu.mum.waa.models.Auction;
import edu.mum.waa.models.Item;
import java.util.ArrayList;

/**
 *
 * @author uurtsaikh
 */
@Named(value = "myAuctionBean")
@SessionScoped
public class MyAuctionBean implements Serializable {

    /**
     * Creates a new instance of MyAuctionBean
     */
    
    @EJB
    AuctionFacadeLocal auctionController;
    
    private List<Auction> auctions;
    private Item item;
    private Auction auction;
    
    
    public MyAuctionBean() {
    }

    public List<Auction> getAuctions() {
        auctions = new ArrayList<>();
        for (Auction a : auctionController.findAll()){
            if(a.getSellerId() == Util.getUser().getId()){
                auctions.add(a);
            }
        }
        return auctions;
    }

    public void setAuctions(List<Auction> auctions) {
        this.auctions = auctions;
    }

    public Item getItem(int auctionId) {
        return auctionController.find(auctionId).getItemId();
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Auction getAuction() {
        return auction;
    }

    public void setAuction(Auction auction) {
        this.auction = auction;
    }
    
}
