package com.sanjiv.quiz_app.Dao;

import com.sanjiv.quiz_app.Model.Question;
import com.sanjiv.quiz_app.Model.Quiz;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;


public interface QuizDao extends JpaRepository<Quiz, Integer> {


}
