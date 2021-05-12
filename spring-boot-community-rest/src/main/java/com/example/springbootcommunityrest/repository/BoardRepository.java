package com.example.springbootcommunityrest.repository;

import com.example.springbootcommunityrest.domain.Board;
import com.example.springbootcommunityrest.domain.User;
import com.example.springbootcommunityrest.domain.projection.BoardOnlyContainTitle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RepositoryRestResource(excerptProjection = BoardOnlyContainTitle.class)
public interface BoardRepository extends JpaRepository<Board, Long> {
    Board findByUser(User user);

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    <S extends Board> S save(S entity);

    @RestResource(path = "query")
    List<Board> findByTitle(@Param("title") String title);

    // 노출하고 싶지 않은 메서지, 필드는
    // @RepositoryRestResource(exported = false)
    // @RestResource(exported = false)
    // 와 같이 지정

}
