package main;
import service.StudentService;
import model.Locker;
import model.Student;
import java.util.Scanner;

public class Main {

    public static void main(String[] args){

        Scanner sc = new Scanner(System.in);

        StudentService service = new StudentService();

        while(true){
        	System.out.println();
        	System.out.println("--------------------------------------");
            System.out.println("1 Add Student");
            System.out.println("2 View Students");
            System.out.println("3 Delete Student");
            System.out.println("4 Exit");
            System.out.println("--------------------------------------");
            int choice = sc.nextInt();

            switch(choice){

                case 1:
                    System.out.print("Enter id: ");
                    int id = sc.nextInt();

                    System.out.print("Enter name: ");
                    String name = sc.next();

                    System.out.print("Enter surname: ");
                    String surname = sc.next();
                                     
                    System.out.print("Enter studnetClass: ");
                    String studentClass = sc.next();
                    
                    System.out.print("Enter marks: ");
                    int marks = sc.nextInt();
                    
                    System.out.println("Enter lockerId: ");
                    int lockerId =sc.nextInt();
                    
                    System.out.println("Enter location: ");
                    String location = sc.next();
                    
                    System.out.println("Enter password: ");
                    String password = sc.next();
                    System.out.println("account created successfully !! ");
                    Locker locker=new Locker(lockerId,location,password);
                    
                    Student s = new Student(id,name,surname,studentClass,marks,locker);
                    service.addStudent(s);
                    break;

                case 2:
                    service.viewStudents();
                    break;
                case 3:
                    System.out.print("Enter id: ");
                    int did = sc.nextInt();

                    service.deleteStudent(did);
                    break;

                case 4:
                    System.exit(0);
            }
          
        }
    }
}