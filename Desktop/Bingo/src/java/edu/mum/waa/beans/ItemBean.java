/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.waa.beans;

import edu.mum.waa.controllers.CategoryFacadeLocal;
import edu.mum.waa.controllers.ItemFacadeLocal;
import edu.mum.waa.controllers.PictureFacadeLocal;
import edu.mum.waa.models.Item;
import edu.mum.waa.models.Picture;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.primefaces.event.FileUploadEvent;

/**
 *
 * @author uurtsaikh
 */
@Named(value = "itemBean")
@SessionScoped
public class ItemBean implements Serializable {

    /**
     * Creates a new instance of ItemBean
     */
    
    @EJB
    ItemFacadeLocal itemController; // you can create, update, find items with this controller class.
    @EJB
    PictureFacadeLocal pictureController;
    
    @EJB
    CategoryFacadeLocal categoryController;
    
    
    private String name;
    private String description;
    private int categoryId;
    private List<File> files = new ArrayList<>();
    
    private String destination = "/Users/javkhlant/Downloads/tmp/";
    
    
    /*
        this List stores user's items
    */
    
    private List<Item> userItems;
    
    private Item chosenItem; // chosen item  for make auction
    
    public ItemBean() {
    }
    
    public void upload(FileUploadEvent event) {
        FacesMessage msg = new FacesMessage("Success! ", event.getFile().getFileName() + " is uploaded.");
        FacesContext.getCurrentInstance().addMessage(null, msg);
        try {
            copyFile(event.getFile().getFileName(), event.getFile().getInputstream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void copyFile(String fileName, InputStream in) {
        try {
            File file = new File(destination + fileName);
            OutputStream out = new FileOutputStream(file);
            int read = 0;
            byte[] bytes = new byte[1024];
            while ((read = in.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }
            files.add(file);
            in.close();
            out.flush();
            out.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public String createItem (){
        Item item = new Item(Integer.SIZE, name, description);
        item.setCategoryId(categoryController.find(categoryId));
        itemController.create(item);
        for(File file : files) {
            Picture picture = new Picture(file.getPath(), item);
            pictureController.create(picture);
        }
        return "myItemList";
    }

    /* this method returns user's all items as  a List */
    public List<Item> getUserItems () {
//        userItems = new ArrayList<>();
//        // get items from database using logged user.
//        for (Item i : itemController.findAll()) {
//            if (i.get)
//        }
//        userItems.add(null)
        return itemController.findAll();
    }
    
    /*
        this method creates auction on item. 
    */
    
    public String createAuction (int itemId){
        
        chosenItem = itemController.find(itemId);

        return "createAuction";
    }
    
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }    

    public Item getChosenItem() {
        return chosenItem;
    }

    public void setChosenItem(Item chosenItem) {
        this.chosenItem = chosenItem;
    }
    
}
