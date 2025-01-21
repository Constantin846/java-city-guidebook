package tz.project.guidebook.landmark.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tz.project.guidebook.comment.Comment;
import tz.project.guidebook.comment.CommentRepository;
import tz.project.guidebook.exception.NotFoundException;
import tz.project.guidebook.landmark.Landmark;
import tz.project.guidebook.landmark.dto.LandmarkCommentsDto;
import tz.project.guidebook.landmark.repository.LandmarkRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LandmarkServiceImplTest {
    @Mock
    private CommentRepository commentRepository;
    @Mock
    private LandmarkRepository landmarkRepository;
    @InjectMocks
    private LandmarkServiceImpl landmarkService;

    @Test
    void create() {
        Landmark landmark = new Landmark();
        landmark.setId(1L);

        when(landmarkRepository.save(landmark)).thenReturn(landmark);
        when(landmarkRepository.findById(landmark.getId())).thenReturn(Optional.of(landmark));

        Landmark actualLandmark = landmarkService.create(landmark);

        assertEquals(landmark, actualLandmark);
    }

    @Test
    void findById() {
        Landmark landmark = new Landmark();
        landmark.setId(1L);

        when(landmarkRepository.findById(landmark.getId())).thenReturn(Optional.of(landmark));

        Landmark actualLandmark = landmarkService.findById(landmark.getId());

        assertEquals(landmark, actualLandmark);
    }

    @Test
    void findById_landmarkNotFoundException() {
        Landmark landmark = new Landmark();
        landmark.setId(1L);

        when(landmarkRepository.findById(landmark.getId())).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> landmarkService.findById(landmark.getId()));
    }

    @Test
    void findByIdWithComments() {
        Landmark landmark = new Landmark();
        landmark.setId(1L);
        Comment comment = new Comment();
        comment.setId(2L);
        comment.setLandmarkId(landmark.getId());
        LandmarkCommentsDto lCDto = new LandmarkCommentsDto(landmark);
        lCDto.setComments(Set.of(comment));

        when(landmarkRepository.findById(landmark.getId())).thenReturn(Optional.of(landmark));
        when(commentRepository.findAllByLandmarkId(landmark.getId())).thenReturn(List.of(comment));

        LandmarkCommentsDto actualLCDto = landmarkService.findByIdWithComments(landmark.getId());

        assertEquals(lCDto, actualLCDto);
    }
}