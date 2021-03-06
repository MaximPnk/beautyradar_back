package ru.beautyradar.pushservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PrivatePush {
    private Long userId;
    private String title;
    private String msg;
}
