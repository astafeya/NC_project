package com.project.service;

import com.project.model.Text;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.project.repository.TextRepository;

import java.util.Date;
import java.util.List;

@Service
public class TextService {
    private static Logger log = LogManager.getLogger(TextService.class.getName());
    @Autowired
    private TextRepository textRepository;

    public Text create(Integer id, Integer ownerID, String title, Boolean visibility, String content) {
        Date creationDate = new Date();
        //Integer id = //TODO: auto generation id
        Text text = textRepository.save(new Text(id, ownerID, title, visibility, creationDate, creationDate, content));
        log.info("create : " + text);
        return text;
    }

    public Text edit(Integer ownerID, String title, Boolean visibility, Date creationDate, String content) {
        Text text = textRepository.findByOwnerIDAndCreationDate(ownerID, creationDate);
        log.info("edit starts: " + text);
        text.setTitle(title);
        text.setVisibility(visibility);
        text.setContent(content);
        Date lastEditDate = new Date();
        text.setLastEditDate(lastEditDate);
        log.info("edit ends:" + text);
        return textRepository.save(text);
    }

    public List<Text> getVisibleTextsByOwnerID(Integer ownerID) {
        List<Text> texts = textRepository.findTextByOwnerIDAndVisibility(ownerID, true);
        log.info("getVisibleTextsByOwnerID: ownerID = " + ownerID + ", result = " + texts);
        return texts;
    }

    public List<Text> getInvisibleTextsByOwnerID(Integer ownerID) {
        List<Text> texts = textRepository.findTextByOwnerIDAndVisibility(ownerID, false);
        log.info("getInvisibleTextsByOwnerID: ownerID = " + ownerID + ", result = " + texts);
        return texts;
    }
}
