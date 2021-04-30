package ru.beautyradar.frontgateway.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "job", schema = "beauty")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "job_id")
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @Column(name = "master_workday_id")
    private MasterWorkDayEntity masterWorkDay;

    @OneToOne(cascade = CascadeType.ALL)
    @Column(name = "client_id")
    private ClientEntity clientEntity;

    @OneToOne (cascade = CascadeType.ALL)
    @Column(name = "service_description_id")
    private ServiceDescriptionEntity serviceDescription;

    @OneToOne (cascade = CascadeType.ALL)
    @Column(name = "job_status_id")
    private JobStatusEntity jobStatus;

    @Column(name = "start_time")
    private LocalDateTime startTime;

    @Column(name = "end_time")
    private LocalDateTime endTime;


    @Column(name = "created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private LocalDateTime updatedAt;



}
