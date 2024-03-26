package com.example.massimportuniversalendpoint.facade;

import com.example.massimportuniversalendpoint.enums.ServiceType;
import com.example.massimportuniversalendpoint.exception.MassImportException;
import com.example.massimportuniversalendpoint.provider.ExcelServiceProvider;
import com.example.massimportuniversalendpoint.service.ExcelService;
import com.zaxxer.hikari.HikariDataSource;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Service
@Slf4j
@RequiredArgsConstructor
public class MassImportFacade {
    private final ExcelServiceProvider excelServiceProvider;
    private final HikariDataSource hikariDataSource;

    public void saveEntitiesFromExcel(MultipartFile file, ServiceType serviceType) {
        Sheet sheet = getSheetByIndex(file, 0);
        saveEntitiesAsBatches(sheet, serviceType);
    }

    public byte[] generateTemplate(ServiceType serviceType) {
        return excelServiceProvider
                .getExcelService(serviceType)
                .generateTemplate();
    }

    public Sheet getSheetByIndex(MultipartFile file, Integer index) {
        Workbook workbook;
        try {
            workbook = new XSSFWorkbook(file.getInputStream());
        } catch (IOException exception) {
            throw new MassImportException("Couldn't create workbook from file", HttpStatus.BAD_REQUEST);
        }
        return workbook.getSheetAt(index);
    }

    private void saveEntitiesAsBatches(Sheet sheet, ServiceType serviceType) {
        int availableThreads = Runtime.getRuntime().availableProcessors() - 1;
        int totalObjects = sheet.getLastRowNum();
        int threadQuantity = Math.min(availableThreads, hikariDataSource.getMaximumPoolSize());
        int rowQuantity = totalObjects / threadQuantity;
        ExcelService excelService = excelServiceProvider.getExcelService(serviceType);
        ExecutorService executorService = Executors.newFixedThreadPool(threadQuantity);
        for (int i = 1; i <= sheet.getLastRowNum(); i += rowQuantity) {
            int startIndex = i;
            int endIndex = Math.min(i + rowQuantity - 1, totalObjects);
            executorService.execute(() -> {
                List<Object> batchRowList = new ArrayList<>();
                for (int j = startIndex; j <= endIndex; j++) {
                    batchRowList.add(excelService.createEntity(sheet.getRow(j)));
                }
                excelService.saveEntities(batchRowList);
            });
        }
        try {
            executorService.shutdown();
            executorService.awaitTermination(1, TimeUnit.HOURS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
