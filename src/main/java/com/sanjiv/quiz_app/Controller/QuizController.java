package com.sanjiv.quiz_app.Controller;

import com.sanjiv.quiz_app.Model.Question;
import com.sanjiv.quiz_app.Model.QuestionWrapper;
import com.sanjiv.quiz_app.Model.Response;
import com.sanjiv.quiz_app.Service.QuizService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("quiz")
public class QuizController {

    @Autowired
    QuizService quizService;

    @PostMapping("create")
    public ResponseEntity<String> createQuiz(@RequestParam int id, @RequestParam String category, @RequestParam int numQ, @RequestParam String title){
        return quizService.createQuiz(id, category, numQ, title);
    }

    @GetMapping("get/{id}")
    public ResponseEntity<List<QuestionWrapper>> getQuizQuestion(@PathVariable Integer id){
        return quizService.getQuizQuestion(id);
    }

    @PostMapping("submit/{id}")
    public ResponseEntity<Integer> submitQuiz(@PathVariable Integer id, @RequestBody List<Response> responses){
        return quizService.calculateResult(id, responses);
    }

}
