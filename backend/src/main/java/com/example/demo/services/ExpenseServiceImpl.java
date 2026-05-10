package com.example.demo.services;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.example.demo.constant.ErrorConstant;
import com.example.demo.dto.AnalyticsDto;
import com.example.demo.dto.CategoryExpenseDto;
import com.example.demo.entity.Expense;
import com.example.demo.entity.User;
import com.example.demo.excepation.ExpenseServiceException;
import com.example.demo.repository.ExpenseRepository;

@Service
public class ExpenseServiceImpl implements ExpenseService {

	@Autowired
	private ExpenseRepository expenseRepository;

	@Override
	public Expense addExpense(Expense expense, User user) {

		try {

			expense.setUser(user);

			return expenseRepository.save(expense);

		} catch (Exception e) {

			throw new ExpenseServiceException(ErrorConstant.EXPENSE_SAVE_FAILED, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public List<Expense> getUserExpenses(User user) {

		List<Expense> expenses = expenseRepository.findByUser(user);

		if (expenses.isEmpty()) {

			throw new ExpenseServiceException("No expenses found for this user", HttpStatus.NOT_FOUND);
		}

		return expenses;
	}

	@Override
	public List<Expense> getAllExpenses() {

		List<Expense> expenses = expenseRepository.findAll();

		if (expenses.isEmpty()) {

			throw new ExpenseServiceException("Expense list is empty", HttpStatus.NO_CONTENT);
		}

		return expenses;
	}

	@Override
	public Expense getExpenseById(Long id) {

		return expenseRepository.findById(id).orElseThrow(() ->

		new ExpenseServiceException(ErrorConstant.EXPENSE_NOT_FOUND, HttpStatus.NOT_FOUND));
	}

	@Override
	public Expense updateExpense(Long id, Expense expenseDetails) {

		Expense expense = getExpenseById(id);

		try {

			expense.setTitle(expenseDetails.getTitle());

			expense.setAmount(expenseDetails.getAmount());

			expense.setCategory(expenseDetails.getCategory());

			expense.setDate(expenseDetails.getDate());

			expense.setDescription(expenseDetails.getDescription());

			return expenseRepository.save(expense);

		} catch (Exception e) {

			throw new ExpenseServiceException("Failed to update expense", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public void deleteExpense(Long id) {

		Expense expense = getExpenseById(id);

		try {

			expenseRepository.delete(expense);

		} catch (Exception e) {

			throw new ExpenseServiceException(ErrorConstant.EXPENSE_DELETE_FAILED, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@Override
	public List<Expense> searchByCategory(String category) {

		return expenseRepository.findByCategory(category);
	}

	@Override
	public List<Expense> searchByTitle(String keyword) {

		return expenseRepository.findByTitleContaining(keyword);
	}

	@Override
	public List<Expense> sortExpenses(String field) {

		return expenseRepository.findAll(Sort.by(Sort.Direction.ASC, field));
	}

	@Override
	public Double getMonthlyExpense(User user, int month) {

		if (month < 1 || month > 12) {

			throw new ExpenseServiceException("Invalid month value", HttpStatus.BAD_REQUEST);
		}

		Double total = expenseRepository.getMonthlyExpenseByUser(user, month);

		if (total == null) {

			throw new ExpenseServiceException("No expense data found", HttpStatus.NOT_FOUND);
		}

		return total;
	}

	//charts
	@Override
	public List<CategoryExpenseDto>
	getCategoryWiseExpenses(
	        User user) {

	    List<CategoryExpenseDto> report =
	            expenseRepository
	                    .getCategoryWiseExpenses(user);

	    if (report.isEmpty()) {

	        throw new ExpenseServiceException(
	                "No expense data found",
	                HttpStatus.NOT_FOUND
	        );
	    }

	    return report;
	}
	
	
	//Report & charts
	@Override
	public List<AnalyticsDto> monthlyAnalytics(
	        User user) {

	    List<Expense> expenses =
	            expenseRepository.findByUser(user);

	    Map<String, Double> map =
	            expenses.stream()

	                    .collect(Collectors.groupingBy(

	                            expense -> expense
	                                    .getDate()
	                                    .getMonth()
	                                    .toString(),

	                            Collectors.summingDouble(
	                                    Expense::getAmount
	                            )
	                    ));

	    return map.entrySet()
	            .stream()

	            .map(entry ->
	                    new AnalyticsDto(
	                            entry.getKey(),
	                            entry.getValue()
	                    ))

	            .collect(Collectors.toList());
	}

	@Override
	public List<AnalyticsDto> dayWiseAnalytics(
	        User user) {

	    List<Expense> expenses =
	            expenseRepository.findByUser(user);

	    Map<String, Double> map =
	            expenses.stream()

	                    .collect(Collectors.groupingBy(

	                            expense -> expense
	                                    .getDate()
	                                    .toString(),

	                            Collectors.summingDouble(
	                                    Expense::getAmount
	                            )
	                    ));

	    return map.entrySet()
	            .stream()

	            .map(entry ->
	                    new AnalyticsDto(
	                            entry.getKey(),
	                            entry.getValue()
	                    ))

	            .collect(Collectors.toList());
	}

	@Override
	public List<AnalyticsDto> categoryWiseAnalytics(
	        User user) {

	    List<Expense> expenses =
	            expenseRepository.findByUser(user);

	    Map<String, Double> map =
	            expenses.stream()

	                    .collect(Collectors.groupingBy(

	                            Expense::getCategory,

	                            Collectors.summingDouble(
	                                    Expense::getAmount
	                            )
	                    ));

	    return map.entrySet()
	            .stream()

	            .map(entry ->
	                    new AnalyticsDto(
	                            entry.getKey(),
	                            entry.getValue()
	                    ))

	            .collect(Collectors.toList());
	}
}
