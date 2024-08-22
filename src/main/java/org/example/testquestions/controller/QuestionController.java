package org.example.testquestions.controller;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.example.testquestions.entity.TestQuestion;
import org.example.testquestions.repository.QuestionRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@NoArgsConstructor
@AllArgsConstructor
@RequestMapping("/api/questions")
public class QuestionController {

   QuestionRepository repository;

    // POST - суроо кошуу
    @PostMapping
    public TestQuestion addQuestion(@RequestBody TestQuestion question) {
        return repository.save(question);
    }

    // GET - бардык суроолорду алуу
    @GetMapping
    public List<TestQuestion> getQuestions() {
        return repository.findAll();
    }

    // GET - конкреттүү суроону алуу
    @GetMapping("/{id}")
    public ResponseEntity<TestQuestion> getQuestionById(@PathVariable Long id) {
        Optional<TestQuestion> question = repository.findById(id);
        return question.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // PUT - суроону жаңыртуу
    @PutMapping("/{id}")
    public ResponseEntity<TestQuestion> updateQuestion(@PathVariable Long id, @RequestBody TestQuestion newQuestion) {
        return repository.findById(id)
                .map(question -> {
                    question.setQuestion(newQuestion.getQuestion());
                    question.setOptionA(newQuestion.getOptionA());
                    question.setOptionB(newQuestion.getOptionB());
                    question.setOptionC(newQuestion.getOptionC());
                    question.setOptionD(newQuestion.getOptionD());
                    question.setCorrectOption(newQuestion.getCorrectOption());
                    TestQuestion updatedQuestion = repository.save(question);
                    return ResponseEntity.ok(updatedQuestion);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // DELETE - суроону өчүрүү
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteQuestion(@PathVariable Long id) {
        return repository.findById(id)
                .map(question -> {
                    repository.delete(question);
                    return ResponseEntity.ok().<Void>build();
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
