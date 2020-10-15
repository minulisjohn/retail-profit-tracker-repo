package com.profectus.retail.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.profectus.retail.model.Transaction;
import com.profectus.retail.service.TransactionService;

@CrossOrigin(origins = "*")
@RestController
public class TransactionController {

	@Autowired
	TransactionService transactionService;
	
	////REST API for adding a purchase or sale
	@PostMapping("/transaction")
	public Transaction addTransaction(@RequestBody Transaction transaction) {
		return transactionService.addTransaction(transaction);
	}
	
	////REST API for fetching deleting a purchase or sale
	@DeleteMapping("/transaction")
	public void deleteTransaction(@RequestBody Transaction transaction) {
		transactionService.deleteTransaction(transaction);
	}
	
	//REST API for calculating the profit for the passed in search criteria
	@GetMapping("/profit")
	public BigDecimal calculateProfit(@RequestParam String asOfDate, 
			@RequestParam String categoryId, @RequestParam String productCodes, @RequestParam String purchasePrice) {
		return transactionService.calculateProfit(asOfDate, categoryId, productCodes, purchasePrice);
	}
	
}
