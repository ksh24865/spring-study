package com.example.springbootcommunityweb.service;

import com.example.springbootcommunityweb.domain.Board;
import com.example.springbootcommunityweb.domain.enums.BoardType;
import com.example.springbootcommunityweb.repository.BoardRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class BoardService {
    private final BoardRepository boardRepository;

    public BoardService(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    public Page<Board> findBoardList(Pageable pageable){
        // pageale로 넘어온 pageNumber 객체가 0이하일 때 0으로 초기화,
        // 기본 페이지 크기인 10으로 새로운 pageRequest객체를 만들어 페이징 처리된 게시글 리스트 반환
        // DB에 저장된 Entity들을 페이지로 나누는 것
        pageable = PageRequest.of(pageable.getPageNumber() <= 0 ? 0 :
                pageable.getPageNumber() -1, pageable.getPageSize());
        return boardRepository.findAll(pageable);
    }
    public Board findBoardByIdx(Long idx){
        return boardRepository.findById(idx).orElse(new Board());
    }
}
