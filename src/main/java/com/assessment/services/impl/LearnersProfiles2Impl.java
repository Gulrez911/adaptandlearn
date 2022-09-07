package com.assessment.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.assessment.data.LearnersProfiles2;
import com.assessment.repositories.LearnersProfiles2Repository;
import com.assessment.services.LearnersProfiles2Service;

@Service
@Transactional
public class LearnersProfiles2Impl implements LearnersProfiles2Service {

	@Autowired
	LearnersProfiles2Repository profiles2Repository;

	@Override
	public void saveOrUpdate(List<LearnersProfiles2> learners) {
		profiles2Repository.deleteAll();
		for (LearnersProfiles2 li : learners) {
			profiles2Repository.save(li);
		}
	}

}
