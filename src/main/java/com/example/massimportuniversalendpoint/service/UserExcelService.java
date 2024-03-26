package com.example.massimportuniversalendpoint.service;

import com.example.massimportuniversalendpoint.annotations.EnumExcelService;
import com.example.massimportuniversalendpoint.annotations.ExcelColumn;
import com.example.massimportuniversalendpoint.entity.User;
import com.example.massimportuniversalendpoint.enums.ServiceType;
import com.example.massimportuniversalendpoint.repository.UserRepository;
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
@RequiredArgsConstructor
@EnumExcelService(type = ServiceType.USER)
public class UserExcelService implements ExcelService<User> {
    private final UserRepository userRepository;

    @Override
    public void saveEntities(List<User> userList) {
        userRepository.saveAll(userList);
    }

    public User createEntity(Row row) {
        User user = new User();
        for (int i = 0; i <= User.class.getDeclaredFields().length; i++) {
            switch (i) {
                case 0:
                    user.setProfession(row.getCell(i).getStringCellValue());
                    break;
                case 1:
                    user.setFirstName(row.getCell(i).getStringCellValue());
                    break;
                case 2:
                    user.setLastName(row.getCell(i).getStringCellValue());
                    break;
                case 3:
                    user.setEmail(row.getCell(i).getStringCellValue());
                    break;
            }
        }
        return user;
    }

    public byte[] generateTemplate() {
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Users");
            Row headerRow = sheet.createRow(0);

            Field[] entityFields = User.class.getDeclaredFields();
            for (Field field : entityFields) {
                ExcelColumn excelColumn = field.getAnnotation(ExcelColumn.class);
                if (excelColumn != null) {
                    int columnIndex = excelColumn.columnIndex();
                    Cell cell = headerRow.createCell(columnIndex);
                    cell.setCellValue(field.getName());
                }
            }
            try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
                workbook.write(outputStream);
                return outputStream.toByteArray();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new byte[0];
    }

}

