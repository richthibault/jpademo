package com.richthibault.jpademo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.richthibault.jpademo.model.Post;

@RestController
@RequestMapping("/posts")
public class PostController {
	
	@Autowired
	PostService postService;
	
	@GetMapping("")
	public Page<Post> listPosts(@RequestParam(value="page",required=false,defaultValue="1") int page) {
		return postService.getPosts(page-1);
	}

}
