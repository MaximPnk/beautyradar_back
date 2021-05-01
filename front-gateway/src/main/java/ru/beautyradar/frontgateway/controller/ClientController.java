package ru.beautyradar.frontgateway.controller;

import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.beautyradar.frontgateway.dto.ClientDto;
import ru.beautyradar.frontgateway.dto.wrap.swagger.*;
import ru.beautyradar.frontgateway.service.inter.ClientService;

@Api(value = "ClientController", tags = {"Client"})
@SwaggerDefinition(tags = {@Tag(name = "Client Controller", description = "Контроллер клиентов")})
@RestController
@RequiredArgsConstructor
@RequestMapping("/client")
@Slf4j
public class ClientController {
    @Autowired
    private ClientService service;

    @ApiOperation(value = "Get client list", httpMethod = "GET", notes = "Получение списка клиентов", response = ClientListResponse.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success")})
    @GetMapping("/")
    public ResponseEntity<?> getClients() {
        return ResponseEntity.ok(service.getAllClients());
    }

    @ApiOperation(value = "Get client by ID", httpMethod = "GET", notes = "Получение клиента по ID", response = ClientResponseID.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success")})
    @GetMapping("/{id}")
    public ResponseEntity<?> getUserByUpn(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @ApiOperation(value = "Create new client", httpMethod = "POST", notes = "Создание клиента", response = ClientResponse.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success")})
    @PostMapping("/")
    public ResponseEntity<?> saveUser(@RequestBody ClientDto dto) {
        return ResponseEntity.ok(service.saveClient(dto));
    }

}
