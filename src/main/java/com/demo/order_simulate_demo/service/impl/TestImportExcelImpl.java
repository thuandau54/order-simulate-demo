package com.demo.order_simulate_demo.service.impl;

import com.demo.order_simulate_demo.service.TestImportExcel;
import com.demo.order_simulate_demo.service.bases.BaseImportExcel;
import org.apache.poi.ss.usermodel.Row;

public class TestImportExcelImpl extends BaseImportExcel implements TestImportExcel {
    @Override
    protected <T> T convertRowToData(Row row) {
        return null;
    }
}
