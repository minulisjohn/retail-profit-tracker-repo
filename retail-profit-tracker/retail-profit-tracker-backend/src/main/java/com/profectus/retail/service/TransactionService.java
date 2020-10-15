package com.profectus.retail.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.profectus.retail.exception.NoDataException;
import com.profectus.retail.model.Transaction;
import com.profectus.retail.repo.TransactionRepo;

@Component
public class TransactionService {

	@Autowired
	private TransactionRepo transactionRepo;
	
	//Service method for adding purchase or sale
	public Transaction addTransaction(Transaction transaction) {
		transaction.setCreationDate(LocalDateTime.now());
		return transactionRepo.save(transaction);
	}
	
	//Service method for deleting purchase or sale
	public void deleteTransaction(Transaction transaction) {
		transactionRepo.delete(transaction);
	}
	
	////Service method for calculating profit
	public BigDecimal calculateProfit(String asOfDate, 
			String categoryIdStr, String productCodesStr, String priceStr) {
		
		//Setting fromDate to the first day of the current year
		LocalDate fromDate = LocalDate.of(LocalDate.now().getYear(), 01, 01);
		LocalDate toDate = null;
		if(asOfDate == null || asOfDate.equalsIgnoreCase("")) {
			toDate = LocalDate.now();
		} else {
			toDate = LocalDate.parse(asOfDate);
		}
		
		//Validating category field
		Integer categoryId = null;
		if(!(categoryIdStr == null || categoryIdStr.equalsIgnoreCase(""))) {
			categoryId = Integer.valueOf(categoryIdStr);
		} 
		
		//Validating productCodes field
		List<String> productCodes = null;
		if(!(productCodesStr == null || productCodesStr.equalsIgnoreCase(""))) {
			productCodes = Arrays.asList(productCodesStr.split(",")); 
		}
		
		//Validating price field
		BigDecimal price = null;
		if(!(priceStr == null || priceStr.equalsIgnoreCase(""))) {
			price = new BigDecimal(priceStr);
		}
		List<Object[]> searchResults = transactionRepo.calculateProfit(fromDate, toDate, 
				categoryId, productCodes, price);
		
		
		//Calculating the profit
		BigDecimal purchasePrice = BigDecimal.ZERO;
		BigDecimal salePrice = BigDecimal.ZERO;
		BigDecimal purchasedQty = BigDecimal.ZERO;
		BigDecimal soldQty = BigDecimal.ZERO;
		for (Object[] row : searchResults) {
			if(String.valueOf(row[0]).equalsIgnoreCase("Purchase")) {
				purchasePrice = new BigDecimal(String.valueOf(row[1]));
				purchasedQty = new BigDecimal(String.valueOf(row[2]));
			} else {
				salePrice = new BigDecimal(String.valueOf(row[1]));
				soldQty = new BigDecimal(String.valueOf(row[2]));
			}
		}
		if(purchasedQty.compareTo(BigDecimal.ZERO) == 0 || soldQty.compareTo(BigDecimal.ZERO) == 0) {
			throw new NoDataException("No purchase or sale data could be found.");
		}
		BigDecimal purchasePricePerQty = purchasePrice.divide(purchasedQty);
		BigDecimal profit = salePrice.subtract(soldQty.multiply(purchasePricePerQty));
		return profit;
		
	}
	
}
