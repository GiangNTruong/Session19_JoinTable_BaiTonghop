package ra.luyentap2bang.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ra.luyentap2bang.dao.CategoryDAO;
import ra.luyentap2bang.entity.Category;

import java.util.List;

@Controller

public class CategoryController {
    @Autowired
    private CategoryDAO categoryDAO;
    @RequestMapping("/category")
    public String list(Model model){
        List<Category> categories = categoryDAO.getCategories();
        model.addAttribute("listCate",categories);
        return "listCategory";
    }
    @GetMapping("/showAddCategory")
    public String showAddCategory(Model model){
        Category ca = new Category();
        model.addAttribute("ca",ca);
        return "addCategory";
    }
    @PostMapping("/addCategory")
    public String addCategory(@ModelAttribute("ca") Category category){
        categoryDAO.addCategory(category);
        return "redirect:/category";
    }
    @GetMapping("showEditCategory")
    public String showEditCategory(Model model, @RequestParam("id") String id){
        Category category = categoryDAO.getCategoryById(id);
        model.addAttribute("ca",category);
        return "editCategory";
    }
    @PostMapping("/updateCategory")
    public String updateCategory(@ModelAttribute("ca") Category category){
        categoryDAO.updateCategory(category);
        return "redirect:/category";
    }
    @GetMapping("/deleteCategory")
    public String deleteCategory(@RequestParam("id") String id){
        categoryDAO.deleteCategory(id);
        return "redirect:/category";
    }
}
