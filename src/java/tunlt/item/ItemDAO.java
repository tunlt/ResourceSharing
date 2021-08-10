/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunlt.item;

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
public class ItemDAO implements Serializable {

    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    List<ItemDTO> list;

    public List<ItemDTO> countPagebyItem(String name, String cate) throws NamingException, SQLException {
        try {
            if (cate.equals("all")) {
                if (name.equals("")) {
                    con = DBHelp.makeConnection();
                    String sql = "SELECT itemid, itemname, color, tblCategories.catename, quantity\n"
                            + "FROM tblItem join tblCategories on tblCategories.categoryid = tblItem.categoryid \n"
                            + "where quantity > 0  "
                            + "Order by itemname DESC ";
                    ps = con.prepareStatement(sql);
                    rs = ps.executeQuery();
                    list = new ArrayList<>();
                    while (rs.next()) {
                        ItemDTO itemdto = new ItemDTO(rs.getString("itemid"),
                                rs.getString("itemname"),
                                rs.getString("color"), 
                                rs.getInt("quantity"),
                                rs.getString("catename"));
                        list.add(itemdto);
                    }

                } else {
                    con = DBHelp.makeConnection();
                    String sql = "SELECT itemid, itemname, color, tblCategories.catename, quantity \n"
                            + "FROM tblItem join tblCategories on tblCategories.categoryid = tblItem.categoryid \n"
                            + "where itemname like ? and quantity > 0  "
                            + "Order by itemname DESC ";
                    ps = con.prepareStatement(sql);
                    ps.setString(1, "%" + name + "%");
                    rs = ps.executeQuery();
                    list = new ArrayList<>();
                    while (rs.next()) {
                        ItemDTO itemdto = new ItemDTO(rs.getString("itemid"),
                                rs.getString("itemname"),
                                rs.getString("color"),
                                rs.getInt("quantity"), 
                                rs.getString("catename"));
                        list.add(itemdto);
                    }
                }
            } else if (!cate.equals("all")) {
                if (!name.equals("")) {
                    con = DBHelp.makeConnection();
                    String sql = "SELECT itemid, itemname, color, tblCategories.catename, quantity \n"
                            + "FROM tblItem join tblCategories on tblCategories.categoryid = tblItem.categoryid \n"
                            + "where itemname like ? and quantity > 0 and tblCategories.catename like ? "
                            + "Order by itemname DESC ";
                    ps = con.prepareStatement(sql);
                    ps.setString(1, "%" + name + "%");
                    ps.setString(2, "%" + cate + "%");
                    rs = ps.executeQuery();
                    list = new ArrayList<>();
                    while (rs.next()) {
                        ItemDTO itemdto = new ItemDTO(rs.getString("itemid"),
                                rs.getString("itemname"),
                                rs.getString("color"),
                                rs.getInt("quantity"),
                                rs.getString("catename"));
                        list.add(itemdto);
                    }
                } else {
                    con = DBHelp.makeConnection();
                    String sql = "SELECT itemid, itemname, color, tblCategories.catename, quantity\n"
                            + "FROM tblItem join tblCategories on tblCategories.categoryid = tblItem.categoryid \n"
                            + "where quantity > 0 and tblCategories.catename like ? "
                            + "Order by itemname DESC  ";
                    ps = con.prepareStatement(sql);
                    ps.setString(1, "%" + cate + "%");

                    rs = ps.executeQuery();
                    list = new ArrayList<>();
                    while (rs.next()) {
                        ItemDTO itemdto = new ItemDTO(rs.getString("itemid"),
                                rs.getString("itemname"), 
                                rs.getString("color"), 
                                rs.getInt("quantity"), 
                                rs.getString("catename"));
                        list.add(itemdto);
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

    public List<ItemDTO> getItemByPage(String name, String cate, int index, int size) throws NamingException, SQLException {
        int beginsize = size * (index - 1) + 1;
        int endsize = size * index;
        try {
            if (cate.equals("all")) {
                if (name.equals("")) {
                    con = DBHelp.makeConnection();
                    String sql = "with x as (select ROW_NUMBER() over (order by itemname ASC) as r,itemid, itemname, color, tblCategories.catename, quantity\n"
                            + "FROM tblItem join tblCategories on tblCategories.categoryid = tblItem.categoryid \n"
                            + "where quantity > 0)\n"
                            + "select * from x where r between ? and ?";
                    ps = con.prepareStatement(sql);
                    ps.setInt(1, beginsize);
                    ps.setInt(2, endsize);
                    rs = ps.executeQuery();
                    list = new ArrayList<>();
                    while (rs.next()) {
                        ItemDTO itemdto = new ItemDTO(rs.getString("itemid"),
                                rs.getString("itemname"), 
                                rs.getString("color"), 
                                rs.getInt("quantity"), 
                                rs.getString("catename"));
                        list.add(itemdto);
                    }

                } else {
                    con = DBHelp.makeConnection();
                    String sql = "with x as (select ROW_NUMBER() over (order by itemname ASC) as r,itemid, itemname, color, tblCategories.catename, quantity\n"
                            + "FROM tblItem join tblCategories on tblCategories.categoryid = tblItem.categoryid \n"
                            + "where quantity > 0 and itemname like ?)\n"
                            + "select * from x where r between ? and ?";
                    ps = con.prepareStatement(sql);
                    ps.setString(1, "%" + name + "%");
                    ps.setInt(2, beginsize);
                    ps.setInt(3, endsize);
                    rs = ps.executeQuery();
                    list = new ArrayList<>();
                    while (rs.next()) {
                        ItemDTO itemdto = new ItemDTO(rs.getString("itemid"),
                                rs.getString("itemname"),
                                rs.getString("color"),
                                rs.getInt("quantity"),
                                rs.getString("catename"));
                        list.add(itemdto);
                    }
                }
            } else if (!cate.equals("all")) {
                if (!name.equals("")) {
                    con = DBHelp.makeConnection();
                    String sql = "with x as (select ROW_NUMBER() over (order by itemname ASC) as r,itemid, itemname, color, tblCategories.catename, quantity\n"
                            + "FROM tblItem join tblCategories on tblCategories.categoryid = tblItem.categoryid \n"
                            + "where quantity > 0 and itemname like ? and tblCategories.catename like ?)\n"
                            + "select * from x where r between ? and ? ";
                    ps = con.prepareStatement(sql);
                    ps.setString(1, "%" + name + "%");
                    ps.setString(2, "%" + cate + "%");
                    ps.setInt(3, beginsize);
                    ps.setInt(4, endsize);
                    rs = ps.executeQuery();
                    list = new ArrayList<>();
                    while (rs.next()) {
                        ItemDTO itemdto = new ItemDTO(rs.getString("itemid"),
                                rs.getString("itemname"), 
                                rs.getString("color"), 
                                rs.getInt("quantity"), 
                                rs.getString("catename"));
                        list.add(itemdto);
                    }
                } else {
                    con = DBHelp.makeConnection();
                    String sql = "with x as (select ROW_NUMBER() over (order by itemname ASC) as r,itemid, itemname, color, tblCategories.catename, quantity\n"
                            + "FROM tblItem join tblCategories on tblCategories.categoryid = tblItem.categoryid \n"
                            + "where quantity > 0 and tblCategories.catename like ? )\n"
                            + "select * from x where r between ? and ?";
                    ps = con.prepareStatement(sql);
                    ps.setString(1, "%" + cate + "%");
                    ps.setInt(2, beginsize);
                    ps.setInt(3, endsize);
                    rs = ps.executeQuery();
                    list = new ArrayList<>();
                    while (rs.next()) {
                        ItemDTO itemdto = new ItemDTO(rs.getString("itemid"),
                                rs.getString("itemname"), 
                                rs.getString("color"),
                                rs.getInt("quantity"), 
                                rs.getString("catename"));
                        list.add(itemdto);
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

    public ItemDTO getItembyID(String itemid) throws NamingException, SQLException {
        ItemDTO item = null;
        try {
            con = DBHelp.makeConnection();
            String sql = "select itemid, itemname, color, tblCategories.catename, quantity\n"
                    + "from tblItem join tblCategories on tblCategories.categoryid = tblItem.categoryid\n"
                    + "where itemid = '" + itemid + "'";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                item = new ItemDTO(rs.getString("itemid"), 
                        rs.getString("itemname"), 
                        rs.getString("color"), 
                        rs.getInt("quantity"),
                        rs.getString("catename"));
            }
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return item;
    }

    public int getQuantityByItemID(String itemid) throws NamingException, SQLException {
        try {
            con = DBHelp.makeConnection();
            String sql = "select quantity\n"
                    + "from tblItem \n"
                    + "where itemid = '" + itemid + "'";
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("quantity");
            }
        } finally {
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return -1;
    }

    public boolean UpdateQuantitybyItemID(String itemid, int quantity) throws NamingException, SQLException {
        try {
            con = DBHelp.makeConnection();
            String sql = "Update tblItem\n"
                    + "set quantity = ? \n"
                    + "where itemid = '" + itemid + "'";
            ps = con.prepareStatement(sql);
            ps.setInt(1, quantity);
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

    public int countPage(List<ItemDTO> list) throws NamingException, SQLException {
        return list.size();
    }

}
