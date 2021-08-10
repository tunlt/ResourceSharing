/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunlt.user;

import java.io.Serializable;

/**
 *
 * @author Tu
 */
public class UserDTO implements Serializable {

    private String username;
    private String password;
    private String fullname;
    private String address;
    private String email;
    private int phone;
    private String createdate;
    private String status;

    private boolean isManager;

    public UserDTO() {
    }

    public UserDTO(String username, String fullname, boolean isManager, String status, String email) {
        this.username = username;
        this.fullname = fullname;
        this.isManager = isManager;
        this.status = status;
        this.email = email;
    }

    public UserDTO(String username, String password, String fullname, String address, String email, int phone, String createdate, String status, boolean isManager) {
        this.username = username;
        this.password = password;
        this.fullname = fullname;
        this.address = address;
        this.email = email;
        this.phone = phone;
        this.createdate = createdate;
        this.status = status;
        this.isManager = isManager;
    }

   

    public UserDTO(String username, String password, String fullname, String address, String createdate, String status, boolean isManager) {
        this.username = username;
        this.password = password;
        this.fullname = fullname;
        this.address = address;
        this.createdate = createdate;
        this.status = status;
        this.isManager = isManager;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCreatedate() {
        return createdate;
    }

    public void setCreatedate(String createdate) {
        this.createdate = createdate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean isIsManager() {
        return isManager;
    }

    public void setIsManager(boolean isManager) {
        this.isManager = isManager;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

}
