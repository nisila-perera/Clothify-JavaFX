package service.custom.impl;

import entity.AdminEntity;
import model.Admin;
import org.modelmapper.ModelMapper;
import repository.DaoFactory;
import repository.custom.AdminDao;
import service.custom.AdminBo;
import util.DaoType;

import java.util.ArrayList;
import java.util.List;

public class AdminBoImpl implements AdminBo {
    @Override
    public List<Admin> getAdmin() {
        AdminDao adminDao = DaoFactory.getInstance().getDaoType(DaoType.ADMIN);
        List<AdminEntity> list =adminDao.getAll();
        List<Admin> adminList = new ArrayList<>();
        for (AdminEntity entity : list) {
            adminList.add(new ModelMapper().map(entity, Admin.class));
        }
        return adminList;
    }
}
