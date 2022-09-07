package com.assessment.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.assessment.data.LearnersProfiles1;
import com.assessment.repositories.LearnersProfiles1Repository;
import com.assessment.services.LearnersProfiles1Service;

@Service
@Transactional
public class LearnersProfiles1Impl implements LearnersProfiles1Service {

	@Autowired
	LearnersProfiles1Repository profiles1Repository;

	@Override
	public void saveOrUpdate(List<LearnersProfiles1> learners) {
		profiles1Repository.deleteAll();
		for (LearnersProfiles1 li : learners) {
			profiles1Repository.save(li);
		}
	}

}
