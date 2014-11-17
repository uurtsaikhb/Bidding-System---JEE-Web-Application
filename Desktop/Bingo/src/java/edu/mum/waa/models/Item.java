/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.waa.models;

/**
 *
 * @author uurtsaikh
 */
public class Item {
    
    private String name;
    private String description;
    private String[] pictures;
    private Category category;
    private User user;

    public Item(String name, String description, String[] pictures, Category category, User user) {
        this.name = name;
        this.description = description;
        this.pictures = pictures;
        this.category = category;
        this.user = user;
    }
    
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String[] getPictures() {
        return pictures;
    }

    public void setPictures(String[] pictures) {
        this.setPictures(pictures);
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }
}
