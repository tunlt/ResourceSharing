/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunlt.Booking;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import tunlt.utils.DBHelp;

/**
 *
 * @author Tu
 */
public class BookingDAO implements Serializable {

    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    List<BookingDTO> list;

    public boolean insertBooking(String username, String statusorder, String datebegin, String dateend, int quantity, String request, String itemname, String itemid) throws NamingException, SQLException {
        try {
            con = DBHelp.makeConnection();
            String sql = "insert into tblBooking(username,statusorder,datebook,dateend,quantity,request,itemname,itemid)\n"
                    + "values(?,?,?,?,?,?,?,?); ";
            ps = con.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, statusorder);
            ps.setString(3, datebegin);
            ps.setString(4, dateend);
            ps.setInt(5, quantity);
            ps.setString(6, request);
            ps.setString(7, itemname);
            ps.setString(8, itemid);
            if (ps.executeUpdate() > 0) {
                return true;
            }
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }

    public boolean UpdateStatusbybookingID(int bookingid, String statusorder) throws NamingException, SQLException {
        try {
            con = DBHelp.makeConnection();
            String sql = "Update tblBooking\n"
                    + "set statusorder = ? \n"
                    + "where bookingid = '" + bookingid + "'";
            ps = con.prepareStatement(sql);
            ps.setString(1, statusorder);
            if (ps.executeUpdate() > 0) {
                return true;
            }
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }

    public BookingDTO getbookingbyID(String bookingid) throws NamingException, SQLException {
        BookingDTO booking = null;
        try {
            con = DBHelp.makeConnection();
            String sql = "select bookingid,itemname,username,datebook,dateend,request,statusorder,quantity,itemid\n"
                    + "from tblBooking\n"
                    + "where bookingid = '" + bookingid + "'";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                booking = new BookingDTO(rs.getInt("bookingid"),
                         rs.getString("username"),
                         rs.getString("statusorder"),
                         rs.getString("datebook"),
                         rs.getString("dateend"),
                         rs.getString("request"),
                         rs.getString("itemname"),
                         rs.getInt("quantity"),
                         rs.getString("itemid"));
            }
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return booking;
    }

    public List<BookingDTO> getListbyStatus(String status) throws SQLException, NamingException {
        try {
            con = DBHelp.makeConnection();
            String sql = "select catename "
                    + "from tblCategories ";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            list = new ArrayList<>();
            while (rs.next()) {
                list.add(new BookingDTO(rs.getString("statusorder")));
            }
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return list;
    }

    public List<BookingDTO> ListBooking(String name, String cate) throws NamingException, SQLException {
        try {
            if (cate.equals("all")) {
                if (name.equals("")) {
                    con = DBHelp.makeConnection();
                    String sql = "SELECT itemname,username,datebook,dateend,request,statusorder,quantity,itemid,bookingid\n"
                            + "FROM tblBooking \n"
                            + "Order by datebook ASC ";
                    ps = con.prepareStatement(sql);
                    rs = ps.executeQuery();
                    list = new ArrayList<>();
                    while (rs.next()) {
                        BookingDTO booking = new BookingDTO(rs.getInt("bookingid"), rs.getString("username"),
                                rs.getString("statusorder"),
                                rs.getString("datebook"),
                                rs.getString("dateend"),
                                rs.getString("request"),
                                rs.getString("itemname"),
                                rs.getInt("quantity"),
                                rs.getString("itemid"));
                        list.add(booking);
                    }

                } else {
                    con = DBHelp.makeConnection();
                    String sql = "SELECT  itemname,username,datebook,dateend,request,statusorder,quantity,itemid,bookingid\n"
                            + "FROM tblBooking\n"
                            + "where itemname like ?\n"
                            + "Order by datebook ASC ";
                    ps = con.prepareStatement(sql);
                    ps.setString(1, "%" + name + "%");
                    rs = ps.executeQuery();
                    list = new ArrayList<>();
                    while (rs.next()) {
                        BookingDTO booking = new BookingDTO(rs.getInt("bookingid"), rs.getString("username"),
                                rs.getString("statusorder"),
                                rs.getString("datebook"),
                                rs.getString("dateend"),
                                rs.getString("request"),
                                rs.getString("itemname"),
                                rs.getInt("quantity"),
                                rs.getString("itemid"));
                        list.add(booking);
                    }
                }
            } else if (!cate.equals("all")) {
                if (!name.equals("")) {
                    con = DBHelp.makeConnection();
                    String sql = "SELECT  itemname,username,datebook,dateend,request,statusorder,quantity,bookingid,itemid\n"
                            + "FROM tblBooking  \n"
                            + "where itemname like ? and statusorder like ?\n"
                            + "Order by datebook ASC ";
                    ps = con.prepareStatement(sql);
                    ps.setString(1, "%" + name + "%");
                    ps.setString(2, cate);
                    rs = ps.executeQuery();
                    list = new ArrayList<>();
                    while (rs.next()) {
                        BookingDTO booking = new BookingDTO(rs.getInt("bookingid"), rs.getString("username"),
                                rs.getString("statusorder"),
                                rs.getString("datebook"),
                                rs.getString("dateend"),
                                rs.getString("request"),
                                rs.getString("itemname"),
                                rs.getInt("quantity"),
                                rs.getString("itemid"));
                        list.add(booking);
                    }
                } else {
                    con = DBHelp.makeConnection();
                    String sql = "SELECT  itemname,username,datebook,dateend,request,statusorder,quantity,bookingid,itemid\n"
                            + "FROM tblBooking  \n"
                            + "where statusorder = ?\n"
                            + "Order by datebook ASC ";
                    ps = con.prepareStatement(sql);
                    ps.setString(1, cate);
                    rs = ps.executeQuery();
                    list = new ArrayList<>();
                    while (rs.next()) {
                        BookingDTO booking = new BookingDTO(rs.getInt("bookingid"), rs.getString("username"),
                                rs.getString("statusorder"),
                                rs.getString("datebook"),
                                rs.getString("dateend"),
                                rs.getString("request"),
                                rs.getString("itemname"),
                                rs.getInt("quantity"),
                                rs.getString("itemid"));
                        list.add(booking);
                    }
                }
            }
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return list;
    }

    public List<BookingDTO> getBookByPage(String name, String cate, int index, int size) throws NamingException, SQLException {
        int beginsize = size * (index - 1) + 1;
        int endsize = size * index;
        try {
            if (cate.equals("all")) {
                if (name.equals("")) {
                    con = DBHelp.makeConnection();
                    String sql = "with x as (select ROW_NUMBER() over (order by datebook ASC) as r,itemname,username,datebook,dateend,request,statusorder,quantity,bookingid,itemid\n"
                            + "FROM tblBooking)\n"
                            + "select * from x where r between ? and ?";
                    ps = con.prepareStatement(sql);
                    ps.setInt(1, beginsize);
                    ps.setInt(2, endsize);
                    rs = ps.executeQuery();
                    list = new ArrayList<>();
                    while (rs.next()) {
                        BookingDTO booking = new BookingDTO(rs.getInt("bookingid"), rs.getString("username"),
                                rs.getString("statusorder"),
                                rs.getString("datebook"),
                                rs.getString("dateend"),
                                rs.getString("request"),
                                rs.getString("itemname"),
                                rs.getInt("quantity"),
                                rs.getString("itemid"));
                        list.add(booking);
                    }
                } else {
                    con = DBHelp.makeConnection();
                    String sql = "with x as (select ROW_NUMBER() over (order by datebook ASC) as r,itemname,username,datebook,dateend,request,statusorder,quantity,bookingid,itemid\n"
                            + "FROM tblBooking \n"
                            + "where itemname like ?)\n"
                            + "select * from x where r between ? and ?";
                    ps = con.prepareStatement(sql);
                    ps.setString(1, "%" + name + "%");
                    ps.setInt(2, beginsize);
                    ps.setInt(3, endsize);
                    rs = ps.executeQuery();
                    list = new ArrayList<>();
                    while (rs.next()) {
                        BookingDTO booking = new BookingDTO(rs.getInt("bookingid"), rs.getString("username"),
                                rs.getString("statusorder"),
                                rs.getString("datebook"),
                                rs.getString("dateend"),
                                rs.getString("request"),
                                rs.getString("itemname"),
                                rs.getInt("quantity"),
                                rs.getString("itemid"));
                        list.add(booking);
                    }
                }
            } else if (!cate.equals("all")) {
                if (!name.equals("")) {
                    con = DBHelp.makeConnection();
                    String sql = "with x as (select ROW_NUMBER() over (order by datebook ASC) as r,itemname,username,datebook,dateend,request,statusorder,quantity,bookingid,itemid\n"
                            + "FROM tblBooking \n"
                            + "where itemname like ? and statusorder = ?)\n"
                            + "select * from x where r between ? and ?";
                    ps = con.prepareStatement(sql);
                    ps.setString(1, "%" + name + "%");
                    ps.setString(2, cate);
                    ps.setInt(3, beginsize);
                    ps.setInt(4, endsize);
                    rs = ps.executeQuery();
                    list = new ArrayList<>();
                    while (rs.next()) {
                        BookingDTO booking = new BookingDTO(rs.getInt("bookingid"), rs.getString("username"),
                                rs.getString("statusorder"),
                                rs.getString("datebook"),
                                rs.getString("dateend"),
                                rs.getString("request"),
                                rs.getString("itemname"),
                                rs.getInt("quantity"),
                                rs.getString("itemid"));
                        list.add(booking);
                    }
                } else {
                    con = DBHelp.makeConnection();
                    String sql = "with x as (select ROW_NUMBER() over (order by datebook ASC) as r,itemname,username,datebook,dateend,request,statusorder,quantity,bookingid,itemid\n"
                            + "FROM tblBooking   \n"
                            + "where statusorder = ?)\n"
                            + "select * from x where r between ? and ?";
                    ps = con.prepareStatement(sql);
                    ps.setString(1, cate);
                    ps.setInt(2, beginsize);
                    ps.setInt(3, endsize);
                    rs = ps.executeQuery();
                    list = new ArrayList<>();
                    while (rs.next()) {
                        BookingDTO booking = new BookingDTO(rs.getInt("bookingid"), rs.getString("username"),
                                rs.getString("statusorder"),
                                rs.getString("datebook"),
                                rs.getString("dateend"),
                                rs.getString("request"),
                                rs.getString("itemname"),
                                rs.getInt("quantity"),
                                rs.getString("itemid"));
                        list.add(booking);
                    }
                }
            }
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return list;
    }

    public int countPage(List<BookingDTO> list) throws NamingException, SQLException {
        return list.size();
    }
}
