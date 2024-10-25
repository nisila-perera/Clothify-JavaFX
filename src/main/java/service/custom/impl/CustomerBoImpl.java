package service.custom.impl;

import entity.CustomerEntity;
import model.Customer;
import org.modelmapper.ModelMapper;
import repository.DaoFactory;
import repository.custom.CustomerDao;
import service.custom.CustomerBo;
import util.DaoType;

import java.util.ArrayList;
import java.util.List;

public class CustomerBoImpl implements CustomerBo {
    @Override
    public boolean addCustomer(Customer customer) {
        CustomerEntity entity = new ModelMapper().map(customer, CustomerEntity.class);
        CustomerDao customerDao = DaoFactory.getInstance().getDaoType(DaoType.CUSTOMER);
        return customerDao.save(entity);
    }

    @Override
    public boolean updateCustomer(Customer customer) {
        CustomerEntity entity = new ModelMapper().map(customer, CustomerEntity.class);
        CustomerDao customerDao = DaoFactory.getInstance().getDaoType(DaoType.CUSTOMER);
        customerDao.update(entity);

        return true;
    }

    @Override
    public boolean deleteCustomer(Customer customer) {
        CustomerEntity entity = new ModelMapper().map(customer, CustomerEntity.class);
        CustomerDao customerDao = DaoFactory.getInstance().getDaoType(DaoType.CUSTOMER);
        customerDao.delete(entity.getId());

        return true;
    }

    @Override
    public List<Customer> getCustomer() {
        CustomerDao customerDao = DaoFactory.getInstance().getDaoType(DaoType.CUSTOMER);
        List<CustomerEntity> list =customerDao.getAll();
        List<Customer> customerList = new ArrayList<>();
        for (CustomerEntity entity : list) {
            customerList.add(new ModelMapper().map(entity, Customer.class));
        }
        return customerList;
    }

    @Override
    public Customer search(String id) {
        CustomerDao customerDao = DaoFactory.getInstance().getDaoType(DaoType.CUSTOMER);
        return new ModelMapper().map(customerDao.search(id),Customer.class);
    }
}
