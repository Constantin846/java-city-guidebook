package tz.project.guidebook.landmark.dto;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.FieldDefaults;
import tz.project.guidebook.comment.Comment;
import tz.project.guidebook.landmark.Landmark;

import java.util.HashSet;
import java.util.Set;

@Data
@EqualsAndHashCode(callSuper = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class LandmarkCommentsDto extends Landmark {
    Set<Comment> comments;

    public LandmarkCommentsDto(Landmark landmark) {
        this.id = landmark.getId();
        this.city = landmark.getCity();
        this.name = landmark.getName();
        this.category = landmark.getCategory();
        this.locationLat = landmark.getLocationLat();
        this.locationLon = landmark.getLocationLon();
        this.averageRating = landmark.getAverageRating();
        this.comments = new HashSet<>();
    }
}
