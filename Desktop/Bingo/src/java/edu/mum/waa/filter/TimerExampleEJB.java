/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.waa.filter;

import edu.mum.waa.controllers.AuctionFacadeLocal;
import edu.mum.waa.models.Auction;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;

import javax.ejb.Schedule;
import javax.ejb.Stateless;

/**
 *
 * @author mandal
 */

@Stateless
public class TimerExampleEJB {

    @EJB
    AuctionFacadeLocal auctionController;
    
    private final Logger log = Logger.getLogger(TimerExampleEJB.class.getName());

    @Schedule(minute="*/1", hour="*", persistent=false)
    public void runEveryMinute() 
    {
        log.log(Level.INFO, "running every minute .. now it's: " + new Date().toString());
        System.out.println("HERE ONE MINUTE");
        Date now = new Date();
        for(Auction auction: auctionController.findAll())
        {
            
            if (auction.getEndDate().before(now))
            {
                
                auction.setStatus(Util.AUCTION_STATUS_FINISHED);
                auctionController.edit(auction);
            }
            else 
            if (auction.getStartingDate().before(now))
            {
                auction.setStatus(Util.AUCTION_STATUS_STARTED);
                auctionController.edit(auction);
            }
            else 
            {
                auction.setStatus(Util.AUCTION_STATUS_NOT_STARTED);
                auctionController.edit(auction);
            }
        }
    }
}
