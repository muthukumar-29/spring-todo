package dev.muthu.todo_auth.controller;

import dev.muthu.todo_auth.model.Todo;
import dev.muthu.todo_auth.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/todo")
@RestController
public class TodoController {

    @Autowired
    private TodoService todoService;

    @PostMapping
    ResponseEntity<Todo> create(@RequestBody Todo todo) {
        return new ResponseEntity<>(todoService.create(todo), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    ResponseEntity<Todo> getById(@PathVariable Long id) {
        try {
            Todo getByIdTodo = todoService.getById(id);
            return new ResponseEntity<>(getByIdTodo, HttpStatus.OK);
        } catch (RuntimeException exception) {
            return new ResponseEntity<>((HttpHeaders) null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    ResponseEntity<List<Todo>> getAll(){
        return new ResponseEntity<>(todoService.getAll(), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    ResponseEntity<Todo> update(@PathVariable Long id, @RequestBody Todo todo){
        return new ResponseEntity<>(todoService.updateById(id, todo), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    void deleteById(@PathVariable Long id){
        todoService.deleteById(id);
    }

    @DeleteMapping
    void deleteById(@RequestBody Todo todo){
        todoService.delete(todo);
    }

}

