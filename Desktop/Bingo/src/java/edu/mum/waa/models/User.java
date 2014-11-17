/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.waa.models;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author uurtsaikh
 */
public class User {
    
    private String firstname;
    private String lastname;
    private String username;
    private String email;
    private String phone;
    private String password;
    private List<Item> items;
    private List<Auction> auctions;

    public User() {
        this.items = new ArrayList<>();
        this.auctions = new ArrayList<>();
    }
    
    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the items
     */
    public List<Item> getItems() {
        return items;
    }

    /**
     * @param items the items to set
     */
    public void setItems(List<Item> items) {
        this.items = items;
    }

    /**
     * @return the auctions
     */
    public List<Auction> getAuctions() {
        return auctions;
    }

    /**
     * @param auctions the auctions to set
     */
    public void setAuctions(List<Auction> auctions) {
        this.auctions = auctions;
    }
}
