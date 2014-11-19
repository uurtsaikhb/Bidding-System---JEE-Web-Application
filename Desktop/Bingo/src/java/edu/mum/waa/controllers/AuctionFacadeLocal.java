/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.waa.controllers;

import edu.mum.waa.models.Auction;
import edu.mum.waa.models.Item;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author uurtsaikh
 */
@Local
public interface AuctionFacadeLocal {

    void create(Auction auction);

    void edit(Auction auction);

    void remove(Auction auction);

    Auction find(Object id);

    List<Auction> findAll();

    List<Auction> findRange(int[] range);
    
    Auction findByItemId(Item item);

    int count();
    
}
