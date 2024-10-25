package service.custom.impl;

import entity.ProductEntity;
import model.Product;
import org.modelmapper.ModelMapper;
import repository.DaoFactory;
import repository.custom.ProductDao;
import service.custom.ProductBo;
import util.DaoType;

import java.util.ArrayList;
import java.util.List;

public class ProductBoImpl implements ProductBo {
    @Override
    public boolean addProduct(Product product) {
        ProductEntity entity = new ModelMapper().map(product, ProductEntity.class);
        ProductDao productDao = DaoFactory.getInstance().getDaoType(DaoType.PRODUCT);
        productDao.save(entity);
        return true;
    }

    @Override
    public boolean updateProduct(Product product) {
        ProductEntity entity = new ModelMapper().map(product, ProductEntity.class);
        ProductDao productDao = DaoFactory.getInstance().getDaoType(DaoType.PRODUCT);
        productDao.update(entity);
        return false;
    }

    @Override
    public boolean deleteProduct(Product product) {
        ProductEntity entity = new ModelMapper().map(product, ProductEntity.class);
        ProductDao productDao = DaoFactory.getInstance().getDaoType(DaoType.PRODUCT);
        productDao.delete(entity.getId());
        return false;
    }

    @Override
    public Product searchProduct(String id) {
        ProductDao productDao = DaoFactory.getInstance().getDaoType(DaoType.PRODUCT);
        return new ModelMapper().map(productDao.search(id), Product.class);
    }

    @Override
    public List<Product> getProduct() {
        ProductDao productDao = DaoFactory.getInstance().getDaoType(DaoType.PRODUCT);
        List<ProductEntity> list = productDao.getAll();
        List<Product> productList = new ArrayList<>();
        for (ProductEntity entity : list) {
            productList.add(new ModelMapper().map(entity, Product.class));
        }
        return productList;
    }
}
