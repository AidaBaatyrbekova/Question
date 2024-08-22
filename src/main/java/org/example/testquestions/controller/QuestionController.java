package org.example.testquestions.controller;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.example.testquestions.entity.TestQuestion;
import org.example.testquestions.service.QuestionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@NoArgsConstructor
@AllArgsConstructor
@RequestMapping("/api/questions")
public class QuestionController {

   QuestionService service;

    @PostMapping
    public TestQuestion addQuestion(@RequestBody TestQuestion question) {
        return service.addQuestion(question);
    }

    @GetMapping
    public List<TestQuestion> getQuestions() {
        return service.getAllQuestions();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TestQuestion> getQuestionById(@PathVariable Long id) {
        return service.getQuestionById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<TestQuestion> updateQuestion(@PathVariable Long id, @RequestBody TestQuestion newQuestion) {
        return service.updateQuestion(id, newQuestion)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteQuestion(@PathVariable Long id) {
        return service.deleteQuestion(id) ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }
}
