package tz.project.guidebook.rating.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tz.project.guidebook.exception.NotFoundException;
import tz.project.guidebook.landmark.Landmark;
import tz.project.guidebook.landmark.repository.LandmarkRepository;
import tz.project.guidebook.rating.RatingRecord;
import tz.project.guidebook.rating.RatingRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RatingServiceImplTest {
    @Mock
    private LandmarkRepository landmarkRepository;
    @Mock
    private RatingRepository ratingRepository;
    @InjectMocks
    private RatingServiceImpl ratingService;


    @Test
    void addRating() {
        RatingRecord ratingRecord = new RatingRecord();
        ratingRecord.setId(2L);
        Landmark landmark = new Landmark();
        landmark.setId(1L);

        when(landmarkRepository.findById(landmark.getId())).thenReturn(Optional.of(landmark));

        Landmark actualLandmark = ratingService.addRating(ratingRecord, landmark.getId());

        assertEquals(landmark, actualLandmark);
    }

    @Test
    void addComment_landmarkNotFoundException() {
        RatingRecord ratingRecord = new RatingRecord();
        ratingRecord.setId(2L);
        Landmark landmark = new Landmark();
        landmark.setId(1L);

        when(landmarkRepository.findById(landmark.getId())).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> ratingService.addRating(ratingRecord, landmark.getId()));
    }
}