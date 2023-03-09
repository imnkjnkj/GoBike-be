package com.example.bike.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@Embeddable
public class UserLikeId implements Serializable {
    private static final long serialVersionUID = 393140477389305432L;
    @NotNull
    @Column(name = "news_id", nullable = false)
    private Integer newsId;

    @NotNull
    @Column(name = "user_id", nullable = false)
    private Integer userId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        UserLikeId entity = (UserLikeId) o;
        return Objects.equals(this.newsId, entity.newsId) &&
                Objects.equals(this.userId, entity.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(newsId, userId);
    }

}