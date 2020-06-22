package dev.brandow.springredditclone.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import dev.brandow.springredditclone.model.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long>{

}
