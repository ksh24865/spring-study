package com.springboot.community.batch.repository;

import com.springboot.community.batch.domain.BoardPicture;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardPictureRepository extends JpaRepository<BoardPicture, Long> {
    BoardPicture save(BoardPicture boardPicture);

    List<BoardPicture> findAllByBoardIdx(Integer boardIdx);
}

