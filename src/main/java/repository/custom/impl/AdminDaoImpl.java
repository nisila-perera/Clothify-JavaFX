package repository.custom.impl;

import entity.AdminEntity;
import repository.custom.AdminDao;

import java.util.List;

public class AdminDaoImpl implements AdminDao {
    @Override
    public boolean save(AdminEntity adminEntity) {
        return false;
    }

    @Override
    public boolean delete(String id) {
        return false;
    }

    @Override
    public List<AdminEntity> getAll() {
        return List.of();
    }

    @Override
    public boolean update(AdminEntity adminEntity) {
        return false;
    }

    @Override
    public AdminEntity search(String id) {
        return null;
    }
}
