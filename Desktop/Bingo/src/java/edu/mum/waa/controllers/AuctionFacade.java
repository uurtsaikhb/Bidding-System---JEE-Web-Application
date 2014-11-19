/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.waa.controllers;

import edu.mum.waa.models.Auction;
import edu.mum.waa.models.Item;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author uurtsaikh
 */
@Stateless
public class AuctionFacade extends AbstractFacade<Auction> implements AuctionFacadeLocal {
    @PersistenceContext(unitName = "BingoPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AuctionFacade() {
        super(Auction.class);
    }
    
    @Override
    public Auction findByItemId(Item item) {

        Query query = em.createNamedQuery("Auction.findByItemId", Auction.class);
        query.setParameter("itemId", item);

        return (Auction)query.getSingleResult();
    }
    
}
