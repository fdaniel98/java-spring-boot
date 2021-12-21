
package com.devtika.devtikaspringdocker.controller;

import com.devtika.devtikaspringdocker.entities.Todo;
import com.devtika.devtikaspringdocker.services.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class DevtikaController {

    @Autowired
    private TodoService todoService;

    @GetMapping("/")
    public String home()
    {
        return "Devtike Java Knowledge Project - TODO List";
    }

    @GetMapping("/to-do")
    public List<Todo> index (){
        return todoService.getTodo();
    }

    @GetMapping("/to-do/{id}")
    public ResponseEntity<Todo> show(@PathVariable(value = "id") long id) {
        Optional<Todo> todo = todoService.getTodoTaskById(id);
        return todo.map(value -> ResponseEntity.ok().body(value)).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/to-do")
    public Todo store(@Validated @RequestBody(required = true) Todo todo) {
        return todoService.createTodoTask(todo);
    }

    @PutMapping("/to-do/{id}")
    public ResponseEntity<Todo> update (@PathVariable(value = "id") long id, @RequestBody(required = true) Todo todoChange) {
        Todo todo = todoService.updateTodoTask(id, todoChange);

        if(todo != null){
            return ResponseEntity.ok().body(todo);
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/to-do/{id}")
    public ResponseEntity<String> delete (@PathVariable(value = "id") long id) {
        String response = todoService.deleteTodoTask(id);
        return ResponseEntity.ok().body(response);
    }
}
