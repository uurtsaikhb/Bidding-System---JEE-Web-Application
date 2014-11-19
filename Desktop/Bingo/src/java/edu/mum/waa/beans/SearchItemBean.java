/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.waa.beans;

import edu.mum.waa.controllers.ItemFacadeLocal;
import edu.mum.waa.controllers.PictureFacadeLocal;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.ejb.EJB;
import edu.mum.waa.models.Item;
import java.util.ArrayList;
import java.util.List;

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

    @EJB
    PictureFacadeLocal pictureController;

    private String searchText;
    private List<Item> resultItems;

    public SearchItemBean() {
    }

    public List<Item> getResultItems() {

        resultItems = new ArrayList<Item>();
        for (Item i : itemController.findAll()) {
            if (i.getName().toLowerCase().contains(searchText.toString())
                    || i.getDescription().toLowerCase().contains(searchText.toLowerCase())) {
                resultItems.add(i);
            }

        }

        return resultItems;
    }

    public String getPhotos(int itemId) {

//        try{
//            return pictureController.find(itemId).getPath();
//        }catch(Exception e){
//            System.out.println(e.toString());
//            return "resources/uploads/black.png";
//        }
        System.out.println(itemId);
//        return pictureController.find(itemId).getPath();
        return "resources/uploads/pruis.jpg";
    }

    public String getSearchText() {
        
        return searchText;
    }

    public void setSearchText(String searchText) {
        this.searchText = searchText;
    }
}
