package tz.project.guidebook.landmark;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import tz.project.guidebook.landmark.dto.LandmarkCommentsDto;
import tz.project.guidebook.landmark.dto.LandmarkFindParams;
import tz.project.guidebook.landmark.service.LandmarkService;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/landmark")
@RequiredArgsConstructor
public class LandmarkController {
    private final LandmarkService landmarkService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Landmark create(@Valid @RequestBody Landmark landmark) {
        log.info("Create new landmark: {}", landmark);
        return landmarkService.create(landmark);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Landmark> find(@Valid LandmarkFindParams params) {
        log.info("Find landmarks by params {}", params);
        return landmarkService.findByParams(params);
    }

    @GetMapping("/{landmarkId}")
    @ResponseStatus(HttpStatus.OK)
    public Landmark findById(@PathVariable("landmarkId") long landmarkId) {
        log.info("Find landmark by id={}", landmarkId);
        return landmarkService.findById(landmarkId);
    }

    @GetMapping("/{landmarkId}/comments")
    @ResponseStatus(HttpStatus.OK)
    public LandmarkCommentsDto findByIdWithComments(@PathVariable("landmarkId") long landmarkId) {
        log.info("Find landmark with comments by id={}", landmarkId);
        return landmarkService.findByIdWithComments(landmarkId);
    }
}
