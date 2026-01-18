package dev.muthu.todo_auth.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
@Entity
@Table(name = "todos")
public class Todo {

    @Id
    @GeneratedValue
    private Long id;

    @NotBlank
    @NotNull
    private String title;

    @NotBlank
    @NotNull
    private String description;

    private boolean isCompleted;

//    @Email
//    private String email;

//    @Pattern("")
//    private String phone;

//    @Size(min = 1, max = 100)
//    private int age;

}
