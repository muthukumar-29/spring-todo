package dev.muthu.todo_auth.repository;

import dev.muthu.todo_auth.model.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo, Long> {
}
