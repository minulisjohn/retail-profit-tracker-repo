package com.profectus.retail.repo;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.profectus.retail.model.Transaction;

@Repository
public interface TransactionRepo extends JpaRepository<Transaction, Integer>{

//	@PersistenceContext
//	EntityManager entityManager;
//	
//	
//    CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
//    CriteriaQuery criteriaQuery = criteriaBuilder.createQuery(); 
//    Root<Transaction> from = criteriaQuery.from(Transaction.class);
//    
//    CriteriaQuery<Object> select = criteriaQuery.select(from);
//    TypedQuery<Object> typedQuery = entityManager.createQuery(select);
//    List<Object> resultlist = typedQuery.getResultList();
    
	@Query(
			value = "SELECT RT.TRANSACTION_TYPE, SUM(RT.PRICE), SUM(RT.QUANTITY) FROM RETAIL_TRANSACTION RT, RETAIL_PRODUCT RP, RETAIL_PRODUCT_CATEGORY RPC" + 
		    		" WHERE RT.TRANSACTION_DATE BETWEEN :fromDate AND :toDate" + 
		    		" AND RT.PRODUCT_ID = RP.PRODUCT_CODE AND  (:productCodes IS NULL OR RT.PRODUCT_ID IN (:productCodes))" + 
		    		" AND RP.CATEGORY_ID = RPC.ID AND (:categoryId IS NULL OR RPC.ID = :categoryId)" + 
		    		" AND (:purchasePrice IS NULL OR RT.PRICE >= :purchasePrice)" + 
		    		" GROUP BY RT.TRANSACTION_TYPE, RT.PRODUCT_ID",
			nativeQuery = true)
	public List<Object[]> calculateProfit(LocalDate fromDate, LocalDate toDate, 
			Integer categoryId, List<String> productCodes, BigDecimal purchasePrice);

	
	@Query(
			value = "SELECT RT.TRANSACTION_TYPE, SUM(RT.PRICE), SUM(RT.QUANTITY) FROM RETAIL_TRANSACTION RT, RETAIL_PRODUCT RP, RETAIL_PRODUCT_CATEGORY RPC" + 
		    		" WHERE RP.CATEGORY_ID = RPC.ID AND (:categoryId IS NULL OR RPC.ID = :categoryId)" + 
		    		" GROUP BY RT.TRANSACTION_TYPE, RT.PRODUCT_ID",
			nativeQuery = true)
	public List<Object> calculateProfi(int categoryId);

}
