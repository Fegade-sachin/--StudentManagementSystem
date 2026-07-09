package service;

import java.sql.Connection;

import deo.StudentDAO;
import model.Locker;
import model.Student;
import util.DBConnection;

public class StudentService 
{

    StudentDAO dao = new StudentDAO();

    public void addStudent(Student s , Locker l){
        dao.saveStudentWithLocker(s, l);
    }

    public void viewStudents(){
        dao.viewStudents();
    }

    public void deleteStudent(int id){
        dao.deleteStudent(id);
    }
    public Student searchStudentById(int id) {
        dao.searchStudentById(id);
		return null;
    }
    public void viewStudentsWithLocker() {
        dao.viewStudentsWithLocker();
    }

    public Student findStudentById(int studentId) {
        return dao.getStudentById(studentId); // return the actual student object
    }

    
 
    public void addLockerToStudent(Student s, Locker l) {
        try (Connection con = DBConnection.getConnection()) {
            dao.saveOrUpdateLocker(l, s.getId(), con);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void viewLockerById(int searchLockerId) {
        dao.viewLockerById(searchLockerId); // single locker
    }








}
