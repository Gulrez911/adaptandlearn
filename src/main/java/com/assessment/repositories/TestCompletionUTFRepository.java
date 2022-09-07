package com.assessment.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.assessment.data.TestCompletionUTF;

public interface TestCompletionUTFRepository extends JpaRepository<TestCompletionUTF, Long>{

	TestCompletionUTF findByLanguage(String lang);
}
