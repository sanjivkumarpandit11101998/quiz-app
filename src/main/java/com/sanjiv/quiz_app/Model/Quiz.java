package com.sanjiv.quiz_app.Model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Quiz {

    @Id
    private Integer id;

    private String title;

    @ManyToMany
    private List<Question> questions;

//    public List<Question> getQuestions() {
//        return questions;
//    }
}
