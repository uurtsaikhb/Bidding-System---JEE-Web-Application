/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.waa.beans;

import edu.mum.waa.controllers.ItemFacadeLocal;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.ejb.EJB;

/**
 *
 * @author uurtsaikh
 */
@Named(value = "searchItemBean")
@SessionScoped
public class SearchItemBean implements Serializable {

    /**
     * Creates a new instance of SearchItemBean
     */
    @EJB
    ItemFacadeLocal itemController;
    
    private String searchText;

    public SearchItemBean() {
    }
    
    public String searchItem () {
        System.out.println("Search Result: " + searchText);
        return "/searchResult";
    }

    public String getSearchText() {
        return searchText;
    }

    public void setSearchText(String searchText) {
        this.searchText = searchText;
    }
}
