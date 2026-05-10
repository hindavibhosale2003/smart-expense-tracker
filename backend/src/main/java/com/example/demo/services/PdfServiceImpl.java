package com.example.demo.services;

import java.io.ByteArrayInputStream;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Expense;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

@Service
public class PdfServiceImpl implements PdfService{

	public ByteArrayInputStream generateExpensePdf(List<Expense> expenses) throws Exception {

		Document document = new Document();

		java.io.ByteArrayOutputStream out = new java.io.ByteArrayOutputStream();

		PdfWriter.getInstance(document, out);

		document.open();

		document.add(new Paragraph("Expense Report"));

		PdfPTable table = new PdfPTable(4);

		table.addCell("Title");
		table.addCell("Amount");
		table.addCell("Category");
		table.addCell("Date");

		for (Expense expense : expenses) {

			table.addCell(expense.getTitle());

			table.addCell(String.valueOf(expense.getAmount()));

			table.addCell(expense.getCategory());

			table.addCell(expense.getDate().toString());
		}

		document.add(table);

		document.close();

		return new ByteArrayInputStream(out.toByteArray());
	}
}
