package ru.beautyradar.frontgateway.dto.wrap;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Resp<T> {
    private int code;
    private String message;
    private T body;
}
