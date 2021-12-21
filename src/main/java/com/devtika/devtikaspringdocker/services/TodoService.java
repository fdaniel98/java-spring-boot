package com.devtika.devtikaspringdocker.services;

import com.devtika.devtikaspringdocker.entities.Todo;
import com.devtika.devtikaspringdocker.repositories.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TodoService {

    @Autowired
    private TodoRepository todoRepository;

    public List<Todo> getTodo()
    {
        return (List<Todo>) todoRepository.findAll();
    }

    public Optional<Todo> getTodoTaskById(long id)
    {
        return todoRepository.findById(id);
    }

    public Todo createTodoTask(Todo todo)
    {
        return todoRepository.save(todo);
    }

    public Todo updateTodoTask(long id, Todo todoChange)
    {
        Optional<Todo> todoOpt = todoRepository.findById(id);

        if(todoOpt.isPresent()){
            Todo todo = todoOpt.get();
            String task = todoChange.getTask() != null && !todoChange.getTask().toString().isEmpty() ? todoChange.getTask() : todo.getTask();
            todo.setTask(task);
            todo.setDone(todoChange.getDone());

            return todoRepository.save(todo);
        }
        else {
            return null;
        }
    }

    public String deleteTodoTask(long id)
    {
        try {
            todoRepository.deleteById(id);
            return String.format("Task id: %s deleted", id);
        }
        catch (IllegalArgumentException e){
            return "The given id doesnt exist";
        }
        catch (Exception e){
            return "A unknown error occurred";
        }

    }
}
