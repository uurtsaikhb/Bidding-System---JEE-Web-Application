/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.waa.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author uurtsaikh
 */
public class Auction {

    private Date startingDate;
    private int duration;
    private Date endDate;
    private String state; // past/actives
    private double startingPrice;
    private double buyoutPrice;
    private double hammerPrice;
    private double stepPrice;
    private Item item;
    private User seller;
    private List<Bid> bids;

    public Auction() {
        this.bids = new ArrayList<>();
    }

    /**
     * @return the startingDate
     */
    public Date getStartingDate() {
        return startingDate;
    }

    /**
     * @param startingDate the startingDate to set
     */
    public void setStartingDate(Date startingDate) {
        this.startingDate = startingDate;
    }

    /**
     * @return the duration
     */
    public int getDuration() {
        return duration;
    }

    /**
     * @param duration the duration to set
     */
    public void setDuration(int duration) {
        this.duration = duration;
    }

    /**
     * @return the endDate
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * @param endDate the endDate to set
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    /**
     * @return the state
     */
    public String getState() {
        return state;
    }

    /**
     * @param state the state to set
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * @return the startingPrice
     */
    public double getStartingPrice() {
        return startingPrice;
    }

    /**
     * @param startingPrice the startingPrice to set
     */
    public void setStartingPrice(double startingPrice) {
        this.startingPrice = startingPrice;
    }

    /**
     * @return the buyoutPrice
     */
    public double getBuyoutPrice() {
        return buyoutPrice;
    }

    /**
     * @param buyoutPrice the buyoutPrice to set
     */
    public void setBuyoutPrice(double buyoutPrice) {
        this.buyoutPrice = buyoutPrice;
    }

    /**
     * @return the hammerPrice
     */
    public double getHammerPrice() {
        return hammerPrice;
    }

    /**
     * @param hammerPrice the hammerPrice to set
     */
    public void setHammerPrice(double hammerPrice) {
        this.hammerPrice = hammerPrice;
    }

    /**
     * @return the stepPrice
     */
    public double getStepPrice() {
        return stepPrice;
    }

    /**
     * @param stepPrice the stepPrice to set
     */
    public void setStepPrice(double stepPrice) {
        this.stepPrice = stepPrice;
    }

    /**
     * @return the item
     */
    public Item getItem() {
        return item;
    }

    /**
     * @param item the item to set
     */
    public void setItem(Item item) {
        this.item = item;
    }

    /**
     * @return the seller
     */
    public User getSeller() {
        return seller;
    }

    /**
     * @param seller the seller to set
     */
    public void setSeller(User seller) {
        this.seller = seller;
    }

    /**
     * @return the bids
     */
    public List<Bid> getBids() {
        return bids;
    }

    /**
     * @param bids the bids to set
     */
    public void setBids(List<Bid> bids) {
        this.bids = bids;
    }

}
