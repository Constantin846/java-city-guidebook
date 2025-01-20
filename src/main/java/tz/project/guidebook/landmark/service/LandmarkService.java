package tz.project.guidebook.landmark.service;

import tz.project.guidebook.landmark.Landmark;
import tz.project.guidebook.landmark.dto.LandmarkCommentsDto;
import tz.project.guidebook.landmark.dto.LandmarkFindParams;

import java.util.List;

public interface LandmarkService {
    Landmark create(Landmark landmark);

    List<Landmark> findByParams(LandmarkFindParams params);

    Landmark findById(long landmarkId);

    LandmarkCommentsDto findByIdWithComments(long landmarkId);
}
