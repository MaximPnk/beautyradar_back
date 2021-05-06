package ru.beautyradar.frontgateway.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "service_description", schema = "beauty")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ServiceDescriptionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "service_description_id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "master_id")
    private MasterEntity master;

    @OneToOne
    @JoinColumn(name = "service_category_id")
    private ServiceCategoryEntity serviceCategory;

    @Column(name = "duration")
    private Integer duration; //todo - переделать на класс Duration (интервал времени)

    @Column(name = "price")
    private Double price;

}
