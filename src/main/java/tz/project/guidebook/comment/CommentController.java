package tz.project.guidebook.comment;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tz.project.guidebook.comment.comment.CommentService;

@Slf4j
@RestController
@RequestMapping("/comment")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/{landmarkId}")
    public Comment create(@Valid @RequestBody Comment comment,
                          @PathVariable("landmarkId") long landmarkId) {
        log.info("Add comment {} to landmark by id={}", comment.getDescription(), landmarkId);
        return commentService.addComment(comment, landmarkId);
    }
}
