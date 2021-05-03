package ru.beautyradar.frontgateway.event;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;
import ru.beautyradar.frontgateway.entity.UserEntity;

@Getter
public class SaveClientEvent extends ApplicationEvent {

    private final UserEntity userEntity;

    public SaveClientEvent(Object source, UserEntity userEntity) {
        super(source);
        this.userEntity = userEntity;
    }
}
