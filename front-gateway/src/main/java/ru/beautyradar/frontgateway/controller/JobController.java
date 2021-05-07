package ru.beautyradar.frontgateway.controller;

import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.beautyradar.frontgateway.dto.JobDto;
import ru.beautyradar.frontgateway.dto.wrap.Resp;
import ru.beautyradar.frontgateway.dto.wrap.swagger.JobListResponse;
import ru.beautyradar.frontgateway.dto.wrap.swagger.JobResponse;
import ru.beautyradar.frontgateway.service.inter.JobService;

@Api(value = "JobController", tags = {"Job"})
@SwaggerDefinition(tags = {@Tag(name = "Job Controller", description = "Контроллер записей")})
@RestController
@RequiredArgsConstructor
@RequestMapping("/job")
@Slf4j
public class JobController {

    private final JobService jobService;

    @ApiOperation(value = "Get job list", httpMethod = "GET", notes = "Получение списка записей", response = JobListResponse.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success")})
    @GetMapping("/")
    public ResponseEntity<?> getAllJobs() {
        return ResponseEntity.ok(jobService.getAllJobsDto());
    }

    @ApiOperation(value = "Get job list by master id", httpMethod = "GET", notes = "Получение списка записей мастера", response = JobListResponse.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success")})
    @GetMapping("/master/{masterId}")
    public ResponseEntity<?> getAllJobsByMasterId(@PathVariable("masterId") Long masterId) {
        return ResponseEntity.ok(jobService.getAllJobsDtoByMasterId(masterId));
    }

    @ApiOperation(value = "Get job list by client id", httpMethod = "GET", notes = "Получение списка записей клиента", response = JobListResponse.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success")})
    @GetMapping("/client/{clientId}")
    public ResponseEntity<?> getAllJobsByClientId(@PathVariable("clientId") Long clientId) {
        return ResponseEntity.ok(jobService.getAllJobsDtoByClientId(clientId));
    }

    @ApiOperation(value = "Get job list by master workday id", httpMethod = "GET", notes = "Получение списка записей мастера за день", response = JobListResponse.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success")})
    @GetMapping("/master-workday/{masterWorkdayId}")
    public ResponseEntity<?> getAllJobsByMasterWorkdayId(@PathVariable("masterWorkdayId") Long masterWorkdayId) {
        return ResponseEntity.ok(jobService.getAllJobsDtoByMasterWorkdayId(masterWorkdayId));
    }

    @ApiOperation(value = "Get job list by service description id", httpMethod = "GET", notes = "Получение списка записей мастера по услуге", response = JobListResponse.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success")})
    @GetMapping("/service-description/{serviceDescriptionId}")
    public ResponseEntity<?> getAllJobsByServiceDescriptionId(@PathVariable("serviceDescriptionId") Long serviceDescriptionId) {
        return ResponseEntity.ok(jobService.getAllJobsDtoByServiceDescriptionId(serviceDescriptionId));
    }

    @ApiOperation(value = "Get job by id", httpMethod = "GET", notes = "Получение записи по id", response = JobResponse.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success")})
    @GetMapping("/{jobId}")
    public ResponseEntity<?> getJobById(@PathVariable("jobId") Long jobId) {
        return ResponseEntity.ok(jobService.getJobDtoById(jobId));
    }

    @ApiOperation(value = "Create job", httpMethod = "POST", notes = "Создание записи", response = JobResponse.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success")})
    @PostMapping("/")
    public ResponseEntity<?> createJob(@RequestBody JobDto jobDto) {
        return ResponseEntity.ok(jobService.createJob(jobDto));
    }

    @ApiOperation(value = "Update job", httpMethod = "PUT", notes = "Изменение записи", response = JobResponse.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success")})
    @PutMapping("/{jobId}")
    public ResponseEntity<?> updateJob(@PathVariable("jobId") Long jobId, @RequestBody JobDto jobDto) {
        return ResponseEntity.ok(jobService.updateJob(jobId, jobDto));
    }

    @ApiOperation(value = "Delete job by id", httpMethod = "DELETE", notes = "Удаление записи по id", response = Resp.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success")})
    @DeleteMapping("/{jobId}")
    public ResponseEntity<?> deleteJobById(@PathVariable("jobId") Long jobId) {
        return ResponseEntity.ok(jobService.deleteJobById(jobId));
    }

}
