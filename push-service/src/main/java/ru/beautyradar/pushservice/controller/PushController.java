package ru.beautyradar.pushservice.controller;

import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.beautyradar.pushservice.dto.wrap.swagger.StringResponse;
import ru.beautyradar.pushservice.model.PrivatePush;
import ru.beautyradar.pushservice.service.inter.PushService;

@Api(value = "FirebaseController", tags = {"Firebase"})
@SwaggerDefinition(tags = {@Tag(name = "Firebase Controller", description = "Контроллер push-уведомлений")})
@RestController
@RequestMapping("/push")
@RequiredArgsConstructor
@Slf4j
public class PushController {

    private final PushService pushService;

    @ApiOperation(value = "Push", httpMethod = "POST", notes = "Отправка личного push-уведомления", response = StringResponse.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success")})
    @PostMapping("/")
    public ResponseEntity<?> sendMessage(@RequestBody PrivatePush privatePush) {
        return ResponseEntity.ok(pushService.send(privatePush));
    }

}
