package service.custom.impl;

import entity.EmployeeEntity;
import entity.SupplierEntity;
import model.Customer;
import model.Employee;
import model.Supplier;
import org.modelmapper.ModelMapper;
import repository.DaoFactory;
import repository.custom.CustomerDao;
import repository.custom.EmployeeDao;
import repository.custom.SupplierDao;
import service.custom.SupplierBo;
import util.DaoType;

import java.util.ArrayList;
import java.util.List;

public class SupplierBoImpl implements SupplierBo {
    @Override
    public boolean addSupplier(Supplier supplier) {
        SupplierEntity entity = new ModelMapper().map(supplier, SupplierEntity.class);
        SupplierDao supplierDao = DaoFactory.getInstance().getDaoType(DaoType.SUPPLIER);
        supplierDao.save(entity);

        return true;
    }

    @Override
    public boolean deleteSupplier(Supplier supplier) {
        SupplierEntity entity = new ModelMapper().map(supplier, SupplierEntity.class);
        SupplierDao supplierDao = DaoFactory.getInstance().getDaoType(DaoType.SUPPLIER);
        supplierDao.delete(entity.getId());
        return true;
    }

    @Override
    public boolean updateSupplier(Supplier supplier) {
        SupplierEntity entity = new ModelMapper().map(supplier, SupplierEntity.class);
        SupplierDao supplierDao = DaoFactory.getInstance().getDaoType(DaoType.SUPPLIER);
        supplierDao.update(entity);

        return true;
    }

    @Override
    public Supplier searchSupplier(String id) {
        SupplierDao supplierDao = DaoFactory.getInstance().getDaoType(DaoType.SUPPLIER);
        return new ModelMapper().map(supplierDao.search(id), Supplier.class);
    }

    @Override
    public List<Supplier> getSupplier() {
        SupplierDao supplierDao = DaoFactory.getInstance().getDaoType(DaoType.SUPPLIER);
        List<SupplierEntity> list = supplierDao.getAll();
        List<Supplier> supplierList = new ArrayList<>();
        for (SupplierEntity entity : list) {
            supplierList.add(new ModelMapper().map(entity, Supplier.class));
        }
        return supplierList;
    }
}
