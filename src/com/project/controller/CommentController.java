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

    @PostMapping("/new_comment")
    public void addComment(@PathVariable String login, @PathVariable long textId, String commentatorLogin,
                           String content) {
        service.add(login, textId, commentatorLogin, content);
    }

    @PostMapping("/{commentNumber}/edit")
    public void editComment(@PathVariable String login, @PathVariable long textId,
                            @PathVariable int commentNumber, String content) {
        service.edit(login, textId, commentNumber, content);
    }

    @DeleteMapping("/{commentNumber}/delete")
    public void deleteEvaluation(@PathVariable String login, @PathVariable long textId,
                                 @PathVariable int commentNumber) {
        service.delete(login, textId, commentNumber);
    }
}
