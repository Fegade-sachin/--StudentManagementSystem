package main;

import java.util.Scanner;

import model.Locker;
import model.LockerStatus;
import model.Student;
import service.AdminService;
import service.StudentService;

public class Main {

	public static void main(String[] args) {

		
		  Scanner sc = new Scanner(System.in); //AdminService adminService = new
		 /* AdminService();
		 * 
		 * System.out.println("===== ADMIN LOGIN =====");
		 * 
		 * System.out.print("Enter username: "); String username =
		 * sc.next().trim().toLowerCase();
		 * 
		 * System.out.print("Enter password: "); String password =
		 * sc.next().trim().toLowerCase();
		 * 
		 * boolean isValid = adminService.login(username, password);
		 * 
		 * if(!isValid){ System.out.println("❌ Invalid Login! Access Denied");
		 * System.exit(0); }
		 * 
		 * System.out.println("✅ Login Successful");
		 */

		// After this Student menu continues

		StudentService service = new StudentService();

		while (true) {
			System.out.println();
			System.out.println("--------------------------------------");
			System.out.println("1 Add Student");
			System.out.println("2 View Students");
			System.out.println("3 Delete Student");
			System.out.println("4 Search Student By ID ");
			System.out.println("5 View Student + Locker");
			System.out.println("6 Exit");
			System.out.println("--------------------------------------");
			int choice = sc.nextInt();

			switch (choice) {

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
				System.out.println("<===== Do You Need Student Locker =====>");
				System.out.println(" If Yes Press : 1 " + " If NO Press :2 ");
				int need = sc.nextInt();

				switch (need) {

				case 1:
					System.out.println("Enter lockerId: ");
					int lockerId = sc.nextInt();

					System.out.println("Enter location: ");
					String location = sc.next();

					System.out.println("Enter password: ");
					String Stpassword = sc.next();

					System.out.print("Enter Locker Status (AVAILABLE/ALLOCATED/LOCKED): ");

					LockerStatus status = LockerStatus.valueOf(sc.next().toUpperCase());
					System.out.println("account created successfully !! ");
					Locker locker = new Locker(lockerId, location, Stpassword, status);

					Student s = new Student(id, name, surname, studentClass, marks, locker);
					service.addStudent(s, locker);
					break;
				case 2:
					break;

				}
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

				System.out.print("Enter Student ID: ");
				int searchId = sc.nextInt();

				service.searchStudentById(searchId);
				break;
			case 5:
				System.out.println("view Students with Locker");
				service.viewStudentsWithLocker();
				break;

			case 6:

				System.exit(0);
			}

		}
	}
}