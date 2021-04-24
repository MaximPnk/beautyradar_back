package ru.beautyradar.frontgateway.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private Long id;
    private String upn;
    private String name;
    private String login;
    private byte[] img;

}
