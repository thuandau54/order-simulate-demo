package com.demo.order_simulate_demo.service.bases;

import com.demo.order_simulate_demo.response.FileResponse;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.List;

public abstract class BaseExportExcel {

    protected Workbook workbook;
    protected Sheet sheet;
    protected CellStyle headerStyle;
    protected CellStyle dataStyle;

    protected void loadBaseExportExcel() {
        // Initialize XSSFWorkbook for .xlsx files
        this.workbook = new XSSFWorkbook();
        initializeStyles();
    }

    /**
     * Constructor for loading a template file
     * @param templatePath Path to the template Excel file
     * @throws IOException If an I/O error occurs while loading the template
     */
    protected void loadBaseExportExcel(String templatePath) {
        try {
            ClassLoader classLoader = getClass().getClassLoader();
            InputStream inputStream = classLoader.getResourceAsStream(templatePath);
            //FileInputStream fileIn = new FileInputStream(templatePath)
            this.workbook = new XSSFWorkbook(inputStream);
            initializeStyles();
        } catch (IOException e) {
            throw new RuntimeException("Cannot load excel template");
        }
    }

    /**
     * Initialize cell styles for headers and data
     */
    protected void initializeStyles() {
        // Header style
        headerStyle = workbook.createCellStyle();
        Font headerFont = workbook.createFont();
        headerFont.setBold(true);
        headerFont.setFontHeightInPoints((short) 12);
        headerStyle.setFont(headerFont);
        headerStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        headerStyle.setAlignment(HorizontalAlignment.CENTER);
        headerStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        headerStyle.setBorderBottom(BorderStyle.THIN);
        headerStyle.setBorderTop(BorderStyle.THIN);
        headerStyle.setBorderLeft(BorderStyle.THIN);
        headerStyle.setBorderRight(BorderStyle.THIN);

        // Data style
        dataStyle = workbook.createCellStyle();
        dataStyle.setBorderBottom(BorderStyle.THIN);
        dataStyle.setBorderTop(BorderStyle.THIN);
        dataStyle.setBorderLeft(BorderStyle.THIN);
        dataStyle.setBorderRight(BorderStyle.THIN);
        dataStyle.setAlignment(HorizontalAlignment.LEFT);
        dataStyle.setVerticalAlignment(VerticalAlignment.CENTER);
    }

    /**
     * Create a sheet with the given name
     */
    protected void createSheet(String sheetName) {
        this.sheet = workbook.createSheet(sheetName);
    }

    protected void createSheet() {
        this.sheet = workbook.getSheetAt(0);
//        this.sheet = workbook.createSheet(sheetName);
    }

    /**
     * Load an Excel template from a file path.
     *
     * @param path Path to the Excel template file.
     */
//    protected void loadTemplate(String path) {
//        try {
//            ClassLoader classLoader = getClass().getClassLoader();
//            InputStream templateStream = classLoader.getResourceAsStream(path);
////            InputStream templateStream = new FileInputStream(path);
//            workbook = new Workbook(templateStream);
//            Style defaultStyle = workbook.getDefaultStyle();
//            Font font = defaultStyle.getFont();
//            font.setName("Times New Roman");
//
//            // Áp dụng style mặc định mới
//            workbook.setDefaultStyle(defaultStyle);
//        } catch (Exception e) {
//            handleException("Error loading Excel template. with path: " + path, e);
//        }
//    }

    /**
     * Create header row
     * @param headers List of header names
     */
    protected void createHeaderRow(List<String> headers) {
        if (sheet == null) {
            throw new IllegalStateException("Sheet must be created before adding headers");
        }

        Row headerRow = sheet.createRow(0);
        for (int i = 0; i < headers.size(); i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(headers.get(i));
            cell.setCellStyle(headerStyle);
            sheet.autoSizeColumn(i); // Auto-resize column based on content
        }
    }

    /**
     * Create header row at the specified row and column
     * @param headers List of header names
     * @param startRow Starting row index (0-based)
     * @param startColumn Starting column index (0-based)
     */
    protected void createHeaderRow(List<String> headers, int startRow, int startColumn) {
        if (sheet == null) {
            throw new IllegalStateException("Sheet must be created before adding headers");
        }
        if (startRow < 0 || startColumn < 0) {
            throw new IllegalArgumentException("startRow and startColumn must be non-negative");
        }

        Row headerRow = sheet.createRow(startRow);
        for (int i = 0; i < headers.size(); i++) {
            Cell cell = headerRow.createCell(startColumn + i);
            cell.setCellValue(headers.get(i));
            cell.setCellStyle(headerStyle);
            sheet.autoSizeColumn(startColumn + i); // Auto-resize column based on content
        }
    }

    /**
     * Add data rows (to be implemented by subclasses)
     * @param data List of data to be written
     */
    protected abstract <T> void addDataRows(List<T> data, int startRow, int startColumn);

    /**
     * Write the workbook to a file
     * @param filePath Path to save the Excel file
     * @throws IOException If an I/O error occurs
     */
//    public void writeToFile(String filePath) {
//        try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
//            workbook.write(fileOut);
//        } catch (Exception e) {
//            throw new RuntimeException("Cannot write to file");
//        } finally {
//            closeWorkbook();
//        }
//    }
    public FileResponse writeToFile(String filePath) {
        try (ByteArrayOutputStream byteOut = new ByteArrayOutputStream()) {
            workbook.write(byteOut);
            byte[] fileData = byteOut.toByteArray();

            // Write to file
//            try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
//                fileOut.write(fileData);
//            }

            // Calculate file size in human-readable format
            String fileSize = formatFileSize(fileData.length);

            // Extract file name from path
            String fileName = filePath.substring(filePath.lastIndexOf("/") + 1);

            // Set file type
            String fileType = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";

            return new FileResponse(fileName, fileType, fileSize, fileData);
        } catch (IOException e) {
            throw new RuntimeException("ERROR: " + e.getMessage());
        } finally {
            closeWorkbook();
        }
    }

    private String formatFileSize(long bytes) {
        if (bytes < 1024) return bytes + " B";
        int exp = (int) (Math.log(bytes) / Math.log(1024));
        String pre = String.valueOf("KMGTPE".charAt(exp - 1));
        return String.format("%.1f %sB", bytes / Math.pow(1024, exp), pre);
    }

    /**
     * Write the workbook to an output stream
     * @param outputStream Output stream to write to
     * @throws IOException If an I/O error occurs
     */
    public void writeToStream(OutputStream outputStream) throws IOException {
        try {
            workbook.write(outputStream);
        } finally {
            closeWorkbook();
        }
    }

    /**
     * Close the workbook to free resources
     */
    protected void closeWorkbook() {
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

    /**
     * Get the header style
     * @return CellStyle for headers
     */
    protected CellStyle getHeaderStyle() {
        return headerStyle;
    }

    /**
     * Get the data style
     * @return CellStyle for data
     */
    protected CellStyle getDataStyle() {
        return dataStyle;
    }

}
