package dev.muthu.todo_auth.service;

import dev.muthu.todo_auth.model.Todo;
import dev.muthu.todo_auth.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService {

    @Autowired
    private TodoRepository todoRepository;

    public Todo create(Todo todo){
        return todoRepository.save(todo);
    }

    public Todo getById(Long id){
        return todoRepository.findById(id).orElseThrow(()->new RuntimeException("Todo Not Found"));
    }

    public List<Todo> getAll(){
        return todoRepository.findAll();
    }

    public Page<Todo> getAllPages(int page, int size){
        Pageable pageable = PageRequest.of(page, size);
        return todoRepository.findAll(pageable);
    }

    public Todo updateById(Long id, Todo todo){
//        Todo existing = getById(id);
//        existing.setTitle(todo.getTitle());
//        existing.setDescription(todo.getDescription());
//        existing.setCompleted(todo.isCompleted());
        return todoRepository.save(todo);
    }

    public void deleteById(Long id){
        todoRepository.deleteById(id);
    }

    public void delete(Todo todo){
        todoRepository.delete(todo);
    }
}
