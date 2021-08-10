/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tunlt.BookingDetail;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.naming.NamingException;
import tunlt.utils.DBHelp;

/**
 *
 * @author Tu
 */
public class BookingDetailDAO implements Serializable {

    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    List<BookingDetailDTO> list;

    public boolean insertBookingDetail(String itemid, String censorname, String responeMessage, int bookingid) throws NamingException, SQLException {
        try {
            con = DBHelp.makeConnection();
            String sql = "insert into tblBookingDetail(itemid,censorname,responemsg,bookingid,statusDetail)\n"
                    + "values(?,?,?,?,1);";
            ps = con.prepareStatement(sql);
            ps.setString(1, itemid);
            ps.setString(2, censorname);
            ps.setString(3, responeMessage);
            ps.setInt(4, bookingid);
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
}
