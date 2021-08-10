/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunlt.BookingDetail;

import java.io.Serializable;

/**
 *
 * @author Tu
 */
public class BookingDetailDTO implements Serializable{
    int bookingDetailID;
    int itemid;
    String censorname;
    int bookingid;
    String responeMessage;
    boolean status ;

    public BookingDetailDTO() {
    }

    public BookingDetailDTO(int bookingDetailID, int itemid, String censorname, int bookingid, String responeMessage, boolean status) {
        this.bookingDetailID = bookingDetailID;
        this.itemid = itemid;
        this.censorname = censorname;
        this.bookingid = bookingid;
        this.responeMessage = responeMessage;
        this.status = status;
    }

    public int getBookingDetailID() {
        return bookingDetailID;
    }

    public void setBookingDetailID(int bookingDetailID) {
        this.bookingDetailID = bookingDetailID;
    }

    public int getItemid() {
        return itemid;
    }

    public void setItemid(int itemid) {
        this.itemid = itemid;
    }

    public String getCensorname() {
        return censorname;
    }

    public void setCensorname(String censorname) {
        this.censorname = censorname;
    }

    public int getBookingid() {
        return bookingid;
    }

    public void setBookingid(int bookingid) {
        this.bookingid = bookingid;
    }

    public String getResponeMessage() {
        return responeMessage;
    }

    public void setResponeMessage(String responeMessage) {
        this.responeMessage = responeMessage;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    
}
