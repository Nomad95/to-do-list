package pl.pollub.api.todo.controller;


import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.pollub.api.collaborators.model.User;
import pl.pollub.api.collaborators.model.UserList;
import pl.pollub.api.todo.model.NewTodo;
import pl.pollub.api.todo.model.Todo;
import pl.pollub.exception.exceptions.ElementNotFoundException;

import java.util.List;

@RestController
@RequestMapping(value = "/api/todo")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TodoController {

    private final @NonNull UserList userList;

    @RequestMapping(value = "/{userId}",method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    public @ResponseBody
    Todo addTodoToUserList(@RequestBody NewTodo todo, @PathVariable("userId")Integer userId){
        return userList.findOne(userId).getPrivateTodos().addTodoFromNewTodo(todo);
    }

    @RequestMapping(value = "{userId}",method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.OK)
    public @ResponseBody  Todo updateUserTodo(@RequestBody Todo todo, @PathVariable("userId") Integer userId){
        Todo foundTodo = userList.findOne(userId).getPrivateTodos().findOne(todo.getId());
        if(foundTodo != null)
            return userList.findOne(userId).getPrivateTodos().save(todo);
        else throw new ElementNotFoundException();
    }

    @RequestMapping(value = "{userId}",method = RequestMethod.GET)
    public @ResponseBody
    List<Todo> getUserTodosList(@PathVariable("userId")Integer userId){
        return userList.findOne(userId).getPrivateTodos().findAll();
    }

    @RequestMapping(value = "{userId}/{todoId}",method = RequestMethod.GET)
    public @ResponseBody Todo getUserTodoById(@PathVariable("userId")Integer userId, @PathVariable("todoId")Integer todoId){
        return userList.findOne(userId).getPrivateTodos().findOne(todoId);
    }

    @RequestMapping(value = "{userId}/{todoId}", method = RequestMethod.DELETE)
    public void removeUserTodoById(@PathVariable("todoId")Integer todoId,@PathVariable("userId")Integer userId){
        userList.findOne(userId).getPrivateTodos().removeById(todoId);
    }

    @RequestMapping(value = "{userId}/unfinished",method = RequestMethod.GET)
    public @ResponseBody
    List<Todo> getUserUnfinishedTodos(@PathVariable("userId")Integer userId){
        return userList.findOne(userId).getPrivateTodos().getUnfinishedTodos();
    }

    @RequestMapping(value = "{userId}/accomplish/{todoId}",method = RequestMethod.POST)
    public @ResponseBody Todo setTodoAccomplished(@PathVariable("userId")Integer userId, @PathVariable("todoId")Integer todoId){
        User user = userList.findOne(userId);
        return user.getPrivateTodos().makeTodoAccomplished(todoId,user);
    }
}
