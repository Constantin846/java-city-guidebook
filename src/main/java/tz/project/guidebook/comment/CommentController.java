package tz.project.guidebook.comment;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import tz.project.guidebook.comment.service.CommentService;

@Slf4j
@RestController
@RequestMapping("/comment")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/{landmarkId}")
    @ResponseStatus(HttpStatus.CREATED)
    public Comment create(@Valid @RequestBody Comment comment,
                          @PathVariable("landmarkId") long landmarkId) {
        log.info("Add comment {} to landmark by id={}", comment.getDescription(), landmarkId);
        return commentService.addComment(comment, landmarkId);
    }
}
