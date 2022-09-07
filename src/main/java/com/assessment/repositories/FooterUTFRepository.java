package com.assessment.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.assessment.data.FooterUTF;

public interface FooterUTFRepository extends JpaRepository<FooterUTF, Long>{

	FooterUTF findByLanguage(String lang);
}
