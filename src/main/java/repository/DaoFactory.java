package repository;


import repository.custom.impl.*;
import util.DaoType;

public class DaoFactory {
    private static DaoFactory instance;
    private DaoFactory(){}
    public static DaoFactory getInstance(){
        return instance==null?instance = new DaoFactory():instance;
    }
    public <T extends SuperDao>T getDaoType(DaoType type){
        switch (type){
            case CUSTOMER:return (T) new CustomerDaoImpl();
            case EMPLOYEE:return (T) new EmployeeDaoImpl();
            case PRODUCT:return (T) new ProductDaoImpl();
            case SUPPLIER:return (T) new SupplierDaoImpl();
            case ORDER:return (T) new OrderDaoImpl();
            case ADMIN:return (T) new AdminDaoImpl();
            default:return null;
        }

    }
}
