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
        userService.create("admin", "rioir", "fd@klkl.oi");
        userService.create("admin2", "rioirsa", "fsad@klkl.oi");
        userService.edit("admin2", "iopk", "jfjfk@ld.tr");
        userService.findByLogin("admin");
        userService.delete("admin");

        //Text
        textService.create((long) 1, "Some title", false, "Some content");
        textService.create((long) 1, "Some title2", false, "Some content2");
        List<Text> invisTexts = textService.getInvisibleTextsByOwnerID((long) 1);
        textService.edit((long) 1, "Some another title", true, invisTexts.get(1).getCreationDate(),
                "Some new content");
        List<Text> visTexts = textService.getVisibleTextsByOwnerID((long) 1);

        //Comment
        commentService.create((long)1, (long)1, "super");
        commentService.create((long)1, (long)2, "super2");

        List<Comment> comments = commentService.getByTextID((long)1);

        commentService.edit(comments.get(0).getTextID(), comments.get(0).getCommentatorID(),
                comments.get(0).getDate(), "class");
        commentService.delete(comments.get(1).getTextID(), comments.get(1).getCommentatorID(),
                comments.get(1).getDate());

        //Evaluator
        evaluationService.create((long)1,(long)1);
        evaluationService.create((long)2,(long)1);
        evaluationService.create((long)3,(long)1);

        evaluationService.create((long)2,(long)2);

        evaluationService.create((long)1,(long)3);
        evaluationService.create((long)3,(long)3);

        evaluationService.getRating((long)1);
        evaluationService.getRating((long)2);
        evaluationService.getRating((long)3);

        evaluationService.isEvaluated((long)1, (long)1);
        evaluationService.isEvaluated((long)1, (long)2);
        evaluationService.isEvaluated((long)1, (long)3);

        evaluationService.delete((long)1, (long)1);
    }
}
