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
    private String token;
    private String login;
    private MasterDto master;
    private String name;
    private String phone;
    private String email;
    private byte[] img;
    private Double rating;

}
