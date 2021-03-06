package ru.beautyradar.uploadservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

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

    @Column(name = "created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;

    public GalleryEntity(MasterEntity master, String image) {
        this.master = master;
        this.image = image;
    }
}
