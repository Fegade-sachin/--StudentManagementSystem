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
        public void saveOrUpdateStudent(Student s, Connection con) throws Exception {
            String sql = "MERGE INTO STUDENTS st USING dual ON (st.ID = ?) " +
                         "WHEN MATCHED THEN UPDATE SET NAME=?, SURNAME=?, STUDENTCLASS=?, MARKS=? " +
                         "WHEN NOT MATCHED THEN INSERT (ID, NAME, SURNAME, STUDENTCLASS, MARKS) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setInt(1, s.getId());
                ps.setString(2, s.getName());
                ps.setString(3, s.getSurname());
                ps.setString(4, s.getStudentClass());
                ps.setInt(5, s.getMarks());
                ps.setInt(6, s.getId());
                ps.setString(7, s.getName());
                ps.setString(8, s.getSurname());
                ps.setString(9, s.getStudentClass());
                ps.setInt(10, s.getMarks());
                ps.executeUpdate();
            }
        }

        public void saveOrUpdateLocker(Locker l, int studentId, Connection con) throws Exception {
            String sql = "MERGE INTO LOCKER lo USING dual ON (lo.STUDENT_ID = ?) " +
                         "WHEN MATCHED THEN UPDATE SET LOCATION=?, PASSWORD=?, STATUS=? " +
                         "WHEN NOT MATCHED THEN INSERT (LOCKER_ID, LOCATION, PASSWORD, STATUS, STUDENT_ID) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setInt(1, studentId);
                ps.setString(2, l.getLocation());
                ps.setString(3, l.getPassword());
                ps.setString(4, l.getStatus().name());
                ps.setInt(5, l.getLockerId());
                ps.setString(6, l.getLocation());
                ps.setString(7, l.getPassword());
                ps.setString(8, l.getStatus().name());
                ps.setInt(9, studentId);
                ps.executeUpdate();
            }
        }

        public void saveStudentWithLocker(Student s, Locker l) {
            try (Connection con = DBConnection.getConnection()) {
                con.setAutoCommit(false);
                saveOrUpdateStudent(s, con);
                saveOrUpdateLocker(l, s.getId(), con);
                con.commit();
                System.out.println("✅ Student + Locker Saved");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }



        public void searchStudentById(int id) {

            try {

                Connection con = DBConnection.getConnection();

                String sql = "SELECT * FROM STUDENTS WHERE ID=?";

                PreparedStatement ps = con.prepareStatement(sql);

                ps.setInt(1, id);

                ResultSet rs = ps.executeQuery();

                if(rs.next()) {

                    System.out.println("ID      : " + rs.getInt("ID"));
                    System.out.println("Name    : " + rs.getString("NAME"));
                    System.out.println("Surname : " + rs.getString("Surname"));
                    System.out.println("Class   : " + rs.getString("StudentClass"));
                    System.out.println("Marks   : " + rs.getInt("MARKS"));

                } else {

                    System.out.println("Student Not Found");

                }

            } catch(Exception e) {
                e.printStackTrace();
            }
        }
        public void viewStudentsWithLocker() {
            try (Connection con = DBConnection.getConnection()) {
            	String sql = "SELECT s.id, s.name, s.surname, s.studentClass, s.marks, " +
                        "l.locker_id, l.location, l.status " +
                        "FROM students s LEFT JOIN locker l ON s.id = l.student_id";

                PreparedStatement ps = con.prepareStatement(sql);
                ResultSet rs = ps.executeQuery();

                while (rs.next()) {
                    System.out.println("\n========== STUDENT ==========");
                    System.out.println("ID        : " + rs.getInt("ID"));
                    System.out.println("Name      : " + rs.getString("NAME"));
                    System.out.println("Surname   : " + rs.getString("SURNAME"));
                    System.out.println("Class     : " + rs.getString("STUDENTCLASS"));
                    System.out.println("Marks     : " + rs.getInt("MARKS"));

                    System.out.println("\n---------- LOCKER ----------");
                    int lockerId = rs.getInt("LOCKER_ID");
                    if (rs.wasNull()) {
                        System.out.println("No locker assigned");
                    } else {
                        System.out.println("Locker ID : " + lockerId);
                        System.out.println("Location  : " + rs.getString("LOCATION"));
                        System.out.println("Status    : " + rs.getString("STATUS"));
                    }
                    System.out.println("============================");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        public Student getStudentById(int id) {
            Student student = null;
            try (Connection con = DBConnection.getConnection()) {
                String sql = "SELECT * FROM STUDENTS WHERE ID=?";
                PreparedStatement ps = con.prepareStatement(sql);
                ps.setInt(1, id);
                ResultSet rs = ps.executeQuery();

                if (rs.next()) {
                    student = new Student(
                        rs.getInt("ID"),
                        rs.getString("NAME"),
                        rs.getString("SURNAME"),
                        rs.getString("STUDENTCLASS"),
                        rs.getInt("MARKS"),
                        null // locker fetched separately if needed
                    );
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return student;
        }






}
