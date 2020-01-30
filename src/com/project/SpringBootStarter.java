package com.project;

import com.project.model.Comment;
import com.project.service.CommentService;
import com.project.service.TextService;
import com.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.project.service.EvaluationService;

import java.util.Date;
import java.util.List;

@SpringBootApplication
public class SpringBootStarter {
    @Autowired
    private UserService userService;

    @Autowired
    private TextService textService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private EvaluationService evaluationService;

    public static void main(String[] args) {
        SpringApplication.run(SpringBootStarter.class, args);
    }

    public void run(String ...args) {
        //User
        userService.create("admin", "rioir", "fd@klkl.oi");
        userService.create("admin2", "rioirsa", "fsad@klkl.oi");
        userService.findByLogin("admin");
        userService.delete("admin");

        //Text


        //Comment
        commentService.create(1, 1, "super");
        commentService.create(1, 2, "super2");

        List<Comment> comments = commentService.getByTextID(1);
        commentService.edit(comments.get(0).getTextID(), comments.get(0).getCommentatorID(),
                comments.get(0).getDate(), "class");
        commentService.delete(comments.get(1).getTextID(), comments.get(1).getCommentatorID(),
                comments.get(1).getDate());

        //Evaluator
        evaluationService.create(1,1);
        evaluationService.create(2,1);
        evaluationService.create(3,1);

        evaluationService.create(2,2);

        evaluationService.create(1,3);
        evaluationService.create(3,3);

        evaluationService.getRating(1);
        evaluationService.getRating(2);
        evaluationService.getRating(3);

        evaluationService.delete(1,1);
    }
}
