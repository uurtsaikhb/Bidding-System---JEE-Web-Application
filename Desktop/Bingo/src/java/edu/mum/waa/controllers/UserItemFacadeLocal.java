/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.waa.controllers;

import edu.mum.waa.models.UserItem;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author uurtsaikh
 */
@Local
public interface UserItemFacadeLocal {

    void create(UserItem userItem);

    void edit(UserItem userItem);

    void remove(UserItem userItem);

    UserItem find(Object id);

    List<UserItem> findAll();

    List<UserItem> findRange(int[] range);

    int count();
    
}
