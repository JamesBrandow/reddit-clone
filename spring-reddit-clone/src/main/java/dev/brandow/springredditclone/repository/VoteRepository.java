package dev.brandow.springredditclone.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import dev.brandow.springredditclone.model.Vote;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Long>{

}
