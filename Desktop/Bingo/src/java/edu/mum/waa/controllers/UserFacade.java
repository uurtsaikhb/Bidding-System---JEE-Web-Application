/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.waa.controllers;

import edu.mum.waa.models.User;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author uurtsaikh
 */
@Stateless
public class UserFacade extends AbstractFacade<User> implements UserFacadeLocal {

    @PersistenceContext(unitName = "BingoPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UserFacade() {
        super(User.class);
    }

    @Override
    public User findByUserNameAndPassword(String username, String password) {

        Query query = em.createNamedQuery("User.findByUsernameAndPassword", User.class);
        query.setParameter("username", username);
        query.setParameter("password", password);

        return (User)query.getSingleResult();
    }

}
