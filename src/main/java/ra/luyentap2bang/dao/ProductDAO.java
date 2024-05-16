package ra.luyentap2bang.dao;

import ra.luyentap2bang.entity.Category;
import ra.luyentap2bang.entity.Product;

import java.util.List;

public interface ProductDAO {
    List<Product> getProducts();
    Product getProductById(Integer proId);
    boolean addProduct(Product product);
    boolean updateProduct(Product product);
    boolean deleteProduct(Integer proId);
}
