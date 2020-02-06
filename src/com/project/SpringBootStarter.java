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
    private UserService userService;

    public static void main(String[] args) {
        SpringApplication.run(SpringBootStarter.class, args);
    }

    @Override
    public void run(String ...args) {
        userService.create("admin", "hgy7fgh", "jg@km.tg");
        userService.addText("admin", "some title", false, "some content");
        userService.addText("admin", "some title 2", false, "some content 2");
        userService.addText("admin", "some title 3", false, "some content 3");
        List<Text> inVisibleTexts = userService.getInvisibleTexts("admin");
        userService.editText("admin", "some another title", true, inVisibleTexts.get(0).getCreationDate(),
                "some another content");
        userService.deleteText("admin", inVisibleTexts.get(1).getCreationDate());
        List<Text> visibleTexts = userService.getVisibleTexts("admin");
        userService.addComment("admin", visibleTexts.get(0).getCreationDate(), "admin", "some content");
        userService.addComment("admin", visibleTexts.get(0).getCreationDate(), "admin", "some content 2");
        userService.addComment("admin", visibleTexts.get(0).getCreationDate(), "admin", "some content 3");
        visibleTexts = userService.getVisibleTexts("admin");
        List<Comment> comments = visibleTexts.get(0).getComments();
        userService.editComment("admin", visibleTexts.get(0).getCreationDate(), "admin",
                comments.get(0).getDate(), "some new content");
        userService.deleteComment("admin", visibleTexts.get(0).getCreationDate(), "admin",
                comments.get(1).getDate());
    }
}
