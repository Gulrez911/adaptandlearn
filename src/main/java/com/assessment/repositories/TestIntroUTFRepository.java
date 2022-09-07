package com.assessment.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.assessment.data.TestIntroUTF;

public interface TestIntroUTFRepository extends JpaRepository<TestIntroUTF, Long>{

	TestIntroUTF findByLanguage(String lang);
}
