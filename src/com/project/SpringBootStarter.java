package com.project;

import com.project.model.Comment;
import com.project.model.Text;
import com.project.service.CommentService;
import com.project.service.TextService;
import com.project.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.project.service.EvaluationService;

import java.util.Date;
import java.util.List;

@SpringBootApplication
public class SpringBootStarter implements CommandLineRunner {
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

    @Override
    public void run(String ...args) {
        //User
        /*userService.create(1,"admin", "rioir", "fd@klkl.oi");
        userService.create(2,"admin2", "rioirsa", "fsad@klkl.oi");
        userService.edit("admin2", "iopk", "jfjfk@ld.tr");
        userService.findByLogin("admin");
        userService.delete("admin");*/

        //Text
        /*textService.create(1,1, "Some title", false, "Some content");
        textService.create(2,1, "Some title2", false, "Some content2");
        List<Text> invisTexts = textService.getInvisibleTextsByOwnerID(1);
        textService.edit(1, "Some another title", true, invisTexts.get(1).getCreationDate(),
                "Some new content");
        List<Text> visTexts = textService.getVisibleTextsByOwnerID(1);*/

        //Comment
        /*commentService.create(1, 1, "super");
        commentService.create(1, 2, "super2");

        List<Comment> comments = commentService.getByTextID(1);*/

        /*commentService.edit(comments.get(0).getTextID(), comments.get(0).getCommentatorID(),
                comments.get(0).getDate(), "class");
        commentService.delete(comments.get(1).getTextID(), comments.get(1).getCommentatorID(),
                comments.get(1).getDate());*/

        //Evaluator
        /*evaluationService.create(1,1);
        evaluationService.create(2,1);
        evaluationService.create(3,1);

        evaluationService.create(2,2);

        evaluationService.create(1,3);
        evaluationService.create(3,3);*/

        /*evaluationService.getRating(1);
        evaluationService.getRating(2);
        evaluationService.getRating(3);

        evaluationService.isEvaluated(1, 1);
        evaluationService.isEvaluated(1, 2);
        evaluationService.isEvaluated(1, 3);*/

        //evaluationService.delete(1,1);
    }
}
