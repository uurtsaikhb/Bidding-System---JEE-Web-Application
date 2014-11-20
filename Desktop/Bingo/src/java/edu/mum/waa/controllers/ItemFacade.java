/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.waa.controllers;

import edu.mum.waa.models.Category;
import edu.mum.waa.models.Item;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author uurtsaikh
 */
@Stateless
public class ItemFacade extends AbstractFacade<Item> implements ItemFacadeLocal {
    @PersistenceContext(unitName = "BingoPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ItemFacade() {
        super(Item.class);
    }

    @Override
    public List<Item> findByCategory(Category category) 
    {
        Query query = em.createNamedQuery("Item.finByCategoryId", Item.class);
        query.setParameter("categoryId", category);

        return (List<Item>)query.getResultList();
    }

    @Override
    public List<Item> findForIndex() {
        Query query = em.createNamedQuery("Item.findForIndex", Item.class);

        return (List<Item>)query.getResultList();
    }
    
}
