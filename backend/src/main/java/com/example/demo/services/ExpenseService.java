package com.example.demo.services;

import java.util.List;

import com.example.demo.dto.AnalyticsDto;
import com.example.demo.dto.CategoryExpenseDto;
import com.example.demo.entity.Expense;
import com.example.demo.entity.User;

public interface ExpenseService {

	Expense addExpense(Expense expense, User user);

	List<Expense> getUserExpenses(User user);

	List<Expense> getAllExpenses();

	Expense getExpenseById(Long id);

	Expense updateExpense(Long id, Expense expenseDetails);

	void deleteExpense(Long id);

	List<Expense> searchByCategory(String category);

	List<Expense> searchByTitle(String keyword);

	// Sorting
	List<Expense> sortExpenses(String field);

	// Monthlly report
	Double getMonthlyExpense(User user, int month);

	//charts
	List<CategoryExpenseDto> getCategoryWiseExpenses(User user);
	
	//report& charts
	List<AnalyticsDto> monthlyAnalytics(User user);

	List<AnalyticsDto> dayWiseAnalytics(User user);

	List<AnalyticsDto> categoryWiseAnalytics(User user);
}
