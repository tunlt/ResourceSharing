/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunlt.category;

import java.io.Serializable;

/**
 *
 * @author Tu
 */
public class CategoryDTO implements Serializable{
    private String categoryID;
    private String categoryName;

    public CategoryDTO() {
    }

    public CategoryDTO(String CategoryName) {
        this.categoryName = CategoryName;
    }

    public CategoryDTO(String categoryID, String CategoryName) {
        this.categoryID = categoryID;
        this.categoryName = CategoryName;
    }

    public String getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(String categoryID) {
        this.categoryID = categoryID;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String CategoryName) {
        this.categoryName = CategoryName;
    }
    
}
