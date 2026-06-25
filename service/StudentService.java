package service;

import deo.StudentDAO;
import model.Locker;
import model.Student;

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
    public void searchStudentById(int id) {
        dao.searchStudentById(id);
    }
    public void viewStudentsWithLocker() {
        dao.viewStudentsWithLocker();
    }
}
