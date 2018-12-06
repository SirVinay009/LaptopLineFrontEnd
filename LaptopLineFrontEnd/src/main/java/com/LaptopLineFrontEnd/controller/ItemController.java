package com.LaptopLineFrontEnd.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.LaptopLine.dao.CategoryDAO;
import com.LaptopLine.dao.ItemDAO;
import com.LaptopLine.model.Category;
import com.LaptopLine.model.Item;


@Controller
public class ItemController {

	@Autowired
	ItemDAO itemDAO;
	
	@Autowired
	CategoryDAO categoryDAO;
	
	boolean flag=true;
	
	@RequestMapping(value="/item")
	public String showManageItem(Model m)
	{
		Item item = new Item();
		m.addAttribute("item",item);
	
		List<Item> itemList = itemDAO.listItems();
		m.addAttribute("itemList", itemList);
		
		List<Category> categoryList = categoryDAO.listCategories();
		m.addAttribute("categoryList", this. getCategoryList(categoryList));
		
		flag=true;
		m.addAttribute("flag", flag);
		return "Item";
	}
	
	@RequestMapping(value="/addItem",method=RequestMethod.POST)
	public String addItem(@ModelAttribute("item")Item item,@RequestParam("partimage") MultipartFile fileDetail,Model m,BindingResult result)
	{
		
		itemDAO.addItem(item);
		
		String imagePath="C:\\Users\\Vinay\\eclipse-workspace\\LaptopLineFrontEnd\\src\\main\\webapp\\WEB-INF\\resources\\images";
		imagePath = imagePath+String.valueOf(item.getItemids())+".jpg";
		
		File myFile=new File(imagePath);
		
		if(!fileDetail.isEmpty())
		{
			try
			{
				byte[] fileBytes=fileDetail.getBytes();
				
				FileOutputStream fos=new FileOutputStream(myFile);
				
				BufferedOutputStream bs=new BufferedOutputStream(fos);
				
				bs.write(fileBytes);
				
				bs.close();
				
			}
			catch(Exception e)
			{
				m.addAttribute("errorInfo","Exception Arised:"+e);
			}
		}
		else
		{
			m.addAttribute("errorInfo","Error While Uploading the Image");
		}
		
		//=> Image Uploading Completed
		
		
		Item item1 = new Item();
		m.addAttribute("item", item1);
	
		List<Item> itemList = itemDAO.listItems();
		m.addAttribute("itemList", itemList);
		
		List<Category> categoryList = categoryDAO.listCategories();
		m.addAttribute("categoryList", this.getCategoryList(categoryList));
		
		flag=true;
		m.addAttribute("flag", flag);
		return "Item";
	}
	
	public LinkedHashMap<Integer,String> getCategoryList(List<Category> listCategory)
	{
		
		LinkedHashMap<Integer,String> categoryData = new LinkedHashMap<Integer,String>();
		
		int count=0;
		while(count<listCategory.size())
		{
			categoryData.put(listCategory.get(count).getCategoryids(),listCategory.get(count).getCategorynames());
			count++;
		}
		
		return categoryData;
	}
	
	@RequestMapping(value="/deleteItem/{itemids}")
	public String deleteItem(@PathVariable("itemids")int itemids,Model m)
	{
		Item item = itemDAO.getItem(itemids);
		
		itemDAO.deleteItem(item);
		
		Item item1=new Item();
		m.addAttribute("item", item1);
	
		List<Item> itemList = itemDAO.listItems();
		m.addAttribute("itemList", itemList);
		
		List<Category> categoryList = categoryDAO.listCategories();
		m.addAttribute("categoryList", this.getCategoryList(categoryList));
		
		flag=true;
		m.addAttribute("flag", flag);
		return "Item";
	}
	
	@RequestMapping(value="/editItem/{itemids}")
	public String editItem(@PathVariable("itemids")int itemids,Model m)
	{
		Item item = itemDAO.getItem(itemids);
		
		m.addAttribute("item", item);
		
		List<Item> itemList = itemDAO.listItems();
		m.addAttribute("itemList", itemList);
		
		List<Category> categoryList=categoryDAO.listCategories();
		m.addAttribute("categoryList", this.getCategoryList(categoryList));
		
		flag=false;
		m.addAttribute("flag", flag);
		return "Item";
	}
	
	@RequestMapping(value="/updateItem",method=RequestMethod.POST)
	public String updateItem(@ModelAttribute("item")Item item,Model m)
	{
		
		itemDAO.updateItem(item);
		
		Item item1 = new Item();
		m.addAttribute("item",item1);
	
		List<Item> itemList = itemDAO.listItems();
		m.addAttribute("itemList", itemList);
		
		List<Category> categoryList=categoryDAO.listCategories();
		m.addAttribute("categoryList", this.getCategoryList(categoryList));
		
		flag=false;
		m.addAttribute("flag", flag);
		return "Item";
	}
	
	@RequestMapping("/itemCatalog")
	public String displayAllProduct(Model m)
	{
		List<Item> itemList = itemDAO.listItems();
		m.addAttribute("itemList", itemList);
		
		return "ItemCatalog";
	}
	
	@RequestMapping("/itemDisplay/{itemids}")
	public String displaySingleItem(@PathVariable("itemids")int itemids,Model m)
	{
		Item item = (Item) itemDAO.getItem(itemids);
		m.addAttribute("itemInfo", item);
		m.addAttribute("categorynames", categoryDAO.getCategory(item.getCategoryids()).getCategorynames());
		return "ItemDisplay";
	}
	
}
