/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.waa.beans;

import edu.mum.waa.controllers.CategoryFacadeLocal;
import edu.mum.waa.controllers.ItemFacadeLocal;
import edu.mum.waa.controllers.PictureFacadeLocal;
import edu.mum.waa.controllers.UserItemFacadeLocal;
import edu.mum.waa.filter.Util;
import edu.mum.waa.models.Item;
import edu.mum.waa.models.Picture;
import edu.mum.waa.models.UserItem;
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
import java.util.Objects;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import org.primefaces.event.FileUploadEvent;

/**
 *
 * @author uurtsaikh
 */
@Named(value = "itemBean")
@RequestScoped
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
    @EJB
    UserItemFacadeLocal userItemController;

    private String name;
    private String description;
    private int categoryId;
    private List<File> files = new ArrayList<>();

    private final String path = "resources" + File.separator + "uploads";
    private final ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
    private String destination = servletContext.getRealPath(File.separator + path);
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
            System.out.println(e.getMessage());
        }
    }

    public void copyFile(String fileName, InputStream in) {
        try {
            File file = new File(destination + File.separator + fileName);
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

    public String createItem (int userId){
        
        Item item = new Item(Integer.SIZE, name, description);
        item.setCategoryId(categoryController.find(categoryId));
        itemController.create(item);

        for(File file : files) {
            Picture picture = new Picture(path + File.separator + file.getName(), item);
            pictureController.create(picture);
        }

        System.out.println("USer ID : " + Util.getUser().getId());
        System.out.println("ITEM ID : " + item.getId());
//        
        UserItem userItem = new UserItem(Integer.SIZE, item.getId());
        userItem.setUserId(Util.getUser());
        userItemController.create(userItem);

        return "myItemList?faces-redirect=true"; //?faces-redirect=true
    }

    public List<Item> getUserItems() {
        
        userItems = new ArrayList<>();
        // get items from database using logged user.
        List<UserItem> user_item = userItemController.findAll();
        for (UserItem ui : user_item) {
            if (Objects.equals(Util.getUser().getId(), ui.getUserId().getId())) {
                userItems.add(itemController.find(ui.getItemId()));
            }
        }
        
        return userItems;
    }

    /*
     this method creates auction on item. 
     */
    public String createAuction(int itemId) {

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
