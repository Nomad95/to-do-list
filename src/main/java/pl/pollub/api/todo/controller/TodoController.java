package pl.pollub.api.todo.controller;


import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.pollub.api.todo.list.TodoList;
import pl.pollub.api.todo.model.NewTodo;
import pl.pollub.api.todo.model.Todo;

import java.util.List;

@RestController
@RequestMapping(value = "/api/todo")
public class TodoController {

    private final @NonNull TodoList todoList;

    @Autowired
    public TodoController(TodoList todoList) {
        this.todoList = todoList;
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.CREATED)
    public void addTodo(@RequestBody NewTodo todo){
        todoList.addTodoFromNewTodo(todo);
    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Todo> getTodos(){
        return todoList.findAll();
    }

    @RequestMapping(value = "/{todoId}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<Boolean> removeTodoById(@PathVariable("todoId")Integer todoId){
        boolean wasRemoved = todoList.removeById(todoId);
        return wasRemoved
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
