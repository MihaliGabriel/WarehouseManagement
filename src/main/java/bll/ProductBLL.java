package bll;

import DataAccess.CustomerDAO;
import DataAccess.ProductDAO;
import Model.*;
import Validators.EmailValidator;
import Validators.Validator;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Are un camp de tip ProductDAO, cu care se realizeaza accesul tabelei Product din baza de date. Contine metodele insertProduct(), viewProduct(), deleteProduct(), updateProduct() si getProducts() care primesc parametri de tip Product si apeleaza metodele din ProductDAO.
 */
public class ProductBLL {
    private ProductDAO productDAO;

    public ProductBLL() {
        productDAO = new ProductDAO();
    }

    public void insertProduct(Product product) {
        productDAO.insertQueryProduct(product);
    }

   public ResultSet viewProduct(Product product) {
        ResultSet rs = null;
        rs = productDAO.viewQueryProduct(product);
        return rs;
    }

    public void deleteProduct(Product product) {
        productDAO.deleteQueryProduct(product);
    }
    public List<Product> getProducts() {
        return productDAO.getAllProducts();
    }

   public void updateProduct(Product product, Product updatedProduct) {
        productDAO.updateQueryProduct(product, updatedProduct);
    }


}