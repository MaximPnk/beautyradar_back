package ru.beautyradar.frontgateway.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "master_category", schema = "beauty")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MasterCategoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "master_category_id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @ManyToMany
    @JoinTable(name = "master_categories",
            joinColumns = @JoinColumn(name = "master_category_id"),
            inverseJoinColumns = @JoinColumn(name = "master_id"))
    private List<MasterEntity> master; //todo - нужен ли в БД crated_at?

    public void addMaster(MasterEntity entity) {
        this.master.add(entity);
    }
}

