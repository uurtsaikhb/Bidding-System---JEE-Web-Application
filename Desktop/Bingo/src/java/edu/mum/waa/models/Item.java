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
public class Item {
    
    
    private int id;
    private String name;
    private String detail;
    
    private double startPrice;
    private double buyoutPrice;
    private double stepPrice;

    public Item(int id, String name, String detail, double startPrice, double buyoutPrice, double stepPrice) {
        this.id = id;
        this.name = name;
        this.detail = detail;
        this.startPrice = startPrice;
        this.buyoutPrice = buyoutPrice;
        this.stepPrice = stepPrice;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public double getStartPrice() {
        return startPrice;
    }

    public void setStartPrice(double startPrice) {
        this.startPrice = startPrice;
    }

    public double getBuyoutPrice() {
        return buyoutPrice;
    }

    public void setBuyoutPrice(double buyoutPrice) {
        this.buyoutPrice = buyoutPrice;
    }

    public double getStepPrice() {
        return stepPrice;
    }

    public void setStepPrice(double stepPrice) {
        this.stepPrice = stepPrice;
    }
    
    
    
}
