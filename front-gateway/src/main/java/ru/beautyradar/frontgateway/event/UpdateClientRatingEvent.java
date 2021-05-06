package ru.beautyradar.frontgateway.event;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;
import ru.beautyradar.frontgateway.entity.ClientEntity;

@Getter
public class UpdateClientRatingEvent extends ApplicationEvent {

    private final ClientEntity clientEntity;

    public UpdateClientRatingEvent(Object source, ClientEntity clientEntity) {
        super(source);
        this.clientEntity = clientEntity;
    }
}
