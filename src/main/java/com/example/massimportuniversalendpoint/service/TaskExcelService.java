package com.example.massimportuniversalendpoint.service;

import com.example.massimportuniversalendpoint.annotations.EnumExcelService;
import com.example.massimportuniversalendpoint.annotations.ExcelColumn;
import com.example.massimportuniversalendpoint.entity.Task;
import com.example.massimportuniversalendpoint.enums.ServiceType;
import com.example.massimportuniversalendpoint.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.ByteArrayOutputStream;
import java.lang.reflect.Field;
import java.util.List;

@Slf4j
@EnumExcelService(type = ServiceType.TASK)
@RequiredArgsConstructor
public class TaskExcelService implements ExcelService<Task> {
    private final TaskRepository taskRepository;

    @Override
    public void saveEntities(List<Task> taskList) {
        taskRepository.saveAll(taskList);
    }

    @Override
    public Task createEntity(Row row) {
        Task task = new Task();
        for (int i = 0; i <= Task.class.getDeclaredFields().length; i++) {
            Cell cell = row.getCell(i);
            switch (i) {
                case 0:
                    task.setName(cell.getStringCellValue());
                    break;
                case 1:
                    task.setDescription(cell.getStringCellValue());
                    break;
                case 2:
                    task.setPriority((int) cell.getNumericCellValue());
                    break;
                case 3:
                    task.setStartDate(cell.getLocalDateTimeCellValue());
                    break;
                case 4:
                    task.setEndDate(cell.getLocalDateTimeCellValue());
                    break;
                case 5:
                    task.setDeadline(cell.getLocalDateTimeCellValue());
                    break;
            }
        }
        return task;
    }

    public byte[] generateTemplate() {
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Tasks");
            Row headerRow = sheet.createRow(0);

            Field[] entityFields = Task.class.getDeclaredFields();
            for (Field field : entityFields) {
                ExcelColumn excelColumn = field.getAnnotation(ExcelColumn.class);
                if (excelColumn != null) {
                    int columnIndex = excelColumn.columnIndex();
                    Cell cell = headerRow.createCell(columnIndex);
                    cell.setCellValue(field.getName());
                }
            }
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            workbook.write(outputStream);
            return outputStream.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new byte[0];
    }
}


