package com.assessment.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.assessment.data.PublicTestUTF;

public interface PublicTestUTFRepository extends JpaRepository<PublicTestUTF, Long>{

	PublicTestUTF findByLanguage(String lang);
}
