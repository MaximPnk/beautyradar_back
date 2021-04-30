package ru.beautyradar.frontgateway.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "job_status", schema = "beauty")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobStatusEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "job_status_id")
    private Long id;

    @Column(name = "name")
    private String name;


}
