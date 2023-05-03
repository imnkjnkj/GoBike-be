package com.example.bike.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Getter
@Setter
@Entity
@Table(name = "category")
@Accessors(chain = true)
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Category extends AbstractAuditingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "category_generator")
    @SequenceGenerator(name = "category_generator", sequenceName = "category_id_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Integer id;

    @NotNull
    @Column(name = "name", nullable = false, length = Integer.MAX_VALUE)
    private String name;

    @NotNull
    @Column(name = "description", nullable = false, length = Integer.MAX_VALUE)
    private String description;

}