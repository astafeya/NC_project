package com.project;

import com.project.model.Comment;
import com.project.model.Text;
import com.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class SpringBootStarter implements CommandLineRunner {
    @Autowired
    private UserService service;

    public static void main(String[] args) {
        SpringApplication.run(SpringBootStarter.class, args);
    }

    @Override
    public void run(String ...args) {
        // User
        service.create("admin", "hgy7fgh", "jg@km.tg");
        service.create("admin2", "jgk;;mms", "gj@plp.fd");
        service.create("admin3", "kyyapds", "ssf@km.lg");
        service.create("admin4", "kyyapds", "ssf@km.lg");
        service.edit("admin3", "jjgljdj", "dds@fdd.ut");
        service.delete("admin2");

        // Text
        service.addText("admin", "some title", false, "some content");
        service.addText("admin", "some title 2", false, "some content 2");
        service.addText("admin", "some title 3", false, "some content 3");
        List<Text> inVisibleTexts = service.getInvisibleTexts("admin");
        service.editText("admin", "some another title", true, inVisibleTexts.get(0).getCreationDate(),
                "some another content");
        service.deleteText("admin", inVisibleTexts.get(1).getCreationDate());

        // Comment
        List<Text> visibleTexts = service.getVisibleTexts("admin");
        service.addComment("admin", visibleTexts.get(0).getCreationDate(), "admin", "some content");
        service.addComment("admin", visibleTexts.get(0).getCreationDate(), "admin", "some content 2");
        service.addComment("admin", visibleTexts.get(0).getCreationDate(), "admin", "some content 3");
        visibleTexts = service.getVisibleTexts("admin");
        List<Comment> comments = visibleTexts.get(0).getComments();
        service.editComment("admin", visibleTexts.get(0).getCreationDate(), "admin",
                comments.get(0).getDate(), "some new content");
        service.deleteComment("admin", visibleTexts.get(0).getCreationDate(), "admin",
                comments.get(1).getDate());

        // Evaluation
        List<Text> texts = service.getVisibleTexts("admin");
        service.addEvaluation("admin", texts.get(0).getCreationDate(), "admin3");
        service.addEvaluation("admin", texts.get(0).getCreationDate(), "admin4");
        service.isEvaluated("admin", texts.get(0).getCreationDate(), "admin3");
        service.deleteEvaluation("admin", texts.get(0).getCreationDate(), "admin3");
        service.isEvaluated("admin", texts.get(0).getCreationDate(), "admin3");
    }
}
