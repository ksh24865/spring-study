package com.example.springbootcommunityrest.repository;

import com.example.springbootcommunityrest.domain.Board;
import com.example.springbootcommunityrest.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {
    Board findByUser(User user);
}
