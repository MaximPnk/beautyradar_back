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
@Table(name = "client", schema = "beauty")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClientEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "client_id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @Column(name = "rating")
    private Double rating;

    @Column(name = "created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @ManyToMany
    @JoinTable(name = "favorite",
            joinColumns = @JoinColumn(name = "client_id"),
            inverseJoinColumns = @JoinColumn(name = "master_id"))
    private List<MasterEntity> favoriteMasters;

    @ManyToMany
    @JoinTable(name = "master_review",
            joinColumns = @JoinColumn(name = "client_id"),
            inverseJoinColumns = @JoinColumn(name = "master_review_id"))
    private List<MasterReviewEntity> masterReviews;

    public ClientEntity(UserEntity user) {
        this.user = user;
    }
}
