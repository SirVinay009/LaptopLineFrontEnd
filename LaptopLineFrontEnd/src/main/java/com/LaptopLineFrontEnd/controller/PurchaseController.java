package com.LaptopLineFrontEnd.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.LaptopLine.dao.CartDAO;
import com.LaptopLine.dao.PurchaseDAO;
import com.LaptopLine.model.CartDetails;
import com.LaptopLine.model.PurchaseDetails;

@Controller
public class PurchaseController {

	@Autowired
	CartDAO cartDAO;
	
	@Autowired
	PurchaseDAO purchaseDAO;
	
	@RequestMapping(value="/payment")
	public String showPaymentPage()
	{
		return "Payment";
	}
	
	@RequestMapping(value="/paymentprocess",method=RequestMethod.POST)
	public String paymentProcess(@RequestParam("pmode")String pmode,HttpSession session,Model m)
	{
		String username = (String)session.getAttribute("username");
		
		List<CartDetails> cartDetailsItems = cartDAO.retrieveCartDetails(username);
		m.addAttribute("cartItems", cartDetailsItems);
		m.addAttribute("grandTotal",this.calcGrandTotalValue(cartDetailsItems));
		
		PurchaseDetails purchaseDetails = new PurchaseDetails();
		purchaseDetails.setUsername(username);
		purchaseDetails.setOrderdate(new java.util.Date());
		purchaseDetails.setPmode(pmode);
		purchaseDetails.setTotalshoppingamount((int)this.calcGrandTotalValue(cartDetailsItems));
		
		purchaseDAO.insertPurchaseDetails(purchaseDetails);
		
		System.out.println("PurchaseDetail have been saved");
		
		return "ThankYou";
	}
	
	public long calcGrandTotalValue(List<CartDetails> cartDetailsItems)
	{
		int count=0;
		long grandTotalPrice=0;
		while(count<cartDetailsItems.size())
		{
			grandTotalPrice+=(cartDetailsItems.get(count).getQuantity()*cartDetailsItems.get(count).getPrice());
			count++;
		}
		
		return grandTotalPrice;
	}

	
	
}
