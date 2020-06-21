package com.api.osspringboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.osspringboot.model.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long>{

}
