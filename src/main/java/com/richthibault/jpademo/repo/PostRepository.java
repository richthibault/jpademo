package com.richthibault.jpademo.repo;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.richthibault.jpademo.model.Post;

public interface PostRepository extends JpaRepository<Post, Integer> {

	// use named entity graph from the Post model object
	@Override
	@EntityGraph(value="Post.withAuthor",type=EntityGraphType.LOAD)
	Page<Post> findAll(Pageable pageable);
	
	
	// other approaches....
	
	// or define an ad hoc entitygraph
	@EntityGraph(attributePaths = {"author"})
	List<Post> findByAuthorLastName(String lastName);
	
	// or write a join in jpql
	@Query(value="select p, a from Post p inner join fetch p.author a",
			countQuery="select count(p) from Post p")
	Page<Post> findAllWithJoin(Pageable pageable);

	@Override
	@Query(value="select p, a from Post p inner join fetch p.author a")
	List<Post> findAll();

	@Query(value="select p, a from Post p inner join fetch p.author a where p.title = :title")
	List<Post> findByTitle(@Param("title") String title);

}
