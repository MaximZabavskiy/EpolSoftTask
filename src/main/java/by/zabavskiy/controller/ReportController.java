package by.zabavskiy.controller;

import by.zabavskiy.config.AppProperties;
import by.zabavskiy.config.ExcelExporter;
import by.zabavskiy.controller.file.AmazonUploadFileService;
import by.zabavskiy.domain.Report;
import by.zabavskiy.domain.Task;
import by.zabavskiy.repository.impl.ReportSpringDataRepository;
import by.zabavskiy.service.TaskService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/reports")
@RequiredArgsConstructor
public class ReportController {

    private final TaskService taskService;
    private final AppProperties appProperties;
    private final ExcelExporter excelExporter;
    private final AmazonUploadFileService amazonUploadFileService;
    private final ReportSpringDataRepository reportSpringDataRepository;


    @ApiOperation(value = "Endpoint \"Report in XLSX\"")
    @GetMapping
    public ResponseEntity<Map<Object, Object>> findReportOnTasks(Long reportId) throws IOException {

        List<Task> taskList = taskService.findAll();
        String fileName = UUID.randomUUID().toString();
        excelExporter.writeToExcel(fileName, taskList);
        File file = new File(appProperties.getUploadPath() + fileName + ".xlsx");

        byte[] fileBytes = FileUtils.readFileToByteArray(file);

        String fileLink = amazonUploadFileService.uploadFile(fileBytes, reportId);

        Report report = reportSpringDataRepository.findById(reportId).orElse(report = new Report());

        report.setFileLink(fileLink);
        reportSpringDataRepository.save(report);

        return new ResponseEntity<>(Collections.singletonMap("fileLink", fileLink), HttpStatus.CREATED);
    }

}
