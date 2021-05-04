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
@Table(name = "gallery", schema = "beauty")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GalleryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "gallery_id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "master_id")
    private MasterEntity master;

    @Column(name = "img")
    private String image;

    @Column(name = "preview_img")
    private String preview;

    @Column(name = "created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;



}
