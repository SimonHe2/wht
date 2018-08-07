package com.hjh.wht.facade;

import com.hjh.wht.repository.dao.UserQuestionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class WhtController {

    @Autowired
    private UserQuestionDao userQuestionDao;

    @RequestMapping("/questions")
    @ResponseBody
    public String getQuestions(){
        return "question";
    }
}
