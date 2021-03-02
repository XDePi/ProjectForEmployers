package ru.depi.testapplication.demo.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.depi.testapplication.demo.entity.Product;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.sql.Date;
import java.util.List;

/**
 * @author DePi
 **/

@Repository
public class ProductDAOImpl implements ProductDAO {

    @Autowired
    EntityManager entityManager;

    @Override
    public List<Product> getAllProducts() {
        Query query = entityManager.createQuery("from Product");
        List<Product> products = query.getResultList();
        return products;
    }

    @Override
    public void addProduct(Product product) {
        long millis = System.currentTimeMillis();
        Date date = new Date(millis);
        product.setDate(date);
        product.setDate_of_modification(date);
        Product product1 = entityManager.merge(product);
        product.setId(product1.getId());
    }

    @Override
    public void deleteProduct(int id) {
        Query query = entityManager.createQuery("delete from Product " +
                "where id=:productId");
        query.setParameter("productId", id);
        query.executeUpdate();
    }
    
}
