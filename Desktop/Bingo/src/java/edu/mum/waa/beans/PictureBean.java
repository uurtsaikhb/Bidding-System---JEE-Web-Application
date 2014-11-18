/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.waa.beans;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;

/**
 *
 * @author uurtsaikh
 */
@Named(value = "pictureBean")
@SessionScoped
public class PictureBean implements Serializable {

    /**
     * Creates a new instance of PictureBean
     */
    public PictureBean() {
    }
    
}
