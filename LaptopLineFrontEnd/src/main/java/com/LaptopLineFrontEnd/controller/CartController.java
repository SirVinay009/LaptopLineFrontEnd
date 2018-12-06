package com.LaptopLineFrontEnd.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.LaptopLine.dao.CartDAO;
import com.LaptopLine.dao.ItemDAO;
import com.LaptopLine.model.CartDetails;
import com.LaptopLine.model.Item;

@Controller
public class CartController {

	@Autowired
	ItemDAO itemDAO;
	
		@Autowired
	CartDAO cartDAO;

		@RequestMapping("/cart")
		public String showCart(HttpSession session,Model m)
		{
			
			String username = (String)session.getAttribute("username");
			
			List<CartDetails> cartDetailsItems = cartDAO.retrieveCartDetails(username);
			m.addAttribute("cartDetailsPurchase", cartDetailsItems);
			m.addAttribute("grandTotal",this.calcGrandTotalValue(cartDetailsItems));
			return "Cart";
		}
		
		public long calcGrandTotalValue(List<CartDetails> cartDetailsItems)
		{
			
			int count=0;
			long grandtotalprice=0;
			while(count<cartDetailsItems.size())
			{
				grandtotalprice+=(cartDetailsItems.get(count).getQuantity()*cartDetailsItems.get(count).getPrice());
				count++;
			}
			
			return grandtotalprice;

		}
		
		@RequestMapping(value="/updateCartDetails/{cartitemids}")
		public String updateCartDetails(@PathVariable("cartitemids")int cartitemids,@RequestParam("qty")int quantity,Model m,HttpSession session)
		{
			CartDetails cartDetails = cartDAO.getCartDetails(cartitemids);
			cartDetails.setQuantity(quantity);
			cartDAO.updateCartDetails(cartDetails);
			
			String username=(String)session.getAttribute("username");
			
			List<CartDetails> cartDetailsItems = cartDAO.retrieveCartDetails(username);
			m.addAttribute("cartDetailsPurchase", cartDetailsItems);
			m.addAttribute("grandTotal",this.calcGrandTotalValue(cartDetailsItems));
			
			return "Cart";
		}
		
		@RequestMapping(value="/deleteCartDetails/{cartitemids}")
		public String deleteCartDetails(@PathVariable("cartitemids")int cartitemids,Model m,HttpSession session)
		{
			CartDetails cartDetails = cartDAO.getCartDetails(cartitemids);
			cartDAO.deleteCartDetails(cartDetails);
			
			String username = (String)session.getAttribute("username");
			
			List<CartDetails> cartDetailsItems = cartDAO.retrieveCartDetails(username);
			m.addAttribute("cartDetailsPurchase", cartDetailsItems);
			m.addAttribute("grandTotal",this.calcGrandTotalValue(cartDetailsItems));
			
			return "Cart";
		}

		@RequestMapping("/addToCart/{itemids}")
		public String addToCart(@PathVariable("itemids") int itemids,@RequestParam("qty") int quantity,HttpSession session,Model m)
		{
			
			Item item = itemDAO.getItem(itemids);
			
			CartDetails cartDetails = new CartDetails();
			
			String username = (String)session.getAttribute("username");
			
			cartDetails.setCartitemids(item.getItemids());
			cartDetails.setItemnames(item.getItemnames());
			cartDetails.setPrice(item.getPrice());
			cartDetails.setQuantity(quantity);
			cartDetails.setPstatus("NP");
			cartDetails.setUsername(username);		
			cartDAO.addCartDetails(cartDetails);
			
			List<CartDetails> cartDetailsItems = cartDAO.retrieveCartDetails(username);
			m.addAttribute("cartDetailsPurchase", cartDetailsItems);
			m.addAttribute("grandTotal",this.calcGrandTotalValue(cartDetailsItems));
			
			return "Cart";
		}
		
		@RequestMapping(value="/checkout")
		public String checkOut(HttpSession session,Model m)
		{

			String username = (String)session.getAttribute("username");
			
			List<CartDetails> cartDetailsItems = cartDAO.retrieveCartDetails(username);
			m.addAttribute("cartDetailsPurchase", cartDetailsItems);
			m.addAttribute("grandTotal",this.calcGrandTotalValue(cartDetailsItems));
			
			return "PurchaseDetails";
		}


}
