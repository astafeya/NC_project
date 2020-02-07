package com.project;

import com.project.model.Comment;
import com.project.model.Evaluation;
import com.project.model.Text;
import com.project.service.CommentService;
import com.project.service.EvaluationService;
import com.project.service.TextService;
import com.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

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
        // User
        userService.create("admin", "hgy7fgh", "jg@km.tg");
        userService.create("admin2", "jgk;;mms", "gj@plp.fd");
        userService.create("admin3", "kyyapds", "ssf@km.lg");
        userService.create("admin4", "kyyapds", "ssf@km.lg");
        userService.edit("admin3", "jjgljdj", "dds@fdd.ut");
        userService.delete("admin2");

        // Text
        textService.add("admin", "some title", false, "some content");
        textService.add("admin", "some title 2", false, "some content 2");
        textService.add("admin", "some title 3", false, "some content 3");
        List<Text> inVisibleTexts = textService.getInvisibleTexts("admin");
        textService.edit("admin", "some another title", true, inVisibleTexts.get(0).getCreationDate(),
                "some another content");
        textService.delete("admin", inVisibleTexts.get(1).getCreationDate());

        // Comment
        List<Text> visibleTexts = textService.getVisibleTexts("admin");
        commentService.add("admin", visibleTexts.get(0).getCreationDate(), "admin", "some content");
        commentService.add("admin", visibleTexts.get(0).getCreationDate(), "admin", "some content 2");
        commentService.add("admin", visibleTexts.get(0).getCreationDate(), "admin", "some content 3");
        visibleTexts = textService.getVisibleTexts("admin");
        List<Comment> comments = visibleTexts.get(0).getComments();
        commentService.edit("admin", visibleTexts.get(0).getCreationDate(), "admin",
                comments.get(0).getDate(), "some new content");
        commentService.delete("admin", visibleTexts.get(0).getCreationDate(), "admin",
                comments.get(1).getDate());

        // Evaluation
        List<Text> texts = textService.getVisibleTexts("admin");
        evaluationService.add("admin", texts.get(0).getCreationDate(), "admin3");
        evaluationService.add("admin", texts.get(0).getCreationDate(), "admin4");
        evaluationService.isEvaluated("admin", texts.get(0).getCreationDate(), "admin3");
        evaluationService.delete("admin", texts.get(0).getCreationDate(), "admin3");
        evaluationService.isEvaluated("admin", texts.get(0).getCreationDate(), "admin3");
    }
}
