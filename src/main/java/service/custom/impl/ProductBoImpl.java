package service.custom.impl;

import model.Product;
import service.custom.ProductBo;

import java.util.List;

public class ProductBoImpl implements ProductBo {
    @Override
    public boolean addProduct(Product product) {
        return false;
    }

    @Override
    public boolean updateProduct(Product product) {
        return false;
    }

    @Override
    public boolean deleteProduct(Product product) {
        return false;
    }

    @Override
    public Product searchProduct(String id) {
        return null;
    }

    @Override
    public List<Product> getProduct() {
        return List.of();
    }
}
