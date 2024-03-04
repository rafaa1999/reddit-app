package com.rafaa.repository;

import com.rafaa.entity.Subreddit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubredditRepository extends JpaRepository<Subreddit,Long> {
   List<Subreddit> findByName(String subredditName);
}
