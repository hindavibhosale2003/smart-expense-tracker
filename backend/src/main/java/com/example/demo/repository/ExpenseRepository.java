package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.dto.CategoryExpenseDto;
import com.example.demo.entity.Expense;
import com.example.demo.entity.User;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {

	List<Expense> findByUser(User user);

	List<Expense> findByCategory(String category);

	List<Expense> findByTitleContaining(String keyword);

	// monthly report
	@Query("SELECT SUM(e.amount) FROM Expense e " + "WHERE e.user = :user " + "AND MONTH(e.date)=:month")
	Double getMonthlyExpenseByUser(@Param("user") User user, @Param("month") int month);
	
	//charts
	@Query(
			 "SELECT new com.example.demo.dto.CategoryExpenseDto(" +
			 "e.category, SUM(e.amount)) " +
			 "FROM Expense e " +
			 "WHERE e.user = :user " +
			 "GROUP BY e.category"
			)
			List<CategoryExpenseDto>
			getCategoryWiseExpenses(
			        @Param("user") User user);
}