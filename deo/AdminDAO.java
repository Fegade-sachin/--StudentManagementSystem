package deo;

import util.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AdminDAO {

    public boolean login(String username, String password){
    

        boolean status = false;

        try {

            Connection con = DBConnection.getConnection();
            String sql = "SELECT * FROM ADMIN WHERE LOWER(USERNAME)=LOWER(?) AND PASSWORD=?";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, username);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if(rs.next()){
                status = true;   // login success
            }
      
        } catch(Exception e){
            e.printStackTrace();
        }

        return status;
    }
};