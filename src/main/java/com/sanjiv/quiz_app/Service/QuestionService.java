package com.sanjiv.quiz_app.Service;

import com.sanjiv.quiz_app.Dao.QuestionDao;
import com.sanjiv.quiz_app.Model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {

    @Autowired
    QuestionDao questionDao;

    public ResponseEntity<List<Question>> getAllQuestions() {
        try{
            return new ResponseEntity<>(questionDao.findAll(), HttpStatus.OK);
        }catch(Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<List<Question>> getQuestionByCategory(String category) {

        try{
            return new ResponseEntity<List<Question>>(questionDao.findByCategory(category), HttpStatus.OK);
        }catch(Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<String> addQuestion(Question question) {
        questionDao.save(question);

        try{
            return new ResponseEntity<String>("success",HttpStatus.CREATED);
        }catch(Exception e) {
            e.printStackTrace();
        }

        return new ResponseEntity<>("", HttpStatus.BAD_REQUEST);
    }
}
