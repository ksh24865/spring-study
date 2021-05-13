package com.springboot.community.batch.service;

import com.springboot.community.batch.repository.BoardPictureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoardPictureService {

    private final BoardPictureRepository boardPictureRepository;


    public BoardPictureService(BoardPictureRepository boardPictureRepository) {
        this.boardPictureRepository = boardPictureRepository;
    }
}
