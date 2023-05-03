package com.example.bike.entity;

import com.example.bike.enumeration.RoleName;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CacheConcurrencyStrategy;

@Getter
@Setter
@Entity
@Table(name = "role")
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "role_generator")
    @SequenceGenerator(name = "role_generator", sequenceName = "role_id_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Column(name = "name", columnDefinition = "role_name not null")
    @Enumerated(EnumType.STRING)
    private RoleName name = RoleName.USER;

}