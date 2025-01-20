package tz.project.guidebook.landmark.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LandmarkFindParams {
    String city;

    List<String> categories;

    @Min(message = "Rating min value is 1", value = 1)
    @Max(message = "Rating max value is 5", value = 5)
    Integer maxRating;

    @Min(message = "Rating min value is 1", value = 1)
    @Max(message = "Rating max value is 5", value = 5)
    Integer minRating;

    @Min(message = "Location lat min value is -90", value = -90)
    @Max(message = "Location lat max value is 90", value = 90)
    Double lat;

    @Min(message = "Location lon min value is -180", value = -180)
    @Max(message = "Location lon max value is 180", value = 180)
    Double lon;

    @Positive(message = "Radius param must be positive")
    Double radiusKM;

    @PositiveOrZero(message = "Form param must be positive or zero")
    Integer from = 0;

    @Positive(message = "Size param must be positive")
    Integer size = 10;

    String sort;
}
