package repository.custom.impl;

import entity.EmployeeEntity;
import repository.custom.EmployeeDao;

import java.util.List;

public class EmployeeDaoImpl implements EmployeeDao {
    @Override
    public boolean save(EmployeeEntity employeeEntity) {
        return false;
    }

    @Override
    public boolean delete(String id) {
        return false;
    }

    @Override
    public List<EmployeeEntity> getAll() {
        return List.of();
    }

    @Override
    public boolean update(EmployeeEntity employeeEntity) {
        return false;
    }

    @Override
    public EmployeeEntity search(String id) {
        return null;
    }
}
