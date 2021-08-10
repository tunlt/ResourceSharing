/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunlt.item;

import java.io.Serializable;

/**
 *
 * @author Tu
 */
public class ItemDTO implements Serializable{
    private String itemid;
    private String itemname;
    private String color;
    private int quantity;
    private String categoryName; 

    public ItemDTO() {
    }

    public String getCategoryName() {
        return categoryName;
    }

    public ItemDTO(String itemid, String itemname, String color, int quantity, String categoryName) {
        this.itemid = itemid;
        this.itemname = itemname;
        this.color = color;
        this.quantity = quantity;
        this.categoryName = categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public ItemDTO(String itemid, String itemname, String color, int quantity) {
        this.itemid = itemid;
        this.itemname = itemname;
        this.color = color;
        this.quantity = quantity;
    }

    public String getItemid() {
        return itemid;
    }

    public void setItemid(String itemid) {
        this.itemid = itemid;
    }

    public String getItemname() {
        return itemname;
    }

    public void setItemname(String itemname) {
        this.itemname = itemname;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "ItemDTO{" + "itemid=" + itemid + ", itemname=" + itemname + ", color=" + color + ", quantity=" + quantity + ", categoryName=" + categoryName + '}';
    }

    
    
}
