/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.waa.controllers;

import edu.mum.waa.models.Bid;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author uurtsaikh
 */
@Local
public interface BidFacadeLocal {

    void create(Bid bid);

    void edit(Bid bid);

    void remove(Bid bid);

    Bid find(Object id);

    List<Bid> findAll();

    List<Bid> findRange(int[] range);

    int count();
    
}
