package tz.project.guidebook.landmark.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import tz.project.guidebook.exception.NotFoundException;
import tz.project.guidebook.landmark.Landmark;
import tz.project.guidebook.landmark.dto.LandmarkFindParams;
import tz.project.guidebook.landmark.repository.LandmarkRepository;
import tz.project.guidebook.landmark.repository.LandmarkSpecifications;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class LandmarkServiceImpl implements LandmarkService {
    private final LandmarkRepository landmarkRepository;

    @Transactional
    @Override
    public Landmark create(Landmark landmark) {
        Landmark savedLandmark = landmarkRepository.save(landmark);
        return getLandmarkById(savedLandmark.getId());
    }

    @Override
    public List<Landmark> findByParams(LandmarkFindParams params) {
        List<Specification<Landmark>> specifications = new ArrayList<>();
        Sort sort = Sort.by("averageRating");

        if (Objects.nonNull(params.getCity())) {
            Specification<Landmark> specification = LandmarkSpecifications.hasCityEquals(params.getCity());
            specifications.add(specification);
        }
        if (Objects.nonNull(params.getCategories())) {
            Specification<Landmark> specification = LandmarkSpecifications.hasCategoryEquals(params.getCategories());
            specifications.add(specification);
        }
        if (Objects.nonNull(params.getMaxRating())) {
            Specification<Landmark> specification =
                    LandmarkSpecifications.hasAverageRatingLessThanOrEqual(params.getMaxRating());
            specifications.add(specification);
        }
        if (Objects.nonNull(params.getMinRating())) {
            Specification<Landmark> specification =
                    LandmarkSpecifications.hasAverageRatingMoreThanOrEqual(params.getMinRating());
            specifications.add(specification);
        }
        if (Objects.nonNull(params.getLat()) && Objects.nonNull(params.getLon())
                && Objects.nonNull(params.getRadiusKM())) {
            Specification<Landmark> specification =
                    LandmarkSpecifications.hasLocationInRadiusKM(params.getLat(), params.getLon(), params.getRadiusKM());
            specifications.add(specification);
            sort = Sort.by("distance"); //todo
        }

        // todo sort

        if (specifications.isEmpty()) {
            return landmarkRepository.findAll(PageRequest.of(params.getFrom(), params.getSize(), sort))
                    .stream().toList();
        } else {
            Specification<Landmark> totalSpecification = specifications.stream().reduce(Specification::and).get();
            return landmarkRepository.findAll(totalSpecification,
                            PageRequest.of(params.getFrom(), params.getSize(), sort))
                    .stream().toList();
        }
    }

    private Landmark getLandmarkById(long id) {
        return landmarkRepository.findById(id).orElseThrow(() -> {
            String message = String.format("Landmark was not found by id: %d", id);
            log.warn(message);
            return new NotFoundException(message);
        });
    }
}
