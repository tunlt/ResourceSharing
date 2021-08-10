/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunlt.category;

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
public class CategoryDAO implements Serializable{
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    List<CategoryDTO> list;
    public List<CategoryDTO> getCategory() throws SQLException, NamingException{
        try{
            con = DBHelp.makeConnection();
            String sql = "select catename "
                    + "from tblCategories ";
             ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            list = new ArrayList<>();
            while (rs.next()) {
                list.add(new CategoryDTO(rs.getString("catename")));
            }
        }finally{
            if (ps != null) {
                ps.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return list;
    }
}
