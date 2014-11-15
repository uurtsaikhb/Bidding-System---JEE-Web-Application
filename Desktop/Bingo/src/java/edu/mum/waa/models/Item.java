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
    
    private List<Deal> bids = new ArrayList<Deal>();
    private Category category;
    private Customer owner;
    
    
}
