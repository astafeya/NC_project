package com.project.service;

import com.project.model.Text;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.project.repository.TextRepository;

import java.util.Date;
import java.util.List;

@Service
public class TextService {
    @Autowired
    TextRepository textRepository;

    public Text create(Integer ownerID, String title, Boolean visibility, String content) {
        Date creationDate = new Date();
        return textRepository.save(new Text(ownerID, title, visibility, creationDate, creationDate, content));
    }

    public Text edit(Integer ownerID, String title, Boolean visibility, Date creationDate, String content) {
        Text text = textRepository.find(ownerID, creationDate);
        text.setTitle(title);
        text.setVisibility(visibility);
        text.setContent(content);
        Date lastEditDate = new Date();
        text.setLastEditDate(lastEditDate);
        return textRepository.save(text);
    }

    public List<Text> getVisibleTextsByOwnerID(Integer ownerID) {
        return textRepository.findVisibleTextByOwnerID(ownerID);
    }

    public List<Text> getInvisibleTextsByOwnerID(Integer ownerID) {
        return textRepository.findInvisibleTextByOwnerID(ownerID);
    }
}
