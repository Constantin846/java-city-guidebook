package tz.project.guidebook.rating.service;

import tz.project.guidebook.landmark.Landmark;
import tz.project.guidebook.rating.RatingRecord;

public interface RatingService {
    Landmark addRating(RatingRecord ratingRecord, long landmarkId);
}
