package ru.beautyradar.frontgateway.event;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;
import ru.beautyradar.frontgateway.entity.MasterEntity;

@Getter
public class UpdateMasterRatingEvent extends ApplicationEvent {

    private final MasterEntity masterEntity;

    public UpdateMasterRatingEvent(Object source, MasterEntity masterEntity) {
        super(source);
        this.masterEntity = masterEntity;
    }
}
