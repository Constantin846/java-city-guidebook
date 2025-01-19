package tz.project.guidebook.rating;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tz.project.guidebook.landmark.Landmark;
import tz.project.guidebook.rating.service.RatingService;

@Slf4j
@RestController
@RequestMapping("/rating")
@RequiredArgsConstructor
public class RatingController {
    private final RatingService ratingService;

    @PostMapping("/{landmarkId}")
    public Landmark create(@Valid @RequestBody RatingRecord ratingRecord,
                           @PathVariable("landmarkId") long landmarkId) {
        log.info("Add ratingRecord {} to landmark by id={}", ratingRecord.getRating(), landmarkId);
        return ratingService.addRating(ratingRecord, landmarkId);
    }
}
