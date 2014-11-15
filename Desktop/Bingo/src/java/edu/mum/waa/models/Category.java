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
    private Category parentCategory;

    public Category(String name, Category parentCategory) {
        this.name = name;
        this.parentCategory = parentCategory;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getparentCategory() {
        return parentCategory;
    }

    public void setparentCategory(Category parentCategory) {
        this.parentCategory = parentCategory;
    }
    
    
    
}
