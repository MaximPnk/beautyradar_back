package ru.beautyradar.frontgateway.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "service_category", schema = "beauty")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ServiceCategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "service_category_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "master_category_id")
    private MasterCategoryEntity masterCategory;

    @Column(name = "name")
    private String name;

    @Column(name="description")
    private String description;

}
