package com.sanjiv.quiz_app.Service;


import com.sanjiv.quiz_app.Dao.QuestionDao;
import com.sanjiv.quiz_app.Dao.QuizDao;
import com.sanjiv.quiz_app.Model.Question;
import com.sanjiv.quiz_app.Model.QuestionWrapper;
import com.sanjiv.quiz_app.Model.Quiz;
import com.sanjiv.quiz_app.Model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuizService {

    @Autowired
    QuizDao quizDao;

    @Autowired
    QuestionDao questionDao;


    public ResponseEntity<String> createQuiz(int id, String category, int numQ, String title) {
        List<Question> questions= questionDao.findRandomQuestionsByCategory(category, numQ);

        Quiz quiz= new Quiz();
        quiz.setId(id);
        quiz.setTitle(title);
        quiz.setQuestions(questions);

        quizDao.save(quiz);

        return new ResponseEntity<>("Success", HttpStatus.CREATED);

    }

    public ResponseEntity<List<QuestionWrapper>> getQuizQuestion(Integer id) {
        Optional<Quiz> quiz= quizDao.findById(id);

        int a=5;
        List<Question> questionsFromDB= quiz.get().getQuestions();
        List<QuestionWrapper> questionsForUser = new ArrayList<>();

        for(Question q: questionsFromDB){
            QuestionWrapper qw= new QuestionWrapper(q.getId(), q.getQuestionTitle(), q.getOption1(), q.getOption2(), q.getOption3(), q.getOption4());
            questionsForUser.add(qw);
        }


        return new ResponseEntity<>(questionsForUser, HttpStatus.OK);
    }

    public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) {
        Quiz quiz= quizDao.findById(id).get();
        List<Question> questions= quiz.getQuestions();
        int right= 0;
        int i= 0;
        for(Response response: responses){
            if(response.getResponse().equals(questions.get(i).getRightAnswer())){
                right++;
            }
            i++;
        }
        return new ResponseEntity<>(right, HttpStatus.OK);
    }
}
