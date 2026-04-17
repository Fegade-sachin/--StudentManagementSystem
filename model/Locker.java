package model;

public class Locker {

    private int lockerId;
    private String location;
    private String password;

    public Locker(int lockerId, String location, String password) {
        this.lockerId = lockerId;
        this.location = location;
        this.password = password;
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
}