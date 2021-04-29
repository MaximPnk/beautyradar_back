package ru.beautyradar.pushservice.controller;

import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.beautyradar.pushservice.dto.wrap.Resp;
import ru.beautyradar.pushservice.dto.wrap.swagger.StringResponse;
import ru.beautyradar.pushservice.model.PrivateMsg;
import ru.beautyradar.pushservice.service.inter.FirebaseService;
import ru.beautyradar.pushservice.service.inter.UserService;

@Api(value = "FirebaseController", tags = {"Firebase"})
@SwaggerDefinition(tags = {@Tag(name = "Firebase Controller", description = "Контроллер push-уведомлений")})
@RestController
@RequestMapping("/push")
@RequiredArgsConstructor
public class FirebaseController {

    private final FirebaseService firebaseService;
    private final UserService userService;

    @ApiOperation(value = "Push", httpMethod = "POST", notes = "Отправка личного push-уведомления", response = StringResponse.class)
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Success")})
    @PostMapping("/")
    public ResponseEntity<?> sendMessage(@RequestBody PrivateMsg privateMsg) {
        Resp<?> tokenResponse = userService.getTokenByUpn(privateMsg.getUpn());
        if (tokenResponse.getCode() != 0) {
            return ResponseEntity.ok(tokenResponse);
        }

        return ResponseEntity.ok(firebaseService.send(privateMsg, (String) tokenResponse.getBody()));
    }
}
