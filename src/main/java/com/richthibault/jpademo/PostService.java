package com.richthibault.jpademo;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.richthibault.jpademo.model.Post;
import com.richthibault.jpademo.repo.PostRepository;

@Service
public class PostService {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	PostRepository postRepo;
	
	public PostService() {
		super();
	}

	public Page<Post> getPosts(int page) {
		logger.debug("Getting page {}", page);
		Pageable pageRequest = PageRequest.of(page, 10000, 
				Sort.by(Sort.Direction.DESC, "id"));
		return postRepo.findAll(pageRequest);
	}
	
	public List<Post> getPostsByLastName(String lastName) {
		return postRepo.findByAuthorLastName(lastName);
	}

}
