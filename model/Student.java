package model;

public class Student
{
	private int Id;
	private String Name;
	private String Surname;
	private String StudentClass;
	private int Marks;
	private Locker locker;  
	
	public Student(int id, String name,String surname,String studnetClass , int marks,Locker l) 
	{
		Id = id;
		Name = name;
		Surname=surname;
		StudentClass=studnetClass;
		locker = l;
		if(marks>0 && marks<100)
		{
			Marks = marks;
		}
		else
		{
			throw new IllegalArgumentException("mark must be between 0 to 100");
		}
	
	}
	public Locker getLocker() {
        return locker;
    }
	public int getId() 
	{
		return Id;
	}
	public String getName() 
	{
		return Name;
	}
	public int getMarks() {
		return Marks;
	}
	public String getSurname() {
		return Surname;
	}
	public String getStudentClass() {
		return StudentClass;
	}
	public void setLocker(Locker locker2) {
	    this.locker = locker2;
	}
	

	
		
	
	
}
