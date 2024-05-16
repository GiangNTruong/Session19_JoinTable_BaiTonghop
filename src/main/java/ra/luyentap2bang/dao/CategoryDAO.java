package ra.luyentap2bang.dao;

import ra.luyentap2bang.entity.Category;

import java.util.List;

public interface CategoryDAO {
    List<Category> getCategories();
    Category getCategoryById(String catId);
    boolean addCategory(Category category);
    boolean updateCategory(Category category);
    boolean deleteCategory(String catId);
}
