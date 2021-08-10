/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunlt.user;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.NamingException;
import tunlt.utils.DBHelp;

/**
 *
 * @author Tu
 */
public class UserDAO implements Serializable {

    public Connection con;
    public PreparedStatement ps;
    public ResultSet rs;

    public boolean checkLogin(String username, String password) throws NamingException, SQLException {
        try {
            con = DBHelp.makeConnection();
            String sql = "SELECT username "
                    + "FROM tblUser "
                    + "WHERE username = ? and password = ?";
            ps = con.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }

    public UserDTO getUser(String username) throws NamingException, SQLException {
        UserDTO dto = new UserDTO();
        try {
            con = DBHelp.makeConnection();
            String sql = "SELECT fullname, isManager, status, email "
                    + "FROM tblUser "
                    + "WHERE username = ? ";
            ps = con.prepareStatement(sql);
            ps.setString(1, username);
            rs = ps.executeQuery();
            String fullname = "";
            String status = "";
            String email = "";
            if (rs.next()) {
                fullname = rs.getString("fullname");
                boolean role = rs.getBoolean("isManager");
                status = rs.getString("status");
                email = rs.getString("email");
                dto = new UserDTO(username, fullname, role, status, email);
            }
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return dto;
    }

    public int insertUser(UserDTO dto) throws SQLException {
        try {
            con = DBHelp.makeConnection();
            if (con != null) {
                String sql = "insert into tblUser values(?,?,?,?,?,?,?,?,?)";
                ps = con.prepareStatement(sql);
                ps.setString(1, dto.getUsername());
                ps.setString(2, dto.getPassword());
                ps.setString(3, dto.getFullname());
                ps.setString(4, dto.getAddress());
                ps.setString(5, dto.getEmail());
                ps.setInt(6, dto.getPhone());
                ps.setString(7, dto.getCreatedate());
                ps.setString(8, "new");
                ps.setBoolean(9, false);
                return ps.executeUpdate();
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return -1;
    }

    public int UpdateUserStatus(String username, String status) throws SQLException {
        try {
            con = DBHelp.makeConnection();
            if (con != null) {
                String sql = "update tblUser\n"
                        + "set status = ?\n"
                        + "where username = ?";
                ps = con.prepareStatement(sql);
                ps.setString(1, status);
                ps.setString(2, username);
                return ps.executeUpdate();
            }
        } catch (Exception e) {
            System.out.println(e.toString());
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return -1;
    }
}
