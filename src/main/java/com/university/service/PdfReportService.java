package com.university.service;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.TextAlignment;
import com.university.model.Student;
import java.io.ByteArrayOutputStream;
import java.util.List;

public class PdfReportService {

    public ByteArrayOutputStream generateStudentReport(List<Student> students) throws Exception {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        
        PdfWriter writer = new PdfWriter(outputStream);
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf);

        document.add(new Paragraph("STUDENT REPORT")
                .setTextAlignment(TextAlignment.CENTER)
                .setFontSize(18)
                .setBold());

        Table table = new Table(5);
        table.addHeaderCell("ID");
        table.addHeaderCell("Name");
        table.addHeaderCell("Email");
        table.addHeaderCell("Course");
        table.addHeaderCell("Registration Date");

        // Add student data
        for (Student student : students) {
            table.addCell(String.valueOf(student.getId()));
            table.addCell(student.getName());
            table.addCell(student.getEmail());
            table.addCell(student.getCourse() != null ? student.getCourse().getName() : "N/A");
            table.addCell(student.getRegistrationDate().toString());
        }

        document.add(table);
        document.close();

        return outputStream;
    }
}