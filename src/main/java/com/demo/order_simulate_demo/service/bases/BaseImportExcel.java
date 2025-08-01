package com.demo.order_simulate_demo.service.bases;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public abstract class BaseImportExcel {
    protected Workbook workbook;
    protected Sheet sheet;

    /**
     * Open an Excel file from a file path
     * @param filePath Path to the Excel file
     * @throws IOException If an I/O error occurs
     */
    public void openFile(String filePath) throws IOException {
        FileInputStream fileIn = new FileInputStream(filePath);
        openStream(fileIn);
    }

    /**
     * Open an Excel file from an input stream
     * @param inputStream Input stream of the Excel file
     * @throws IOException If an I/O error occurs
     */
    public void openStream(InputStream inputStream) throws IOException {
        this.workbook = new XSSFWorkbook(inputStream);
    }

    /**
     * Select a sheet by name
     * @param sheetName Name of the sheet
     */
    protected void selectSheet(String sheetName) {
        this.sheet = workbook.getSheet(sheetName);
        if (sheet == null) {
            throw new IllegalArgumentException("Sheet '" + sheetName + "' not found in the workbook");
        }
    }

    /**
     * Select a sheet by index
     * @param sheetIndex Index of the sheet (0-based)
     */
    protected void selectSheet(int sheetIndex) {
        this.sheet = workbook.getSheetAt(sheetIndex);
        if (sheet == null) {
            throw new IllegalArgumentException("Sheet at index " + sheetIndex + " not found in the workbook");
        }
    }

    /**
     * Read header row (assumed to be the first row)
     * @return List of header names
     */
    protected List<String> readHeaderRow() {
        if (sheet == null) {
            throw new IllegalStateException("Sheet must be selected before reading headers");
        }

        List<String> headers = new ArrayList<>();
        Row headerRow = sheet.getRow(0);
        if (headerRow != null) {
            for (Cell cell : headerRow) {
                headers.add(getCellValueAsString(cell));
            }
        }
        return headers;
    }

    /**
     * Read all data rows (excluding header)
     * @param <T> Type of the data objects
     * @return List of data objects
     */
    protected <T> List<T> readDataRows() {
        if (sheet == null) {
            throw new IllegalStateException("Sheet must be selected before reading data");
        }

        List<T> data = new ArrayList<>();
        int startRow = 1; // Skip header row
        for (int i = startRow; i <= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);
            if (row != null) {
                T item = convertRowToData(row);
                if (item != null) {
                    data.add(item);
                }
            }
        }
        return data;
    }

    /**
     * Convert a row to a specific data type (to be implemented by subclasses)
     * @param row The Excel row
     * @param <T> Type of the data object
     * @return Data object or null if the row is invalid
     */
    protected abstract <T> T convertRowToData(Row row);

    /**
     * Get cell value as string
     * @param cell The cell to read
     * @return String value of the cell
     */
    protected String getCellValueAsString(Cell cell) {
        if (cell == null) {
            return "";
        }
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    return cell.getDateCellValue().toString();
                } else {
                    // Handle numbers as strings, avoiding scientific notation
                    return String.format("%.0f", cell.getNumericCellValue());
                }
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            case FORMULA:
                return cell.getCellFormula();
            default:
                return "";
        }
    }

    /**
     * Close the workbook to free resources
     */
    public void closeWorkbook() {
        try {
            if (workbook != null) {
                workbook.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Get the workbook instance
     * @return Workbook
     */
    protected Workbook getWorkbook() {
        return workbook;
    }

    /**
     * Get the current sheet
     * @return Sheet
     */
    protected Sheet getSheet() {
        return sheet;
    }
}
