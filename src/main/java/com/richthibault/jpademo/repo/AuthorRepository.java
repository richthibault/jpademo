package com.richthibault.jpademo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.richthibault.jpademo.model.Author;

public interface AuthorRepository extends JpaRepository<Author, Integer> {
	

}
