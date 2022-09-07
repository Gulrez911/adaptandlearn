package com.assessment.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.assessment.data.StudentJourneyUTF;

public interface StudentJourneyUTFRepository extends JpaRepository<StudentJourneyUTF, Long>{

	StudentJourneyUTF findByLanguage(String lang);
}
