package com.demo.order_simulate_demo.service.impl;

import com.demo.order_simulate_demo.config.PropertiesConfig;
import com.demo.order_simulate_demo.constants.FileConstant;
import com.demo.order_simulate_demo.model.ProductModel;
import com.demo.order_simulate_demo.repository.ProductRepo;
import com.demo.order_simulate_demo.response.FileResponse;
import com.demo.order_simulate_demo.service.TestExportExcel;
import com.demo.order_simulate_demo.service.bases.BaseExportExcel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class TestExportExcelImpl extends BaseExportExcel implements TestExportExcel {

    private final PropertiesConfig propertiesConfig;
    private final ProductRepo productRepo;

    public FileResponse exportProduct() {
        String filePath = propertiesConfig.getPathExcel() + FileConstant.PRODUCT_TEMP;
        List<ProductModel> productModels = productRepo.getAllProduct();

        loadBaseExportExcel(filePath);
        createSheet();
        List<String> headers = Arrays.asList("ID", "Name", "Department");
        createHeaderRow(headers, 3, 2);
        addDataRows(productModels, 3, 2);

        return writeToFile(filePath);
    }

    @Override
    protected <T> void addDataRows(List<T> data, int startRow, int startColumn) {
        int rowNum = startRow;
        for (T item : data) {
            ProductModel employee = (ProductModel) item;
            Row row = sheet.createRow(rowNum++);
            row.createCell(1).setCellValue(employee.getCode());
            row.createCell(2).setCellValue(employee.getName());
            row.createCell(3).setCellValue(employee.getCount());

            for (int i = 0; i < 3; i++) {
                Cell cell = row.getCell(startColumn + i, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                cell.setCellStyle(dataStyle);
                sheet.autoSizeColumn(startColumn + i);
            }
        }
    }

}
