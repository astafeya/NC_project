package com.project.service;

import com.project.model.Text;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.project.repository.TextRepository;

import java.util.List;

@Service
public class TextService {
    @Autowired
    TextRepository textRepository;

    /*public Text create(int ownerID, String title, boolean visability, Date lastEditDate, String content) {
        return textRepository.insert(new Text(ownerID, title, visability, lastEditDate, content));
    }*/

    //public Text edit()

    public List<Text> getVisibleTextsByOwnerID(int ownerID) {
        return textRepository.findVisibleTextByOwnerID(ownerID);
    }

    public List<Text> getInvisibleTextsByOwnerID(int ownerID) {
        return textRepository.findInvisibleTextByOwnerID(ownerID);
    }
}
