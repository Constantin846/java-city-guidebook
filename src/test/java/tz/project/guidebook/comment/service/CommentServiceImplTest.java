package tz.project.guidebook.comment.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tz.project.guidebook.comment.Comment;
import tz.project.guidebook.comment.CommentRepository;
import tz.project.guidebook.exception.NotFoundException;
import tz.project.guidebook.landmark.Landmark;
import tz.project.guidebook.landmark.repository.LandmarkRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CommentServiceImplTest {
    @Mock
    private CommentRepository commentRepository;
    @Mock
    private LandmarkRepository landmarkRepository;
    @InjectMocks
    private CommentServiceImpl commentService;

    @Test
    void addComment() {
        Comment comment = new Comment();
        comment.setId(10L);
        Landmark landmark = new Landmark();
        landmark.setId(1L);

        when(landmarkRepository.findById(landmark.getId())).thenReturn(Optional.of(landmark));
        when(commentRepository.findById(comment.getId())).thenReturn(Optional.of(comment));

        Comment actualComment = commentService.addComment(comment, landmark.getId());

        assertEquals(comment, actualComment);
    }

    @Test
    void addComment_commentNotFoundException() {
        Comment comment = new Comment();
        comment.setId(10L);
        Landmark landmark = new Landmark();
        landmark.setId(1L);

        when(landmarkRepository.findById(landmark.getId())).thenReturn(Optional.of(landmark));
        when(commentRepository.findById(comment.getId())).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> commentService.addComment(comment, landmark.getId()));
    }

    @Test
    void addComment_landmarkNotFoundException() {
        Comment comment = new Comment();
        comment.setId(10L);
        Landmark landmark = new Landmark();
        landmark.setId(1L);

        when(landmarkRepository.findById(landmark.getId())).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> commentService.addComment(comment, landmark.getId()));
    }
}