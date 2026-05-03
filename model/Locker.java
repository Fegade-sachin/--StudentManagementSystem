package model;

public class Locker {

    private int lockerId;
    private String location;
    private String password;
    private LockerStatus status;

    public Locker(int lockerId, String location, String password,LockerStatus status) {
        this.lockerId = lockerId;
        this.location = location;
        this.password = password;
        this.status   = status;
    }

    public int getLockerId() {
        return lockerId;
    }

    public String getLocation() {
        return location;
    }

    public String getPassword() {
        return password;
    }
    public LockerStatus getStatus()
    {
    	return status;
    
    }
}