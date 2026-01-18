package dev.muthu.todo_auth.controller;

import dev.muthu.todo_auth.model.Todo;
import dev.muthu.todo_auth.service.TodoService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/todo")
@RestController
@Slf4j
public class TodoController {

    @Autowired
    private TodoService todoService;

    @PostMapping
    ResponseEntity<Todo> create(@RequestBody Todo todo) {
        return new ResponseEntity<>(todoService.create(todo), HttpStatus.CREATED);
    }

    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Todo fetched successfully"),
            @ApiResponse(responseCode = "404", description = "Todo not Found")
    })
    @GetMapping("/{id}")
    ResponseEntity<Todo> getById(@PathVariable Long id) {
        try {
            Todo getByIdTodo = todoService.getById(id);
            return new ResponseEntity<>(getByIdTodo, HttpStatus.OK);
        } catch (RuntimeException exception) {
//            log.error("Error");
//            log.warn("Warning");
//            log.info("Running...");
            return new ResponseEntity<>((HttpHeaders) null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    ResponseEntity<List<Todo>> getAll(){
        return new ResponseEntity<>(todoService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/page")
    ResponseEntity<Page<Todo>> getAllByPages(@RequestParam int page, @RequestParam int size){
        return new ResponseEntity<>(todoService.getAllPages(page, size), HttpStatus.OK);
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

