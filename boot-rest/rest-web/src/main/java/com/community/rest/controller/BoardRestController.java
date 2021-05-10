package com.community.rest.controller;

import com.community.rest.domain.Board;
import com.community.rest.repository.BoardRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.PagedResources.PageMetadata;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/boards")
public class BoardRestController {
    private BoardRepository boardRepository;

    //의존성 주입
    public BoardRestController(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    //반환값은 JSON 타입
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getBoards(@PageableDefault Pageable pageable){
        Page<Board> boards = boardRepository.findAll(pageable);

        //현재 페이지 수, 총 게시판 수, 한 페이지의 게시판수 등 페이징 처리에 관한 리소스를 만드는
        //PagedResources 객체를 생성하기 위해 PagedResources 생성자의 파라미터로 사용되는 PageMetadata객체 생성
        //PageMetadata는 전체 페이지 수, 현재 페이지 번호, 총 게시판 수로 구성됨.
        PageMetadata pageMetadata = new PageMetadata(pageable.getPageSize(),
                boards.getNumber(), boards.getTotalElements());

        //PagedResources 객체 생성, 생성시 해태오스 적용되며 페이징값 생선된 REST형의 데이터 만들어줌
        PagedResources<Board> resources = new PagedResources<>(boards.getContent(),pageMetadata);

        //PagedResources 객체 생성 시 따로 링크를 설정하지 않았다면, 링크 추가 가능
        //Board 마다 상세 정보를 불러올 수 있는 링크 추가
        resources.add(linkTo(methodOn(BoardRestController.class).
                getBoards(pageable)).withSelfRel());
        return ResponseEntity.ok(resources);
    }
}
