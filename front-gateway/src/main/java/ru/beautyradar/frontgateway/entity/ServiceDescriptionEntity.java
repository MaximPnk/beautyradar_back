package ru.beautyradar.frontgateway.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

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

    @OneToOne (cascade = CascadeType.ALL)
    @Column(name = "master_id")
    private MasterEntity masterEntity;

    @OneToOne (cascade = CascadeType.ALL)
    @Column(name = "service_category_id")
    private ServiceCategory serviceCategory;

    @Column(name = "duration")
    private Integer duration; //todo - переделать на класс Duration (интервал времени)

    @Column(name = "price")
    private Double price;

}
