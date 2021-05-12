package com.example.springbootcommunityrest.repository;

import com.example.springbootcommunityrest.domain.Board;
import com.example.springbootcommunityrest.domain.User;
import com.example.springbootcommunityrest.domain.projection.BoardOnlyContainTitle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Repository;

@RepositoryRestResource(excerptProjection = BoardOnlyContainTitle.class)
public interface BoardRepository extends JpaRepository<Board, Long> {
    Board findByUser(User user);

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    <S extends Board> S save(S entity);

}
