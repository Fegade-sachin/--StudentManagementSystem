package service;


import deo.AdminDAO;

public class AdminService {

    AdminDAO dao = new AdminDAO();

    public boolean login(String username, String adopassword){
        return dao.login(username, adopassword);
    }
}