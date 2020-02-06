package com.project;

import com.project.model.Comment;
import com.project.model.Text;
import com.project.service.CommentService;
import com.project.service.UserService;
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

    public static void main(String[] args) {
        SpringApplication.run(SpringBootStarter.class, args);
    }

    @Override
    public void run(String ...args) {
        //userService.create("admin", "hgy7fgh", "jg@km.tg");
        //userService.addText("admin", "some title", false, "some content");
        //userService.addText("admin", "some title 2", false, "some content 2");
        //List<Text> texts = userService.getInvisibleTexts("admin");
        //userService.editText("admin", "some another title", true, texts.get(0).getCreationDate(),
        //        "some another content");
        //userService.deleteText("admin", texts.get(0).getCreationDate());
    }
}
