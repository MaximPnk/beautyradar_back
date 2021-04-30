package ru.beautyradar.frontgateway.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "master_workday", schema = "beauty")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MasterWorkDayEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "master_workday_id")
    private Long id;

    @OneToOne (cascade = CascadeType.ALL)
    @Column(name = "master_id")
    private MasterEntity masterEntity;

    @Column(name = "date")
    private LocalDateTime date;

    @Column(name = "start_time")
    private LocalDateTime startTime;

    @Column(name = "end_time")
    private LocalDateTime endTime;


}
