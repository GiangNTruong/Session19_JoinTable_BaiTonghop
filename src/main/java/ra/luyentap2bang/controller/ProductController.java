package ra.luyentap2bang.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ra.luyentap2bang.dao.CategoryDAO;
import ra.luyentap2bang.dao.ProductDAO;
import ra.luyentap2bang.entity.Category;
import ra.luyentap2bang.entity.Product;

import java.util.List;

@Controller

public class ProductController {
    @Autowired
    private ProductDAO productDAO;
    @Autowired
    private CategoryDAO categoryDAO;
    @RequestMapping(value = {"/","/product"})
    public String listProduct(Model model){
        List<Product> products = productDAO.getProducts();
        model.addAttribute("list",products);
        return "listProduct";
    }
    @GetMapping("/showAddProduct")
    public String showAddProduct(Model model){
        Product po = new Product();
        model.addAttribute("po",po);
        List<Category> categories = categoryDAO.getCategories();
        model.addAttribute("categories", categories);
        return "addProduct";
    }
    @PostMapping("/addProduct")
    public String addProduct(@ModelAttribute("po") Product product){
        productDAO.addProduct(product);
        return "redirect:/product";
    }
    @GetMapping("/showEditProduct")
    public String showEditProduct(Model model, @RequestParam("id") Integer id){
        Product product = productDAO.getProductById(id);
        model.addAttribute("po",product);
        List<Category> categories = categoryDAO.getCategories();
        model.addAttribute("categories", categories);
        return "editProduct";
    }
    @PostMapping("/updateProduct")
    public String updateProduct(@ModelAttribute("po")Product product){
        productDAO.updateProduct(product);
        return "redirect:/product";
    }
    @GetMapping("deleteProduct")
    public String deleteProduct(@RequestParam("id") Integer id){
        productDAO.deleteProduct(id);
        return "redirect:/product";
    }



}
