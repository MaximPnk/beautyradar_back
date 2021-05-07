package ru.beautyradar.frontgateway.controller;

import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.beautyradar.frontgateway.dto.JobStatusDto;
import ru.beautyradar.frontgateway.dto.wrap.Resp;
import ru.beautyradar.frontgateway.dto.wrap.swagger.JobStatusListResponse;
import ru.beautyradar.frontgateway.dto.wrap.swagger.JobStatusResponse;
import ru.beautyradar.frontgateway.service.inter.JobStatusService;

@Api(value = "JobStatusController", tags = {"JobStatus"})
@SwaggerDefinition(tags = {@Tag(name = "Job Status Controller", description = "Контроллер состояний записей")})
@RestController
@RequiredArgsConstructor
@RequestMapping("/job-status")
@Slf4j
public class JobStatusController {

    private final JobStatusService jobStatusService;

    @ApiOperation(value = "Get job status list", httpMethod = "GET", notes = "Получение списка состояний записей", response = JobStatusListResponse.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success")})
    @GetMapping("/")
    public ResponseEntity<?> getAllJobStatuses() {
        return ResponseEntity.ok(jobStatusService.getAllJobStatusesDto());
    }

    @ApiOperation(value = "Get job status by id", httpMethod = "GET", notes = "Получение состояния записей по id", response = JobStatusResponse.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success")})
    @GetMapping("/{jobStatusId}")
    public ResponseEntity<?> getJobStatusById(@PathVariable("jobStatusId") Long jobStatusId) {
        return ResponseEntity.ok(jobStatusService.getJobStatusDtoById(jobStatusId));
    }

    @ApiOperation(value = "Create job status", httpMethod = "POST", notes = "Создание состояния записей", response = JobStatusResponse.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success")})
    @PostMapping("/")
    public ResponseEntity<?> createJobStatus(@RequestBody JobStatusDto jobStatusDto) {
        return ResponseEntity.ok(jobStatusService.createJobStatus(jobStatusDto));
    }

    @ApiOperation(value = "Update job status", httpMethod = "PUT", notes = "Изменение состояния записей", response = JobStatusResponse.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success")})
    @PutMapping("/{jobStatusId}")
    public ResponseEntity<?> updateJobStatus(@PathVariable("jobStatusId") Long jobStatusId, @RequestBody JobStatusDto jobStatusDto) {
        return ResponseEntity.ok(jobStatusService.updateJobStatus(jobStatusId, jobStatusDto));
    }

    @ApiOperation(value = "Delete job status by id", httpMethod = "DELETE", notes = "Удаление состояния записей по id", response = Resp.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success")})
    @DeleteMapping("/{jobStatusId}")
    public ResponseEntity<?> deleteJobStatusById(@PathVariable("jobStatusId") Long jobStatusId) {
        return ResponseEntity.ok(jobStatusService.deleteJobStatusById(jobStatusId));
    }

}
