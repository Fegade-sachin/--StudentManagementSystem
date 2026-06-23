package service;

import deo.StudentDAO;
import model.Student;

public class StudentService 
{

    StudentDAO dao = new StudentDAO();

    public void addStudent(Student s){
        dao.saveOrUpdateStudent(s);
    }

    public void viewStudents(){
        dao.viewStudents();
    }

    public void deleteStudent(int id){
        dao.deleteStudent(id);
    }
    public void searchStudentById(int id) {
        dao.searchStudentById(id);
    }
}
