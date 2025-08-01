package com.demo.order_simulate_demo.service.bases;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.apache.poi.wp.usermodel.HeaderFooterType;
import org.apache.poi.xwpf.usermodel.*;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTTblWidth;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STTblWidth;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigInteger;
import java.util.List;

public abstract class BaseExportWord {
    protected XWPFDocument document;

    public BaseExportWord() {
        // Initialize XWPFDocument for .docx files
        this.document = new XWPFDocument();
    }

    /**
     * Add a paragraph with the specified text
     * @param text The text content
     * @param isBold Whether the text should be bold
     * @param fontSize Font size in points
     * @param alignment Paragraph alignment
     * @return Created paragraph
     */
    protected XWPFParagraph addParagraph(String text, boolean isBold, int fontSize, ParagraphAlignment alignment) {
        XWPFParagraph paragraph = document.createParagraph();
        paragraph.setAlignment(alignment);
        XWPFRun run = paragraph.createRun();
        run.setText(text);
        run.setBold(isBold);
        run.setFontSize(fontSize);
        run.setFontFamily("Times New Roman");
        return paragraph;
    }

    /**
     * Add a table with headers and data
     * @param headers List of header names
     * @param data List of data rows (each row is a list of strings)
     */
    protected XWPFTable addTable(List<String> headers, List<List<String>> data) {
        XWPFTable table = document.createTable(headers.size() > 0 ? data.size() + 1 : data.size(), headers.size());

        // Set table width to 100%
        CTTblWidth tblWidth = table.getCTTbl().addNewTblPr().addNewTblW();
        tblWidth.setType(STTblWidth.DXA);
        tblWidth.setW(BigInteger.valueOf(9000)); // Adjust width as needed (in twentieths of a point)

        // Add headers
        if (!headers.isEmpty()) {
            XWPFTableRow headerRow = table.getRow(0);
            for (int i = 0; i < headers.size(); i++) {
                XWPFTableCell cell = headerRow.getCell(i);
                cell.setText(headers.get(i));
                setCellStyle(cell, true, 12, ParagraphAlignment.CENTER);
            }
        }

        // Add data rows
        for (int i = 0; i < data.size(); i++) {
            XWPFTableRow row = table.getRow(i + 1);
            List<String> rowData = data.get(i);
            for (int j = 0; j < rowData.size(); j++) {
                XWPFTableCell cell = row.getCell(j);
                cell.setText(rowData.get(j));
                setCellStyle(cell, false, 11, ParagraphAlignment.LEFT);
            }
        }

        return table;
    }

    /**
     * Set style for a table cell
     * @param cell The table cell
     * @param isBold Whether the text should be bold
     * @param fontSize Font size in points
     * @param alignment Paragraph alignment
     */
    protected void setCellStyle(XWPFTableCell cell, boolean isBold, int fontSize, ParagraphAlignment alignment) {
        for (XWPFParagraph paragraph : cell.getParagraphs()) {
            paragraph.setAlignment(alignment);
            for (XWPFRun run : paragraph.getRuns()) {
                run.setBold(isBold);
                run.setFontSize(fontSize);
                run.setFontFamily("Times New Roman");
            }
        }
    }

    /**
     * Add a header to the document
     * @param headerText Text for the header
     */
    protected void addHeader(String headerText) throws IOException, InvalidFormatException {
        XWPFHeader header = document.createHeader(HeaderFooterType.DEFAULT);
        XWPFParagraph paragraph = header.createParagraph();
        paragraph.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun run = paragraph.createRun();
        run.setText(headerText);
        run.setBold(true);
        run.setFontSize(14);
        run.setFontFamily("Times New Roman");
    }

    /**
     * Add a footer to the document
     * @param footerText Text for the footer
     */
    protected void addFooter(String footerText) throws IOException, InvalidFormatException {
        XWPFFooter footer = document.createFooter(HeaderFooterType.DEFAULT);
        XWPFParagraph paragraph = footer.createParagraph();
        paragraph.setAlignment(ParagraphAlignment.CENTER);
        XWPFRun run = paragraph.createRun();
        run.setText(footerText);
        run.setFontSize(10);
        run.setFontFamily("Times New Roman");
    }

    /**
     * Write the document to a file
     * @param filePath Path to save the Word file
     * @throws IOException If an I/O error occurs
     */
    public void writeToFile(String filePath) throws IOException {
        try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
            document.write(fileOut);
        } finally {
            closeDocument();
        }
    }

    /**
     * Write the document to an output stream
     * @param outputStream Output stream to write to
     * @throws IOException If an I/O error occurs
     */
    public void writeToStream(OutputStream outputStream) throws IOException {
        try {
            document.write(outputStream);
        } finally {
            closeDocument();
        }
    }

    /**
     * Close the document to free resources
     */
    protected void closeDocument() {
        try {
            if (document != null) {
                document.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Get the document instance
     * @return XWPFDocument
     */
    protected XWPFDocument getDocument() {
        return document;
    }

    /**
     * Abstract method to be implemented by subclasses to add custom content
     * @param data Data to be added to the document
     */
    protected abstract <T> void addContent(List<T> data);
}
