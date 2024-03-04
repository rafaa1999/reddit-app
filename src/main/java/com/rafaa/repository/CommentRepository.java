package com.rafaa.repository;

import com.rafaa.entity.Comment;
import com.rafaa.entity.Post;
import com.rafaa.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Long> {
   List<Comment> findByPost(Post post);
   List<Comment> findAllByUser(User user);
}
