package pl.pollub.api.collaborators.controller;

import com.fasterxml.jackson.databind.node.TextNode;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.pollub.api.collaborators.model.User;
import pl.pollub.api.collaborators.model.UserList;
import pl.pollub.api.todo.model.NewTodo;
import pl.pollub.api.todo.model.Todo;

import java.util.List;

import static pl.pollub.api.commons.factory.GeneralFactory.createNewUser;

@RestController
@RequestMapping(value = "/api/user")
public class UserController {

    private final @NonNull
    UserList userList;

    @Autowired
    public UserController(UserList userList) {
        this.userList = userList;
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public User addNewUser(@RequestBody TextNode name){
        return userList.save(createNewUser(userList.generateId(),name.asText()));
    }

    @RequestMapping(value = "/{userId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public User getUserById(@PathVariable("userId") Integer userId){
        return userList.findOne(userId);
    }

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<User> getAllUsers(){
        return userList.findAll();
    }

    @RequestMapping(value = "/{userId}", method = RequestMethod.DELETE)
    public ResponseEntity<Boolean> removeUser(@PathVariable("userId") Integer userId){
        boolean wasRemoved = userList.removeById(userId);
        return wasRemoved
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    //-- User todos --//

    @RequestMapping(value = "/todos/{userId}",method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.CREATED)
    public void addTodoToUserList(@RequestBody NewTodo todo, @PathVariable("userId")Integer userId){
        userList.findOne(userId).getPrivateTodos().addTodoFromNewTodo(todo);
    }

    @RequestMapping(value = "/todos/{userId}",method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Todo> getUserTodosList(@PathVariable("userId")Integer userId){
        return userList.findOne(userId).getPrivateTodos().findAll();
    }

    @RequestMapping(value = "/todos/{userId}/{todoId}",method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Todo getUserTodoById(@PathVariable("userId")Integer userId, @PathVariable("todoId")Integer todoId){
        return userList.findOne(userId).getPrivateTodos().findOne(todoId);
    }

    @RequestMapping(value = "/todos/{userId}/{todoId}", method = RequestMethod.DELETE)
    public ResponseEntity<Boolean> removeUserTodoById(@PathVariable("todoId")Integer todoId,@PathVariable("userId")Integer userId){
        boolean wasRemoved = userList.findOne(userId).getPrivateTodos().removeById(todoId);
        return wasRemoved
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
