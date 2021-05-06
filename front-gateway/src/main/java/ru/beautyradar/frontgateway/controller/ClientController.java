package ru.beautyradar.frontgateway.controller;

import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.beautyradar.frontgateway.dto.wrap.swagger.*;
import ru.beautyradar.frontgateway.service.inter.ClientService;

@Api(value = "ClientController", tags = {"Client"})
@SwaggerDefinition(tags = {@Tag(name = "Client Controller", description = "Контроллер клиентов")})
@RestController
@RequiredArgsConstructor
@RequestMapping("/client")
@Slf4j
public class ClientController {

    //todo add to favorite
    //todo delete from favorite
    //todo get all favorites

    private final ClientService clientService;

    @ApiOperation(value = "Get client list", httpMethod = "GET", notes = "Получение списка клиентов", response = ClientListResponse.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success")})
    @GetMapping("/")
    public ResponseEntity<?> getAllClients() {
        return ResponseEntity.ok(clientService.getAllClientsDto());
    }

    @ApiOperation(value = "Get client by ID", httpMethod = "GET", notes = "Получение клиента по ID", response = ClientResponse.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success")})
    @GetMapping("/{id}")
    public ResponseEntity<?> getClientById(@PathVariable Long id) {
        return ResponseEntity.ok(clientService.getClientDtoById(id));
    }

    @ApiOperation(value = "Get client by user ID", httpMethod = "GET", notes = "Получить клиента по пользователю", response = ClientResponse.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success")})
    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getClientByUserId(@PathVariable("userId") Long userId) {
        return ResponseEntity.ok(clientService.getClientDtoByUserId(userId));
    }

}
