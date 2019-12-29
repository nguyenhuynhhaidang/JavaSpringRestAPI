package com.dang.backend.controller;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.annotation.PostConstruct;

import com.dang.backend.model.User;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/api/v1")
public class UserController {

    List<User> userList = new CopyOnWriteArrayList<>();

    @PostConstruct
    public void init() {
        // Thêm null vào List để bỏ qua vị trí số 0;
        userList.add(null);
    }

    @GetMapping(value = "/user")
    public List<User> getUsers() {
        return userList;
    }

    @PostMapping(value = "/user")
    public ResponseEntity addUser(@RequestBody User user) {
        userList.add(user);
        // Trả về response với STATUS CODE = 200 (OK)
        // Body sẽ chứa thông tin về đối tượng todo vừa được tạo.
        return ResponseEntity.ok().body(user);
    }

    @GetMapping(value = "/user/{id}")
    public User getUser(@PathVariable(name = "id") int id) {
        return userList.get(id);
    }

    @PutMapping("/user/{id}")
    public User editTodo(@PathVariable(name = "id") Integer id, @RequestBody User user) {
        userList.set(id, user);
        // Trả về đối tượng sau khi đã edit
        return user;
    }

    @DeleteMapping("/user/{id}")
    public ResponseEntity deleteTodo(@PathVariable(name = "id") Integer id) {
        userList.remove(id.intValue());
        return ResponseEntity.ok().build();
    }
}