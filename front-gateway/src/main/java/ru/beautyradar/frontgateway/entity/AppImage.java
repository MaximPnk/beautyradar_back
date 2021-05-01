package ru.beautyradar.frontgateway.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class AppImage {
    @Id
    @GeneratedValue
    Long id;

    @Lob
    byte[] content;
}
