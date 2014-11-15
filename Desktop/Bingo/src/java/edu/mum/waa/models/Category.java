/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.waa.models;

/**
 *
 * @author mandal
 */
public class Category {
    
    private String name;
    private Category parent_category;

    public Category(String name, Category parent_category) {
        this.name = name;
        this.parent_category = parent_category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getParent_category() {
        return parent_category;
    }

    public void setParent_category(Category parent_category) {
        this.parent_category = parent_category;
    }
    
    
    
}
