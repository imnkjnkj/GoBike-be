package com.example.bike.entity;

import com.example.bike.enumeration.NewsStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Entity
@Accessors(chain = true)
@Table(name = "news")
public class News extends AbstractAuditingEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "news_generator")
    @SequenceGenerator(name = "news_generator", sequenceName = "news_id_seq", allocationSize = 1)
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