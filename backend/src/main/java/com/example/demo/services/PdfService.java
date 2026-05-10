package com.example.demo.services;
import java.io.ByteArrayInputStream;
import java.util.List;


import com.example.demo.entity.Expense;


public interface PdfService {
	public ByteArrayInputStream generateExpensePdf(List<Expense> expenses) throws Exception ;
	
	

}
