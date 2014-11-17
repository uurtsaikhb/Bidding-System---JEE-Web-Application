/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.waa.models;

import java.util.Date;

/**
 *
 * @author uurtsaikh
 */
public class Bid {
    
    private Auction auction;
    private User buyer;
    private double bidPrice;
    private double currentBidPrice;
    private Date biddingDate;

    /**
     * @return the auction
     */
    public Auction getAuction() {
        return auction;
    }

    /**
     * @param auction the auction to set
     */
    public void setAuction(Auction auction) {
        this.auction = auction;
    }

    /**
     * @return the buyer
     */
    public User getBuyer() {
        return buyer;
    }

    /**
     * @param buyer the buyer to set
     */
    public void setBuyer(User buyer) {
        this.buyer = buyer;
    }

    /**
     * @return the bidPrice
     */
    public double getBidPrice() {
        return bidPrice;
    }

    /**
     * @param bidPrice the bidPrice to set
     */
    public void setBidPrice(double bidPrice) {
        this.bidPrice = bidPrice;
    }

    /**
     * @return the currentBidPrice
     */
    public double getCurrentBidPrice() {
        return currentBidPrice;
    }

    /**
     * @param currentBidPrice the currentBidPrice to set
     */
    public void setCurrentBidPrice(double currentBidPrice) {
        this.currentBidPrice = currentBidPrice;
    }

    /**
     * @return the biddingDate
     */
    public Date getBiddingDate() {
        return biddingDate;
    }

    /**
     * @param biddingDate the biddingDate to set
     */
    public void setBiddingDate(Date biddingDate) {
        this.biddingDate = biddingDate;
    }
}
