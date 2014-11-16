/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.waa.models;

import java.util.Date;
import java.util.List;

/**
 *
 * @author uurtsaikh
 */
public class Auction {

    private long auctionId;

    private Date startDate;
    private Date endDate;
    private int bestBid;
    private String status;

    private double startPrice;
    private double buyoutPrice;
    private double stepPrice;

    private Item item;
    private User owner;
    private Bid bid;

    private List<User> bidders;

}
