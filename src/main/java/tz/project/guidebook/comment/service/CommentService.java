package tz.project.guidebook.comment.service;

import tz.project.guidebook.comment.Comment;

public interface CommentService {
    Comment addComment(Comment comment, long landmarkId);
}
