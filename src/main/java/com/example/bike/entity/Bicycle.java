package com.example.bike.entity;

import com.fasterxml.jackson.databind.JsonNode;
import io.hypersistence.utils.hibernate.type.array.ListArrayType;
import io.hypersistence.utils.hibernate.type.json.JsonType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.Type;
import org.hibernate.type.SqlTypes;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "bicycle")
public class Bicycle extends AbstractAuditingEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bicycle_generator")
    @SequenceGenerator(name = "bicycle_generator", sequenceName = "bicycle_id_seq",allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @NotNull
    @Column(name = "name", nullable = false, length = Integer.MAX_VALUE)
    private String name;

    @Column(name = "thumbnail", length = Integer.MAX_VALUE)
    private String thumbnail;

    @Type(ListArrayType.class)
    @Column(name = "images", columnDefinition = "text[]")
    private List<String> images;

    @Type(JsonType.class)
    @Column(name = "information")
    @JdbcTypeCode(SqlTypes.JSON)
    private JsonNode information;

    @Type(JsonType.class)
    @Column(name = "suitable_user")
    @JdbcTypeCode(SqlTypes.JSON)
    private JsonNode suitableUser;

    @Type(JsonType.class)
    @Column(name = "transmission_system")
    @JdbcTypeCode(SqlTypes.JSON)
    private JsonNode transmissionSystem;

    @Type(JsonType.class)
    @Column(name = "frame")
    @JdbcTypeCode(SqlTypes.JSON)
    private JsonNode frame;

    @Type(JsonType.class)
    @Column(name = "wheelset")
    @JdbcTypeCode(SqlTypes.JSON)
    private JsonNode wheelset;

}