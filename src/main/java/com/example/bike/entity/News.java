package com.example.bike.entity;

import com.example.bike.enumeration.NewsStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "news")
public class News extends AbstractAuditingEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @NotNull
    @Column(name = "title", nullable = false, length = Integer.MAX_VALUE)
    private String title;

    @NotNull
    @Column(name = "description", nullable = false, length = Integer.MAX_VALUE)
    private String description;

    @Column(name = "thumbnail", length = Integer.MAX_VALUE)
    private String thumbnail;

    @Column(name = "cover_image", length = Integer.MAX_VALUE)
    private String coverImage;

    @Column(name = "sapo")
    private String sapo;

    @Column(name = "status", columnDefinition = "news_status not null")
    @Enumerated(EnumType.STRING)
    private NewsStatus status = NewsStatus.DRAFT;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

}