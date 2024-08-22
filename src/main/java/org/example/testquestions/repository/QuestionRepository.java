package org.example.testquestions.repository;

import org.example.testquestions.entity.TestQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends JpaRepository<TestQuestion,Long> {
}
