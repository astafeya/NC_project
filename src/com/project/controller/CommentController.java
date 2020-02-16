package com.project.controller;

import com.project.model.Comment;
import com.project.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/{login}/{textId}")
public class CommentController {
    @Autowired
    CommentService service;

    @GetMapping("/comments")
    public ResponseEntity<List<Comment>> getComments(@PathVariable String login, @PathVariable long textId) {
        List<Comment> comments = service.getAll(login, textId);
        if (comments == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return ResponseEntity.ok(comments);
        }
    }

    @PostMapping("/newComment")
    public void addComment(@PathVariable String login, @PathVariable long textId, @RequestBody Comment comment) {
        service.add(login, textId, comment.getCommentatorLogin(), comment.getContent());
    }

    @PostMapping("/{commentId}/edit")
    public void editComment(@PathVariable String login, @PathVariable long textId,
                            @PathVariable long commentId, @RequestBody String content) {
        service.edit(login, textId, commentId, content);
    }

    @DeleteMapping("/{commentId}/delete")
    public void deleteEvaluation(@PathVariable String login, @PathVariable long textId,
                                 @PathVariable long commentId) {
        service.delete(login, textId, commentId);
    }
}
