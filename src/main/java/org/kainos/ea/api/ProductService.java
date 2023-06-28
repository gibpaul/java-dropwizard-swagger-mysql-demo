package org.kainos.ea.api;

import org.kainos.ea.cli.Product;
import org.kainos.ea.cli.ProductRequest;
import org.kainos.ea.client.FailedToCreateProductException;
import org.kainos.ea.client.FailedToGetProductsException;
import org.kainos.ea.client.InvalidProductException;
import org.kainos.ea.client.ProductDoesNotExistException;
import org.kainos.ea.core.ProductValidator;
import org.kainos.ea.db.ProductDao;

import java.sql.SQLException;
import java.util.List;
import java.util.stream.Collectors;

public class ProductService {
    private ProductDao productDao = new ProductDao();
    private ProductValidator productValidator = new ProductValidator();

    public int createProduct(ProductRequest product) throws FailedToCreateProductException, InvalidProductException {
        try {
            String validation = productValidator.isValidProduct(product);

            if (validation != null) {
                throw new InvalidProductException(validation);
            }

            int id = productDao.createProduct(product);

            if (id == -1) {
                throw new FailedToCreateProductException();
            }

            return id;
        } catch (SQLException e) {
            System.err.println(e.getMessage());

            throw new FailedToCreateProductException();
        }
    }
    public List<Product> getAllProducts() throws FailedToGetProductsException {
        List<Product> productList = null;
        try {
            productList = productDao.getAllProducts();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new FailedToGetProductsException();
        }

        System.out.println(productList);

        return productList;
    }

    public Product getProductById(int id) throws FailedToGetProductsException, ProductDoesNotExistException {
        try {
            Product product = productDao.getProductById(id);

            if (product == null) {
                throw new ProductDoesNotExistException();
            }

            return product;
        } catch (SQLException e) {
            System.err.println(e.getMessage());

            throw new FailedToGetProductsException();
        }
    }


}
