package com.example.restfulwebservice.user;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class UserController {
    private UserDaoService service;

    public UserController(UserDaoService service){
        this.service = service;
    }

    @GetMapping("/users")
    public List<User> retrieveAllUsers(){
        return service.findAll();
    }

    // GET /users/1~~
    @GetMapping("/users/{id}")
    public EntityModel<User> retrieveUser(@PathVariable int id) {
        User user = service.findOne(id);
        if (user == null){
            throw  new UserNotFoundException(String.format("ID[%s] not fount",id));
        }

        // HATEOAS
        // 하나의 리소스에서 파생할 수 있는 기능을 추가적으로 링크 가능
        // ex: 조회 기능의 리소스를 삭제, 수정 정보랑 연동
        EntityModel<User> model = new EntityModel<>(user);
        WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retrieveAllUsers());
        model.add(linkTo.withRel("all-users"));

        return model;
    }


    @PostMapping("/users")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user){ // @Valid check

        User savedUser = service.save(user);

        // 전송 완료 메세지(201)와 id를 클라이언트에게 응답코드로 전송
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }
    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable int id){
        User user = service.deleteById(id);

        if (user == null){
            throw new UserNotFoundException(String.format("ID[%s] not fount",id));
        }

    }
    @PutMapping("/users/{id}")
    public void modifyUser(@PathVariable int id, @RequestBody User newUser){
        User user = service.modifyById(id, newUser.getName());
        if (user == null){
            throw new UserNotFoundException(String.format("ID[%s] not fount",id));
        }

    }




}
