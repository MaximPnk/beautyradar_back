package ru.beautyradar.frontgateway.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

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

    @ManyToOne
    @JoinColumn(name = "master_id")
    private MasterEntity master;

    @ManyToOne
    @JoinColumn(name = "service_category_id")
    private ServiceCategoryEntity serviceCategory;

    @Column(name = "duration")
    private Integer duration;

    @Column(name = "price")
    private BigDecimal price;

}
