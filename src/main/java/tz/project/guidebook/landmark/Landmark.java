package tz.project.guidebook.landmark;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import org.hibernate.validator.constraints.Length;
import tz.project.guidebook.rating.RatingRecord;

import java.util.List;

@Entity
@Table(name = "landmarks")
@Data
@EqualsAndHashCode(of = "id")
@ToString(exclude = "ratingRecords")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Landmark {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Null(message = "Id must not be set")
    Long id;

    @Column(name = "city", nullable = false)
    @Length(message = "City name length must be between 1 and 64 characters inclusive", min = 1, max = 64)
    String city;

    @Column(name = "name", nullable = false)
    @NotNull(message = "Landmark name must be set")
    @Length(message = "Landmark name length must be between 1 and 64 characters inclusive", min = 1, max = 64)
    String name;

    @Column(name = "category", nullable = false)
    @Length(message = "Category name length must be between 1 and 64 characters inclusive", min = 1, max = 64)
    String category;

    @Column(name = "location_lat", nullable = false)
    @NotNull(message = "Location lat must be set")
    @Min(message = "Location lat min value is -90", value = -90)
    @Max(message = "Location lat max value is 90", value = 90)
    Double locationLat;

    @Column(name = "location_lon", nullable = false)
    @NotNull(message = "Location lon must be set")
    @Min(message = "Location lon min value is -180", value = -180)
    @Max(message = "Location lon max value is 180", value = 180)
    Double locationLon;

    @Column(name = "average_rating", nullable = true)
//    @JoinColumn(table = "RatingRecord", name = "landmarkId")
//    @Formula(value = "avg(rating)") todo delete
    @Null(message = "Average rating must not be set")
    Double averageRating;

    @OneToMany(mappedBy = "landmarkId", cascade = CascadeType.ALL, orphanRemoval = true)
    List<RatingRecord> ratingRecords;

    /*Set<Comment> comments;

    List<RatingRecord> ratingRecords;*/ // todo delete
}
