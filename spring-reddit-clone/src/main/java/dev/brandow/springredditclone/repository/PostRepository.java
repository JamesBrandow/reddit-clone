package dev.brandow.springredditclone.repository;

import dev.brandow.springredditclone.model.Post;
import dev.brandow.springredditclone.model.Subreddit;
import dev.brandow.springredditclone.model.User;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
	 
	List<Post> findAllBySubreddit(Subreddit subreddit);
    List<Post> findByUser(User user);
}
