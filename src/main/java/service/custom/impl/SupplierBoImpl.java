package service.custom.impl;

import model.Supplier;
import service.custom.SupplierBo;

import java.util.List;

public class SupplierBoImpl implements SupplierBo {
    @Override
    public boolean addSupplier(Supplier supplier) {
        return false;
    }

    @Override
    public boolean deleteSupplier(Supplier supplier) {
        return false;
    }

    @Override
    public boolean updateSupplier(Supplier supplier) {
        return false;
    }

    @Override
    public Supplier searchSupplier(String id) {
        return null;
    }

    @Override
    public List<Supplier> getSupplier() {
        return List.of();
    }
}
