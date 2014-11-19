/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.waa.beans;

import edu.mum.waa.controllers.AuctionFacadeLocal;
import edu.mum.waa.controllers.ItemFacadeLocal;
import edu.mum.waa.filter.Util;
import edu.mum.waa.models.Auction;
import edu.mum.waa.models.User;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.Date;
import javax.ejb.EJB;

/**
 *
 * @author uurtsaikh
 */
@Named(value = "auctionBean")
@SessionScoped
public class AuctionBean implements Serializable {

    /**
     * Creates a new instance of AuctionBean
     */
    @EJB
    AuctionFacadeLocal auctionController;

    @EJB
    ItemFacadeLocal itemController;
    /*
     this.startingDate = startingDate;
     this.endDate = endDate;
     this.status = status;
     this.startingPrice = startingPrice;
     this.buyoutPrice = buyoutPrice;
     this.stepPrice = stepPrice;
     this.hammerPrice = hammerPrice;
     this.sellerId = sellerId;
     */
    private Date startingDate;
    private Date endDate;
    private int status;
    private float startingPrice;
    private float buyoutPrice;
    private float stepPrice;
    private float hammerPrice;
    private User seller;

    public AuctionBean() {
    }

    public String createAuction(int itemId, int userId) {

        Auction auction = new Auction(Integer.SIZE);
        auction.setStartingDate(startingDate);
        auction.setEndDate(endDate);
        auction.setStartingPrice(startingPrice);
        auction.setBuyoutPrice(buyoutPrice);
        auction.setStepPrice(stepPrice);
        auction.setItemId(itemController.find(itemId));
        auction.setSellerId(userId);

        auctionController.create(auction);

        return "index?faces-redirect=true"; // returns your auction list. 
    }

    public Date getStartingDate() {
        return startingDate;
    }

    public void setStartingDate(Date startingDate) {
        this.startingDate = startingDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public float getStartingPrice() {
        return startingPrice;
    }

    public void setStartingPrice(float startingPrice) {
        this.startingPrice = startingPrice;
    }

    public float getBuyoutPrice() {
        return buyoutPrice;
    }

    public void setBuyoutPrice(float buyoutPrice) {
        this.buyoutPrice = buyoutPrice;
    }

    public float getStepPrice() {
        return stepPrice;
    }

    public void setStepPrice(float stepPrice) {
        this.stepPrice = stepPrice;
    }

    public float getHammerPrice() {
        return hammerPrice;
    }

    public void setHammerPrice(float hammerPrice) {
        this.hammerPrice = hammerPrice;
    }

    public User getSeller() {
        return seller;
    }

    public void setSeller(User seller) {
        this.seller = seller;
    }
}
