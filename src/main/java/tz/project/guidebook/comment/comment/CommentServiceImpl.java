package tz.project.guidebook.comment.comment;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tz.project.guidebook.comment.Comment;
import tz.project.guidebook.comment.CommentRepository;
import tz.project.guidebook.exception.NotFoundException;
import tz.project.guidebook.landmark.Landmark;
import tz.project.guidebook.landmark.repository.LandmarkRepository;

@Slf4j
@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final LandmarkRepository landmarkRepository;

    @Transactional
    @Override
    public Comment addComment(Comment comment, long landmarkId) {
        Landmark landmark = getLandmarkById(landmarkId);
        comment.setLandmarkId(landmark.getId());
        commentRepository.save(comment);
        return getCommentById(comment.getId());
    }

    private Comment getCommentById(long id) {
        return commentRepository.findById(id).orElseThrow(() -> {
            String message = String.format("Comment was not found by id: %d", id);
            log.warn(message);
            return new NotFoundException(message);
        });
    }

    private Landmark getLandmarkById(long id) {
        return landmarkRepository.findById(id).orElseThrow(() -> {
            String message = String.format("Landmark was not found by id: %d", id);
            log.warn(message);
            return new NotFoundException(message);
        });
    }
}
