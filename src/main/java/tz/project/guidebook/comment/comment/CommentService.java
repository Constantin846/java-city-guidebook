package tz.project.guidebook.comment.comment;

import tz.project.guidebook.comment.Comment;

public interface CommentService {
    Comment addComment(Comment comment, long landmarkId);
}
