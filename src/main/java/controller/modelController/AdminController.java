package controller.modelController;

import model.Admin;
import service.custom.AdminBo;
import service.custom.impl.AdminBoImpl;

import java.util.List;

public class AdminController {
    private static AdminController instance;

    final AdminBo adminservice = new AdminBoImpl();

    private AdminController(){}

    public static AdminController getInstance(){
        return instance==null?instance= new AdminController():instance;
    }
    public List<Admin> GetAdmin(){
        return adminservice.getAdmin();
    }
}
