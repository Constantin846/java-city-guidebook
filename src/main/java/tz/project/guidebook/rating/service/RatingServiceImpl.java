package tz.project.guidebook.rating.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tz.project.guidebook.exception.NotFoundException;
import tz.project.guidebook.landmark.Landmark;
import tz.project.guidebook.landmark.repository.LandmarkRepository;
import tz.project.guidebook.rating.RatingRecord;
import tz.project.guidebook.rating.RatingRepository;

@Slf4j
@Service
@RequiredArgsConstructor
public class RatingServiceImpl implements RatingService {
    private final LandmarkRepository landmarkRepository;
    private final RatingRepository ratingRepository;

    @Transactional
    @Override
    public Landmark addRating(RatingRecord ratingRecord, long landmarkId) {
        Landmark landmark = getLandmarkById(landmarkId);
        ratingRecord.setLandmarkId(landmark.getId());
        ratingRepository.save(ratingRecord);
        calculateLandmarkAverageRating(landmark);
        return getLandmarkById(landmarkId);
    }

    private Landmark getLandmarkById(long id) {
        return landmarkRepository.findById(id).orElseThrow(() -> {
            String message = String.format("Landmark was not found by id: %d", id);
            log.warn(message);
            return new NotFoundException(message);
        });
    }

    private void calculateLandmarkAverageRating(Landmark landmark) {
        landmarkRepository.setAverageRating(landmark.getId());
    }
}
