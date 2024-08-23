package org.example.testquestions.service;

import org.example.testquestions.entity.TestQuestion;
import org.example.testquestions.repository.QuestionRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {

   QuestionRepository repository;

    public List<TestQuestion> getAllQuestions() {
        return repository.findAll();
    }

    public Optional<TestQuestion> getQuestionById(Long id) {
        return repository.findById(id);
    }

    public TestQuestion addQuestion(TestQuestion question) {
        return repository.save(question);
    }

    public Optional<TestQuestion> updateQuestion(Long id, TestQuestion newQuestion) {
        return repository.findById(id).map(question -> {
            question.setQuestion(newQuestion.getQuestion());
            question.setOptionA(newQuestion.getOptionA());
            question.setOptionB(newQuestion.getOptionB());
            question.setOptionC(newQuestion.getOptionC());
            question.setOptionD(newQuestion.getOptionD());
            question.setCorrectOption(newQuestion.getCorrectOption());
            return repository.save(question);
        });
    }

    public boolean deleteQuestion(Long id) {
        return repository.findById(id).map(question -> {
            repository.delete(question);
            return true;
        }).orElse(false);
    }
}
