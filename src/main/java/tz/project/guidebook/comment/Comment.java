package tz.project.guidebook.comment;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Null;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "comments")
@Data
@EqualsAndHashCode(of = "id")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Null(message = "Rating id must not be set")
    Long id;

    @Column(name = "description", nullable = false)
    @NotBlank(message = "Comment description must be set")
    String description;

    @Column(name = "landmark_id", nullable = false)
    Long landmarkId;
}
