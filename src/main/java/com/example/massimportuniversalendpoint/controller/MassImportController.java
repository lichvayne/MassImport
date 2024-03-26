package com.example.massimportuniversalendpoint.controller;

import com.example.massimportuniversalendpoint.enums.ServiceType;
import com.example.massimportuniversalendpoint.facade.MassImportFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/mass-import")
@RequiredArgsConstructor
public class MassImportController {
    private final MassImportFacade massImportFacade;

    @GetMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> upload(
            @RequestParam MultipartFile file,
            @RequestParam("type") ServiceType type) {

        massImportFacade.saveEntitiesFromExcel(file, type);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .build();
    }

    @GetMapping(value = "/template")
    public ResponseEntity<byte[]> getTemplate(@RequestParam("type") ServiceType type) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"));
        headers.setContentDispositionFormData("attachment", type.toString() + ".xlsx");
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .headers(headers)
                .body(massImportFacade.generateTemplate(type));
    }

}
