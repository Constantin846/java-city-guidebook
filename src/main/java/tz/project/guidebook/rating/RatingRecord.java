package tz.project.guidebook.rating;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "rating_records")
@Data
@EqualsAndHashCode(of = "id")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RatingRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Null(message = "Rating id must not be set")
    Long id;

    @Column(name = "rating", nullable = false)
    @NotNull(message = "Rating must be set")
    @Min(message = "Rating min value is 1", value = 1)
    @Max(message = "Rating max value is 5", value = 5)
    Double rating;

    @Column(name = "landmark_id", nullable = false)
    Long landmarkId;
}
