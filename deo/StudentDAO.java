package deo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import model.Locker;
import model.Student;
import util.DBConnection;

public class StudentDAO 
{
	public void saveOrUpdateStudent(Student s){

	    try{

	        Connection con = DBConnection.getConnection();

	        String sql =
	            "MERGE INTO STUDENTS st " +
	            "USING dual " +
	            "ON (st.ID = ?) " +
	            "WHEN MATCHED THEN " +
	            "UPDATE SET NAME=?, SURNAME=?, STUDENTCLASS=?, MARKS=? " +
	            "WHEN NOT MATCHED THEN " +
	            "INSERT (ID, NAME, SURNAME, STUDENTCLASS, MARKS) VALUES (?, ?, ?, ?, ?)";

	        PreparedStatement ps = con.prepareStatement(sql);

	        // ON condition
	        ps.setInt(1, s.getId());

	        // UPDATE values
	        ps.setString(2, s.getName());
	        ps.setString(3, s.getSurname());
	        ps.setString(4, s.getStudentClass());
	        ps.setInt(5, s.getMarks());

	        // INSERT values
	        ps.setInt(6, s.getId());
	        ps.setString(7, s.getName());
	        ps.setString(8, s.getSurname());
	        ps.setString(9, s.getStudentClass());
	        ps.setInt(10, s.getMarks());

	        ps.executeUpdate();

	    }catch(Exception e){
	        e.printStackTrace();
	    }
	
    }
	
        public void viewStudents(){

    try{

        Connection con = DBConnection.getConnection();

        String sql = "SELECT * FROM STUDENTS";

        PreparedStatement ps = con.prepareStatement(sql);

        ResultSet rs = ps.executeQuery();

        while(rs.next()){

            System.out.println(
                    rs.getInt("ID")+" "+
                    rs.getString("NAME")+" "+
                    rs.getString("Surname")+" "+
                    rs.getString("StudentClass")+" "+
                    rs.getInt("MARKS")
                    
                    
            );
        }

    }catch(Exception e){
        e.printStackTrace();
    }
}
        public void deleteStudent(int id){

            try{

                Connection con = DBConnection.getConnection();

                String sql = "DELETE FROM STUDENTS WHERE ID=?";

                PreparedStatement ps = con.prepareStatement(sql);

                ps.setInt(1,id);

                ps.executeUpdate();

                System.out.println("Student Deleted");

            }catch(Exception e){
                e.printStackTrace();
            }
        
    }
        public void saveOrUpdateLocker(Locker l, int studentId){

            try{

                Connection con = DBConnection.getConnection();

                String sql =
                    "MERGE INTO LOCKER l " +
                    "USING dual " +
                    "ON (l.STUDENT_ID = ?) " +
                    "WHEN MATCHED THEN " +
                    "UPDATE SET LOCATION=?, PASSWORD=? " +
                    "WHEN NOT MATCHED THEN " +
                    "INSERT (LOCKER_ID, LOCATION, PASSWORD, STUDENT_ID) VALUES (?, ?, ?, ?,?)";

                PreparedStatement ps = con.prepareStatement(sql);

                // ON condition
                ps.setInt(1, studentId);

                // UPDATE
                ps.setString(2, l.getLocation());
                ps.setString(3, l.getPassword());

                // INSERT
                ps.setInt(4, l.getLockerId());
                ps.setString(5, l.getLocation());
                ps.setString(6, l.getPassword());
                ps.setString(7,l.getStatus().name());
                ps.setInt(7, studentId);

                ps.executeUpdate();

                System.out.println("Locker Saved (Insert/Update)");

            }catch(Exception e){
                e.printStackTrace();
            }
        }
        public void saveStudentWithLocker(Student s, Locker l){

            Connection con = null;

            try{
                con = DBConnection.getConnection();
                con.setAutoCommit(false); // 🔥 Transaction

                // 1. Student
                saveOrUpdateStudent(s);

                // 2. Locker
                saveOrUpdateLocker(l, s.getId());

                con.commit();

                System.out.println("Student + Locker Saved");

            }catch(Exception e){
                try{
                    if(con != null) con.rollback();
                }catch(Exception ex){
                    ex.printStackTrace();
                }
                e.printStackTrace();
            }
        } 
}
