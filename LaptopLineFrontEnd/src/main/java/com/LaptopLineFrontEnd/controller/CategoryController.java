package com.LaptopLineFrontEnd.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.LaptopLine.dao.CategoryDAO;
import com.LaptopLine.model.Category;

@Controller
public class CategoryController {

	@Autowired
	CategoryDAO categoryDAO;
	
	@RequestMapping("/category")
	public String showCategoryPage(Model m)
	{
		List<Category> listCategory = categoryDAO.listCategories();
		m.addAttribute("categoryList", listCategory);
		return "Category";
	}
	
	@RequestMapping(value="/addCategory",method=RequestMethod.POST)
	public String addCategory(@RequestParam("categorynames")String categorynames,@RequestParam("categorydescs")String categorydescs,Model m)
	{
		
		Category category = new Category();
		category.setCategorynames(categorynames);
		category.setCategorydescs(categorydescs);
		
		categoryDAO.addCategory(category);
		
		List<Category> listCategory = categoryDAO.listCategories();
		m.addAttribute("categoryList", listCategory);
		
		return "Category";
	}
	
	@RequestMapping(value="/updateCategory",method=RequestMethod.POST)
	public String updateCategory(@RequestParam("categoryids")int categoryids,@RequestParam("categorynames")String categorynames,@RequestParam("categorydescs")String categorydescs,Model m)
	{
		
		Category category = categoryDAO.getCategory(categoryids);
		category.setCategorynames(categorynames);
		category.setCategorydescs(categorydescs);
		
		categoryDAO.updateCategory(category);
		
		List<Category> listCategory = categoryDAO.listCategories();
		m.addAttribute("categoryList", listCategory);
		
		return "Category";
	}
	
	@RequestMapping(value="/deleteCategory/{categoryids}")
	public String deleteCategory(@PathVariable("categoryids")int categoryids,Model m)
	{
		Category category = categoryDAO.getCategory(categoryids);
		categoryDAO.deleteCategory(category);
		
		List<Category> listCategory = categoryDAO.listCategories();
		m.addAttribute("categoryList", listCategory);
		
		return "Category";
	}
	
	@RequestMapping(value="/editCategory/{categoryids}")
	public String editCategory(@PathVariable("categoryids")int categoryids,Model m)
	{
		Category category = categoryDAO.getCategory(categoryids);
		m.addAttribute("categoryData", category);
		return "UpdateCategory";
	}

	
}
