package com.example.demo.controller;

import java.io.ByteArrayInputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.core.Authentication;

import org.springframework.web.bind.annotation.*;

import com.example.demo.dto.AnalyticsDto;
import com.example.demo.dto.CategoryExpenseDto;
import com.example.demo.entity.Expense;
import com.example.demo.entity.User;

import com.example.demo.repository.UserRepository;
import com.example.demo.services.ExpenseService;
import com.example.demo.services.PdfService;

@RestController
@RequestMapping("/api/expenses")
public class ExpenseController {

	@Autowired
	private ExpenseService expenseService;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PdfService pdfService;

	@PostMapping
	public ResponseEntity<Object> addExpense(@RequestBody Expense expense, Authentication authentication) {

		String email = authentication.getName();

		User user = userRepository.findByEmail(email).orElseThrow();

		Expense savedExpense = expenseService.addExpense(expense, user);

		return new ResponseEntity<Object>(savedExpense, HttpStatus.CREATED);
	}

	@GetMapping
	public ResponseEntity<Object> getUserExpenses(Authentication authentication) {

		String email = authentication.getName();

		User user = userRepository.findByEmail(email).orElseThrow();

		List<Expense> expenses = expenseService.getUserExpenses(user);

		return new ResponseEntity<Object>(expenses, HttpStatus.OK);
	}

	@GetMapping("/get")
	public ResponseEntity<Object> getAllExpenses() {

		List<Expense> expenses = expenseService.getAllExpenses();

		return new ResponseEntity<Object>(expenses, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Object> getExpenseById(@PathVariable Long id) {

		Expense expense = expenseService.getExpenseById(id);

		return new ResponseEntity<Object>(expense, HttpStatus.OK);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Object> updateExpense(@PathVariable Long id, @RequestBody Expense expense) {

		Expense updatedExpense = expenseService.updateExpense(id, expense);

		return new ResponseEntity<Object>(updatedExpense, HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteExpense(@PathVariable Long id) {

		expenseService.deleteExpense(id);

		return new ResponseEntity<Object>("Expense Deleted Successfully", HttpStatus.OK);
	}

	@GetMapping("/category")
	public ResponseEntity<Object> searchByCategory(@RequestParam String category) {

		List<Expense> expenses = expenseService.searchByCategory(category);

		return new ResponseEntity<Object>(expenses, HttpStatus.OK);
	}

	@GetMapping("/search")
	public ResponseEntity<Object> searchByTitle(@RequestParam String keyword) {

		List<Expense> expenses = expenseService.searchByTitle(keyword);

		return new ResponseEntity<Object>(expenses, HttpStatus.OK);
	}

	// sorting
	@GetMapping("/sort")
	public ResponseEntity<Object> sortExpenses(@RequestParam String field) {

		List<Expense> expenses = expenseService.sortExpenses(field);

		return new ResponseEntity<Object>(expenses, HttpStatus.OK);
	}

	// Monthlly Report
	@GetMapping("/monthly-report")
	public ResponseEntity<Object> monthlyReport(@RequestParam int month, Authentication authentication) {

		String email = authentication.getName();

		User user = userRepository.findByEmail(email).orElseThrow();

		Double total = expenseService.getMonthlyExpense(user, month);

		return new ResponseEntity<Object>(total, HttpStatus.OK);
	}

	// charts
	@GetMapping("/chart")
	public ResponseEntity<Object> chartData(Authentication authentication) {

		String email = authentication.getName();

		User user = userRepository.findByEmail(email).orElseThrow();

		List<CategoryExpenseDto> report = expenseService.getCategoryWiseExpenses(user);

		return new ResponseEntity<Object>(report, HttpStatus.OK);
	}

	// Pdf file
	@GetMapping("/pdf")
	public ResponseEntity<byte[]> exportPdf(Authentication authentication) throws Exception {

		String email = authentication.getName();

		User user = userRepository.findByEmail(email).orElseThrow();

		List<Expense> expenses = expenseService.getUserExpenses(user);

		ByteArrayInputStream pdf = pdfService.generateExpensePdf(expenses);

		byte[] pdfBytes = pdf.readAllBytes();

		return ResponseEntity.ok()

				.header("Content-Disposition", "attachment; filename=expenses.pdf")

				.body(pdfBytes);
	}

	/* MONTHLY ANALYTICS */

	@GetMapping("/analytics/monthly")
	public ResponseEntity<Object> monthlyAnalytics(Authentication authentication) {

		String email = authentication.getName();

		User user = userRepository.findByEmail(email).orElseThrow();

		List<AnalyticsDto> data = expenseService.monthlyAnalytics(user);

		return new ResponseEntity<>(data, HttpStatus.OK);
	}

	/* DAY WISE ANALYTICS */

	@GetMapping("/analytics/daywise")
	public ResponseEntity<Object> dayWiseAnalytics(Authentication authentication) {

		String email = authentication.getName();

		User user = userRepository.findByEmail(email).orElseThrow();

		List<AnalyticsDto> data = expenseService.dayWiseAnalytics(user);

		return new ResponseEntity<>(data, HttpStatus.OK);
	}

	/* CATEGORY ANALYTICS */

	@GetMapping("/analytics/category")
	public ResponseEntity<Object> categoryAnalytics(Authentication authentication) {

		String email = authentication.getName();

		User user = userRepository.findByEmail(email).orElseThrow();

		List<AnalyticsDto> data = expenseService.categoryWiseAnalytics(user);

		return new ResponseEntity<>(data, HttpStatus.OK);
	}
}