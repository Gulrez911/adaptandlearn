package com.assessment.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.assessment.data.CandidateDetailsForJD;

public interface CandidateDetailsForJDRepository extends JpaRepository<CandidateDetailsForJD, Long> {

	@Query("SELECT r FROM CandidateDetailsForJD r WHERE r.companyId=:companyId and r.jobDescId=:jobDescId order by relevantYears desc")
	List<CandidateDetailsForJD> findByRelvancy( @Param("companyId") String companyId, @Param("jobDescId") Long jobDescId);
}
