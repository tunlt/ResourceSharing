/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunlt.Booking;

import java.io.Serializable;

/**
 *
 * @author Tu
 */
public class BookingDTO implements Serializable{
    int bookingid;
    String username;
    String statusorder;
    String datebook;
    String dateend;
    String requestMessage;
    String item;
    int quantity;
    String itemid;

    public BookingDTO() {
    }

    public BookingDTO(String statusorder) {
        this.statusorder = statusorder;
    }

    public int getBookingid() {
        return bookingid;
    }

    public void setBookingid(int bookingid) {
        this.bookingid = bookingid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getStatusorder() {
        return statusorder;
    }

    public void setStatusorder(String statusorder) {
        this.statusorder = statusorder;
    }

    public String getStringbook() {
        return datebook;
    }

    public void setStringbook(String datebook) {
        this.datebook = datebook;
    }

    public String getStringend() {
        return dateend;
    }

    public void setStringend(String dateend) {
        this.dateend = dateend;
    }



    public String getItemid() {
        return itemid;
    }

    public void setItemid(String itemid) {
        this.itemid = itemid;
    }

    public BookingDTO(int bookingid, String username, String statusorder, String datebook, String dateend, String requestMessage, String item, int quantity, String itemid) {
        this.bookingid = bookingid;
        this.username = username;
        this.statusorder = statusorder;
        this.datebook = datebook;
        this.dateend = dateend;
        this.requestMessage = requestMessage;
        this.item = item;
        this.quantity = quantity;
        this.itemid = itemid;
    }

   

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    


    public String getDatebook() {
        return datebook;
    }

    public void setDatebook(String datebook) {
        this.datebook = datebook;
    }

    public String getDateend() {
        return dateend;
    }

    public void setDateend(String dateend) {
        this.dateend = dateend;
    }

    public String getRequestMessage() {
        return requestMessage;
    }

    public void setRequestMessage(String requestMessage) {
        this.requestMessage = requestMessage;
    }
    
}
