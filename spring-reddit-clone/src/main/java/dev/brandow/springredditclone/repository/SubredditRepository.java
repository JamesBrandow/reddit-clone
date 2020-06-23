package dev.brandow.springredditclone.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import dev.brandow.springredditclone.model.Subreddit;
import org.springframework.stereotype.Repository;

@Repository
public interface SubredditRepository extends JpaRepository<Subreddit, Long> {

	Optional<Subreddit> findByName(String subredditName);
}
