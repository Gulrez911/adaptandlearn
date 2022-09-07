package com.assessment.services;

import java.util.List;

public interface GithubService {
	
	
	public List<String> fetchGithubPublicRepos(String gitUser);
	
	public boolean downloadZip(String name, String branch); 

}
