package com.example.springbootcommunityweb.repository;

import com.example.springbootcommunityweb.domain.Board;
import com.example.springbootcommunityweb.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {
    Board findByUser(User user);
}
