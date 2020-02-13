package com.project.controller;

import com.project.model.Text;
import com.project.service.TextService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/{login}")
public class TextController {
    @Autowired
    TextService service;

    @PostMapping("/new_text") //+?
    public void addText(@PathVariable String login, String title, Boolean visibility, String content) {
        service.add(login, title, visibility, content);
    }

    @GetMapping("/{textId}") //+
    public ResponseEntity<Text> findText(@PathVariable String login, @PathVariable long textId) {
        Text text = service.getText(login, textId);
        if (text == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return ResponseEntity.ok(text);
        }
    }

    @PostMapping("/{textId}/edit") //+?
    public void editText(@PathVariable String login, @PathVariable long textId, String title, Boolean visibility,
                         String content) {
        service.edit(login, textId, title, visibility, content);
    }

    @DeleteMapping("/{textId}/delete") //+?
    public void deleteText(@PathVariable String login, @PathVariable long textId) {
        service.delete(login, textId);
    }

    @GetMapping("/texts") //+
    public ResponseEntity<List<Text>> getVisibleTexts(@PathVariable String login) {
        List<Text> texts = service.getVisibleTexts(login);
        if (texts == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return ResponseEntity.ok(texts);
        }
    }

    @GetMapping("/drafts") //+
    public ResponseEntity<List<Text>> getInvisibleTexts(@PathVariable String login) {
        List<Text> texts = service.getInvisibleTexts(login);
        if (texts == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return ResponseEntity.ok(texts);
        }
    }
}
