package tz.project.guidebook.landmark.repository;

import lombok.experimental.UtilityClass;
import org.springframework.data.jpa.domain.Specification;
import tz.project.guidebook.landmark.Landmark;

import java.util.List;

@UtilityClass
public class LandmarkSpecifications {
    public static Specification<Landmark> hasCityEquals(String city) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("city"), city));
    }

    public static Specification<Landmark> hasCategoryEquals(List<String> categories) {
        return ((root, query, criteriaBuilder) -> root.get("category").in(categories));
    }

    public static Specification<Landmark> hasAverageRatingLessThanOrEqual(int maxRating) {
        return ((root, query, criteriaBuilder) ->
                criteriaBuilder.lessThanOrEqualTo(root.get("averageRating"), maxRating));
    }

    public static Specification<Landmark> hasAverageRatingMoreThanOrEqual(int minRating) {
        return ((root, query, criteriaBuilder) ->
                criteriaBuilder.greaterThanOrEqualTo(root.get("averageRating"), minRating));
    }

    public static Specification<Landmark> hasLocationInRadiusKM(Double lat, Double lon, Double radiusKM) {
        return ((root, query, criteriaBuilder) ->
                criteriaBuilder.lessThanOrEqualTo(
                        criteriaBuilder.function("distance", Double.class,
                                criteriaBuilder.literal(lat),
                                criteriaBuilder.literal(lon),
                                root.get("locationLat"),
                                root.get("locationLon")),
                        radiusKM)
        );
    }
}
